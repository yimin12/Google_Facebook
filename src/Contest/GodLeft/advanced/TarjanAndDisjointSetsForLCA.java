package Contest.GodLeft.advanced;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 22:09
 *   @Description :
 *
 */
public class TarjanAndDisjointSetsForLCA {


    public static Node[] tarJanQuery(Node head, Query[] queries){
        HashMap<Node, LinkedList<Node>> queryMap = new HashMap<>();
        HashMap<Node, LinkedList<Integer>> indexMap = new HashMap<>();
        HashMap<Node, Node> ancestorMap = new HashMap<>();
        UnionFindSet<Node> sets = new UnionFindSet<Node>(getAllNodes(head));
        Node[] res = new Node[queries.length];
        setQueriesAndSetEasyAnswers(queries, res, queryMap, indexMap);
        setAnswers(head, res, queryMap, indexMap, ancestorMap, sets);
        return res;
    }

    private static void setAnswers(Node head, Node[] res, HashMap<Node, LinkedList<Node>> queryMap, HashMap<Node, LinkedList<Integer>> indexMap, HashMap<Node, Node> ancestorMap, UnionFindSet<Node> sets) {
        if(head == null) return;
        setAnswers(head.left, res, queryMap, indexMap, ancestorMap, sets);
        sets.union(head.left, head);
        ancestorMap.put(sets.findHead(head), head);
        setAnswers(head.right, res, queryMap, indexMap, ancestorMap, sets);
        sets.union(head.right, head);
        ancestorMap.put(sets.findHead(head), head);
        LinkedList<Node> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);
        Node node = null;
        Node nodeFather = null;
        int index = 0;
        while (nList != null && !nList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findHead(node);
            if (ancestorMap.containsKey(nodeFather)) {
                res[index] = ancestorMap.get(nodeFather);
            }
        }
    }

    private static void setQueriesAndSetEasyAnswers(Query[] queries, Node[] res, HashMap<Node, LinkedList<Node>> queryMap, HashMap<Node, LinkedList<Integer>> indexMap) {
        Node o1 = null, o2 = null;
        for(int i = 0; i != res.length; i ++){
            o1 = queries[i].o1;
            o2 = queries[i].o2;
            if(o1 == o2 || o1 == null || o2 == null){
                res[i] = o1 != null ? o1 : o2;
            } else {
                if(!queryMap.containsKey(o1)){
                    queryMap.put(o1, new LinkedList<Node>());
                    indexMap.put(o1, new LinkedList<Integer>());
                }
                if(!queryMap.containsKey(o2)){
                    queryMap.put(o2, new LinkedList<Node>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);
                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);  // query the question in the ith question
            }
        }
    }

    private static List<Node> getAllNodes(Node root){
        List<Node> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private static void traverse(Node head, List<Node> res){
        if(head == null){
            return;
        }
        res.add(head);
        traverse(head.left, res);
        traverse(head.right, res);
    }

    public static class Element<V>{
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V>{
        public Map<V, Element<V>> elementMap;
        public Map<Element<V>, Element<V>> parent;
        public Map<Element<V>, Integer> rank;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            parent = new HashMap<>();
            rank = new HashMap<>();
            for(V value : list){
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element); // mapping value to reference
                parent.put(element, element);
                rank.put(element, 1);
            }
        }

        private Element<V> findHead(Element<V> element){
            Deque<Element<V>> path = new LinkedList<>();
            while(element != parent.get(element)){
                path.push(element);
                element = parent.get(element);
            }
            // common trick for no parent pointer in tree, compress path
            while(!path.isEmpty()){
                parent.put(path.pop(),element);
            }
            return element;
        }

        public V findHead(V value){
            return elementMap.containsKey(value) ? findHead(elementMap.get(value)).value : null;
        }

        public boolean sameSet(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> a_root = findHead(elementMap.get(a));
                Element<V> b_root = findHead(elementMap.get(b));
                if(a_root != b_root){
                    int aR_rank = rank.get(a_root);
                    int bR_rank = rank.get(b_root);
                    if(aR_rank >= bR_rank){
                        parent.put(b_root, a_root); // b_root pointing to a_root
                        rank.put(a_root, aR_rank + bR_rank);
                        rank.remove(bR_rank);
                    } else {
                        parent.put(a_root, b_root);
                        rank.put(b_root, aR_rank + bR_rank);
                        rank.remove(a_root);
                    }
                }
            }
        }
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Query {
        public Node o1;
        public Node o2;

        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        printTree(head);
        System.out.println("===============");

        Query[] qs = new Query[7];
        qs[0] = new Query(head.left.right, head.right.left);
        qs[1] = new Query(head.left.left, head.left);
        qs[2] = new Query(head.right.left, head.right.right.left);
        qs[3] = new Query(head.left.left, head.right.right);
        qs[4] = new Query(head.right.right, head.right.right.left);
        qs[5] = new Query(head, head);
        qs[6] = new Query(head.left, head.right.right.left);

        Node[] ans = tarJanQuery(head, qs);

        for (int i = 0; i != ans.length; i++) {
            System.out.println("o1 : " + qs[i].o1.value);
            System.out.println("o2 : " + qs[i].o2.value);
            System.out.println("ancestor : " + ans[i].value);
            System.out.println("===============");
        }

    }

}
