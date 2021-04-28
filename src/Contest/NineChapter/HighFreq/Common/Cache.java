package Contest.NineChapter.HighFreq.Common;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the common cache strategy
 */
public class Cache {

    /**
     * LRU implementation
     */
    class Node<K, V> {
        public Node<K, V> next, prev;
        public K key;
        public V value;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
        public void update(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public class LRUCache<K, V>{

        private final int limit;
        private Node<K, V> head, tail;
        private Map<K, Node<K, V>> map; // mapping key to corresponding node

        public LRUCache(int limit){
            this.limit = limit;
            this.map = new HashMap<>();
        }

        // set K -> remove the node and append to the head
        public void set(K key, V value){
            Node<K, V> node = null;
            if(map.containsKey(key)){
                node = map.get(key);
                node.value = value;
                remove(node);
            } else if(map.size() < limit){
                node = new Node<K, V>(key, value);
            } else {
                // eviction rule
                node = tail;
                remove(tail);
                node.update(key, value);
            }
            append(node); // append to head
        }

        private Node<K, V> append(Node<K, V> node){
            map.put(node.key, node);
            if(head == null){
                head = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            return node;
        }

        private Node<K, V> remove(Node<K, V> node){
            map.remove(node.key);
            if(node.prev != null){
                node.prev.next = node.next;
            }
            if(node.next != null){
                node.next.prev = node.prev;
            }
            if(node == head){
                head = head.next;
            }
            if(node == tail){
                tail = tail.prev;
            }
            node.prev = node.next = null;
            return node;
        }

        private int size(){
            return map.size();
        }

        // get K
        public V get(K key){
            Node<K, V>  node = map.get(key);
            if(node == null) return null;
            remove(node);
            append(node);
            return node.value;
        }
    }

    /**
     * LFU Implementation
     */
    public class LFUClass<K, V>{
        Map<K, Node<K, V>> values;
        Map<K, Integer> frequency;
        Map<Integer, LRUCache<K, V>> lru;
        private final int limit;
        int min = -1;

        public LFUClass(int limit){
            if(limit < 0) throw new IllegalArgumentException("limit should greater than zero");
            this.limit = limit;
            values = new HashMap<>();
            frequency = new HashMap<>();
            lru = new HashMap<>();
            lru.put(1, new LRUCache<>(limit));
        }

        public V get(K key){
            if(!values.containsKey(key)){
                return null;
            }
            int count = frequency.get(key);
            frequency.put(key, count + 1);
            lru.get(count).remove(values.get(key));
            if(count == min && lru.get(count).size() == 0){
                min ++;
            }
            lru.putIfAbsent(count + 1, new LRUCache<>(limit));
            lru.get(count + 1).append(values.get(key));
            return values.get(key).value;
        }

        public void set(K key, V value){
            Node<K, V> node = null;
            // update its values and its frequency
            if(values.containsKey(key)){
                node = values.get(key);
                node.update(key, value);
                values.put(key, node);
                return;
            }
            if(values.size() >= limit){
                node = lru.get(min).tail; // discard node
                lru.get(min).remove(node);
                values.remove(node.key);
            }
            node.update(key, value);
            values.put(key,node);
            frequency.put(key, 1);
            min = 1;
            lru.get(1).append(node);
        }
    }

}
