package DataStructure.Design.HashTable;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 14:55
 *   @Description :
 *
 */
public class HashTableQuadraticProbing<K, V> extends HashTableOpenAddressingBase<K, V> {

    /**
     * An implementation of a hash-table using open addressing with quadratic probing as a collision
     * resolution method.
     *
     * <p>In this implementation we are using the following probing function: H(k, x) = h(k) + f(x) mod
     * 2^n
     *
     * <p>Where h(k) is the hash for the given key, f(x) = (x + x^2) / 2 and n is a natural number. We
     * are using this probing function because it is guaranteed to find an empty cell (i.e it generates
     * all the numbers in the range [0, 2^n) without repetition for the first 2^n numbers).
     */

    public HashTableQuadraticProbing() {
    }

    public HashTableQuadraticProbing(int capacity) {
        super(capacity);
    }

    public HashTableQuadraticProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    // Given a number that finds the next power of two above
    private static int nextPowerOfTwo(int n){
        return Integer.highestOneBit(n) << 1;
    }

    @Override
    protected void setupProbing(K key) {
        // no need to set up for linear or quadratic probing
    }

    @Override
    protected int probe(int x) {
        // Quadratic probing function (x^2+x)/2
        return (x * x + x) >> 1;
    }

    // We should override increaseCapacity as well for quadratic probing
    @Override
    protected void increaseCapacity() {
        capacity = nextPowerOfTwo(capacity);
    }

    @Override
    protected void adjustCapacity() {
        int pow2 = Integer.highestOneBit(capacity);
        if(capacity == pow2){
            return;
        }
        increaseCapacity();
    }
}
