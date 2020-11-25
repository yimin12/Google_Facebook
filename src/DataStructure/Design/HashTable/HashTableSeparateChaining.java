package DataStructure.Design.HashTable;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 22:07
 *   @Description :
 *
 */
@SuppressWarnings("unchecked")
public class HashTableSeparateChaining<K, V> implements Iterable<K> {

    private static final int DEFAULT_CAPACITY = 3; // default minimum size
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor; // manipulate the load factor
    private int capacity, threshhold, size = 0;
    private LinkedList<Entry<K, V>>[] table; // All the nodes in these bucket array are linked list (or use red-black tree)

    public HashTableSeparateChaining(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor){
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity");
        if(maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor)){
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        }
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshhold = (int)(this.capacity * this.maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    // Return # of elements currently inside the hash-table
    public int size(){
        return size;
    }

    // Return true/false if hash-table is/isn't empty
    public boolean isEmpty(){
        return size == 0;
    }

    // Convert a hash value to an index mapping the bucket array, this strips the negative sign and places the hash value in the domain [0, capacity);
    private int normalizeIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    // Clears all the contents of hash-table
    public void clear(){
        Arrays.fill(table, null); // free all list node
        size = 0;
    }

    public boolean containsKey(K key){
        return hasKey(key);
    }

    public boolean hasKey(K key){
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    // Implement the CRUD method
    public V put(K key, V value){
        return insert(key, value);
    }

    public V add(K key, V value){
        return insert(key, value);
    }

    public V insert(K key, V value){
        if(key == null) throw new IllegalArgumentException("Null key exception");
        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    public V get(K key){
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry != null) return entry.value;
        return null;
    }

    public V remove(K key){
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    // Removes an entry from a given bucket if it exists
    private V bucketRemoveEntry(int bucketIndex, K key){
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry != null){
            LinkedList<Entry<K, V>> links = table[bucketIndex];
            links.remove(entry);
            size--;
            return entry.value;
        } else return null;
    }

    // Finds and returns a particular entry in a given bucket if it exists, return null otherwise
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key){
        if(key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null) return null;
        for(Entry<K, V> entry : bucket) if (entry.key.equals(key)) return entry;
        return null;
    }

    // Inserts an entry in a given bucket only if the entry does not already exist in the given bucket, or update the entry value if it does
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry){
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null) table[bucketIndex] = bucket = new LinkedList<>();
        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if(existentEntry == null){
            bucket.add(entry);
            if(++size > threshhold) resizeTable();
            return null; // Null indicate that there was no previous entry
        } else {
            V oldValue = existentEntry.value;
            existentEntry.value = entry.value;
            return oldValue;
        }
    }

    // Resizes the internal table holding buckets of entries
    private void resizeTable(){
        capacity *= 2;
        threshhold = (int)(capacity * maxLoadFactor);
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
        // Use linear time to copy the array
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                for(Entry<K, V> entry : table[i]){
                    int bucketIndex = normalizeIndex(entry.hash); // hashCode is the same, no need to re-calculate
                    LinkedList<Entry<K , V>> bucket = newTable[bucketIndex];
                    if(bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
            }
            // Avoid memory leak. Help GC
            table[i].clear();
            table[i] = null;
        }
        table = newTable;
    }

    // Returns the list of keys found within the hash table
    public List<K> keys(){
        List<K> keys = new ArrayList<>(size());
        for(LinkedList<Entry<K, V>> bucket : table){
            if(bucket != null) for(Entry<K, V> entry : bucket) keys.add(entry.key);
        }
        return keys;
    }

    // Return the list of values found within the hash table
    public List<V> values(){
        List<V> values = new ArrayList<>(size());
        for(LinkedList<Entry<K, V>> bucket : table){
            if(bucket != null) for(Entry<K, V> entry : bucket) values.add(entry.value);
        }
        return values;
    }

    // Return an iterator to iterate over all the keys in this map
    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();
        return new Iterator<K>(){

            int bucketIndex = 0;
            Iterator<Entry<K, V>> bucketIter = (table[0] == null) ? null : table[0].iterator(); // use linked list build in iterator

            @Override
            public boolean hasNext() {
                if(elementCount != size()) throw new ConcurrentModificationException();
                if(bucketIter  == null || !bucketIter.hasNext()){
                    // Search next buckets until valid iterator is found
                    while(++bucketIndex < capacity){
                        if(table[bucketIndex] != null){
                            // Make sure whether it is only one node in this bucket
                            Iterator<Entry<K, V>> nextIter = table[bucketIndex].iterator();
                            if(nextIter.hasNext()){
                                bucketIter = nextIter;
                                break;
                            }
                        }
                    }
                }
                return bucketIndex < capacity;
            }

            @Override
            public K next() {
                return bucketIter.next().key;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }

    // Returns a string representation of this hash table
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry : table[i]) sb.append(entry + ", ");
        }
        sb.append("}");
        return sb.toString();
    }
    class Entry<K, V>{

        int hash;
        K key;
        V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.hash = key.hashCode(); // You can implement your won version of hashCode method.
        }

        // Use build in method in jdk
        public boolean equals(Entry<K, V> other){
            if(hash != other.hash){
                return false;
            }
            return key.equals(other.key);
        }

        @Override
        public String toString() {
            return key + " => " + value;
        }
    }
}
