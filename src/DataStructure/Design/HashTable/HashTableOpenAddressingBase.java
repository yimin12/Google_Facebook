package DataStructure.Design.HashTable;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 23:15
 *   @Description :
 *
 */
@SuppressWarnings("unchecked")
public abstract class HashTableOpenAddressingBase<K, V> implements Iterable<K> {

    protected double loadFactor;
    protected int capacity, threshold, modificationCount;

    // 'useBuckets' counts the total number of used buckets inside the hash-table (includes cells marked as deleted). While 'keyCount' tracks the number of unique
    // keys currently inside the hash-table
    protected int usedBuckets, keyCount;

    // Arrays store the key-value pairs
    protected K[] keys;
    protected V[] values;

    // Special marker token used to indicate the deletion of a key-value store pair
    protected final K TOMBSTONE = (K) new Object();

    private static final int DEFAULT_CAPACITY = 7;
    private static final double DEFAULT_LOAD_FACTOR = 0.65;

    protected HashTableOpenAddressingBase(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    protected HashTableOpenAddressingBase(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    protected HashTableOpenAddressingBase(int capacity, double loadFactor){
        if(capacity < 0) throw new IllegalArgumentException("Illgal capacity: " + capacity);
        if(loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)){
            throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        adjustCapacity();
        threshold = (int)(this.capacity * loadFactor);
        keys = (K[]) new Objects[this.capacity];
        values = (V[]) new Objects[this.capacity];
    }

    // Used to the probing is to actually occur for whatever open addressing scheme we are implementing
    protected abstract void setupProbing(K key);

    protected abstract int probe(int x);

    // Adjust the capacity of the hash table after it's been made larger. This is important to be able to override because the size of the hash-table controls the
    // functionality of the probing function
    protected abstract void adjustCapacity();

    // Increases the capacity of the hash table
    protected void increaseCapacity(){
        capacity = (2 * capacity) + 1;
    }

    public void clear(){
        for(int i = 0; i < capacity; i++){
            keys[i] = null;
            values[i] = null;
        }
        keyCount = usedBuckets = 0;
        modificationCount++;
    }

    // Returns the number of keys currently inside the hash-table
    public int size(){
        return keyCount;
    }

    // Returns the capacity of the hash-table
    public int getCapacity(){
        return capacity;
    }

    // Returns true/false depending on whether the hash-table empty
    public boolean isEmpty(){
        return keyCount == 0;
    }

    public V put(K key, V value){
        return insert(key, value);
    }

    public V add(K key, V value){
        return insert(key, value);
    }

    public boolean containsKey(K key){
        return hasKey(key);
    }

    public List<K> keys(){
        List<K> hashtableKeys = new ArrayList<>();
        for(int i = 0; i < capacity; i++){
            if(keys[i] != null && keys[i] != TOMBSTONE) hashtableKeys.add(keys[i]);
        }
        return hashtableKeys;
    }

    public List<V> values(){
        List<V> hashtableValues = new ArrayList<>(size());
        for(int i = 0; i < capacity; i++){
            if(keys[i] != null && keys[i] != TOMBSTONE) hashtableValues.add(values[i]);
        }
        return hashtableValues;
    }

    // Double the size of the hash-table
    protected void resizeTable(){
        // step 1: expand the capacity
        increaseCapacity();
        adjustCapacity();
        threshold = (int)(capacity * loadFactor);
        // step 2: prepare new container
        K[] oldKeyTable = (K[]) new Object[capacity];
        V[] oldValueTable = (V[]) new Object[capacity];
        // swap
        K[] keyTableTmp = keys;
        keys = oldKeyTable;
        oldKeyTable = keyTableTmp;
        V[] valueTableTmp = values;
        values = oldValueTable;
        oldValueTable = valueTableTmp;

        keyCount = usedBuckets = 0;
        for(int i = 0; i < oldKeyTable.length; i++){
            if(oldKeyTable[i] != null && oldKeyTable[i] != TOMBSTONE){
                insert(oldKeyTable[i], oldValueTable[i]);
            }
            // free the memory
            oldKeyTable[i] = null;
            oldValueTable[i] = null;
        }
    }

    // Converts a hash value to an index. Essentially, this strips the
    // negative sign and places the hash value in the domain [0, capacity)
    protected final int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    // Finds the greatest common denominator of a and b.
    protected static final int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Place a key-value pair into the hash-table. If the value already
    // exists inside the hash-table then the value is updated.
    public V insert(K key, V val) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (usedBuckets >= threshold) resizeTable();
        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());
        // key insight: fill the deleted slot for saving space
        for (int i = offset, j = -1, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // The current slot was previously deleted
            if (keys[i] == TOMBSTONE) {
                if (j == -1) j = i;

                // The current cell already contains a key
            } else if (keys[i] != null) {
                // The key we're trying to insert already exists in the hash-table,
                // so update its value with the most recent value
                if (keys[i].equals(key)) {
                    V oldValue = values[i];
                    if (j == -1) {
                        values[i] = val;
                    } else {
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        keys[j] = key;
                        values[j] = val;
                    }
                    modificationCount++;
                    return oldValue;
                }
                // Current cell is null so an insertion/update can occur
            } else {
                // No previously encountered deleted buckets
                if (j == -1) {
                    usedBuckets++;
                    keyCount++;
                    keys[i] = key;
                    values[i] = val;
                    // Previously seen deleted bucket. Instead of inserting
                    // the new element at i where the null element is insert
                    // it where the deleted token was found.
                } else {
                    keyCount++;
                    keys[j] = key;
                    values[j] = val;
                }
                modificationCount++;
                return null;
            }
        }
    }

    // Returns true/false on whether a given key exists within the hash-table
    public boolean hasKey(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());
        // Start at the original hash value and probe until we find a spot where our key
        // is or hit a null element in which case our element does not exist.
        for (int i = offset, j = -1, x = 1; ; i = normalizeIndex(offset + probe(x++))) {
            // Ignore deleted cells, but record where the first index
            // of a deleted cell is found to perform lazy relocation later.
            if (keys[i] == TOMBSTONE) {
                if (j == -1) j = i;
                // We hit a non-null key, perhaps it's the one we're looking for.
            } else if (keys[i] != null) {
                // The key we want is in the hash-table!
                if (keys[i].equals(key)) {
                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster. This is called lazy deletion/relocation.
                    if (j != -1) {
                        // Swap the key-value pairs of positions i and j.
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                    }
                    return true;
                }
                // Key was not found in the hash-table :/
            } else return false;
        }
    }

    // Get the value associated with the input key.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exists.
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        // Start at the original hash value and probe until we find a spot where our key
        // is or we hit a null element in which case our element does not exist.
        for (int i = offset, j = -1, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // Ignore deleted cells, but record where the first index
            // of a deleted cell is found to perform lazy relocation later.
            if (keys[i] == TOMBSTONE) {

                if (j == -1) j = i;

                // We hit a non-null key, perhaps it's the one we're looking for.
            } else if (keys[i] != null) {

                // The key we want is in the hash-table!
                if (keys[i].equals(key)) {

                    // If j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that the next time we search for this key it will be
                    // found faster. This is called lazy deletion/relocation.
                    if (j != -1) {
                        // Swap key-values pairs at indexes i and j.
                        keys[j] = keys[i];
                        values[j] = values[i];
                        keys[i] = TOMBSTONE;
                        values[i] = null;
                        return values[j];
                    } else {
                        return values[i];
                    }
                }

                // Element was not found in the hash-table :/
            } else return null;
        }
    }

    // Removes a key from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exists.
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Null key");

        setupProbing(key);
        final int offset = normalizeIndex(key.hashCode());

        // Starting at the original hash probe until we find a spot where our key is
        // or we hit a null element in which case our element does not exist.
        for (int i = offset, x = 1; ; i = normalizeIndex(offset + probe(x++))) {

            // Ignore deleted cells
            if (keys[i] == TOMBSTONE) continue;

            // Key was not found in hash-table.
            if (keys[i] == null) return null;

            // The key we want to remove is in the hash-table!
            if (keys[i].equals(key)) {
                keyCount--;
                modificationCount++;
                V oldValue = values[i];
                keys[i] = TOMBSTONE;
                values[i] = null;
                return oldValue;
            }
        }
    }

    // Return a String view of this hash-table.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null && keys[i] != TOMBSTONE) sb.append(keys[i] + " => " + values[i] + ", ");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Iterator<K> iterator() {
        // Before the iteration begins record the number of modifications
        // done to the hash-table. This value should not change as we iterate
        // otherwise a concurrent modification has occurred :0
        final int MODIFICATION_COUNT = modificationCount;

        return new Iterator<K>() {
            int index, keysLeft = keyCount;

            @Override
            public boolean hasNext() {
                // The contents of the table have been altered
                if (MODIFICATION_COUNT != modificationCount) throw new ConcurrentModificationException();
                return keysLeft != 0;
            }

            // Find the next element and return it
            @Override
            public K next() {
                while (keys[index] == null || keys[index] == TOMBSTONE) index++;
                keysLeft--;
                return keys[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
