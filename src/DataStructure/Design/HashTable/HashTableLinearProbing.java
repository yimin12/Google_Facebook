package DataStructure.Design.HashTable;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 14:55
 *   @Description :
 *
 */
public class HashTableLinearProbing<K, V> extends HashTableOpenAddressingBase<K, V> {

    /**
     * This is the linear constant used in the linear probing, it can be any positive prime number. The table capacity will be adjusted so that
     * teh GCD (capacity, LINEAR_CONSTANT) = 1 and all buckets can be probed;
     */
    private static final int LINEAR_CONSTANT = 17;

    public HashTableLinearProbing() {
    }

    public HashTableLinearProbing(int capacity) {
        super(capacity);
    }

    public HashTableLinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    protected void setupProbing(K key) {

    }

    @Override
    protected int probe(int x) {
        return LINEAR_CONSTANT * x;
    }

    @Override
    protected void adjustCapacity() {
        // make capacity and constant's dominator is 1
        while(gcd(LINEAR_CONSTANT, capacity) != 1){
            capacity++;
        }
    }
}
