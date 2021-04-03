package Contest.Bloomberg;

import DataStructure.AlgoUtils.TreeNode;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/2 23:08
 *   @Description :
 *
 */
public class Bloomberg {

    // ---------------------------------------------------------------------------------topKFrequentWords
    class Pair {
        String word;
        int frequency;
        public Pair(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
    public String[] topKFrequentWords(String[] words, int k) {
        // write your code here
        if(words == null || words.length == 0 || k == 0) {
            return new String[0];
        }
        PriorityQueue<Pair> q = new PriorityQueue<Pair>(k, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                if(a.frequency == b.frequency) {
                    return a.word.compareTo(b.word);
                }
                return b.frequency - a.frequency;
            }
        });
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++) {
            if(map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            }
            else {
                map.put(words[i], 1);
            }
        }
        for(String word: map.keySet()) {
            q.offer(new Pair(word, map.get(word)));
        }
        String[] res = new String[k];
        for(int i = 0; i < k; i++) {
            res[i] = q.poll().word;
        }
        return res;
    }

    // ---------------------------------------------------------------------------------candyCrush
    public int[][] candyCrush(int[][] board){
        int m = board.length, n = board[0].length;
        boolean isEnd = false;
        while(!isEnd){
            isEnd = true;
            for(int i = 0; i < m; i ++){
                for(int j = 0; j < n; j ++){
                    int val = Math.abs(board[i][j]);
                    if(val == 0) continue;
                    if(j < n - 2 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val){
                        isEnd = false;
                        for(int k = j; k <= j + 2; k ++){
                            board[i][k] = -val;
                        }
                    }
                    if(i < m - 2 && Math.abs(board[i+1][j]) == val && Math.abs(board[i+2][j]) == val){
                        isEnd = false;
                        for(int k = i; k <= i + 2; k ++) board[k][j] = -val;
                    }
                }
            }
            if(!isEnd){
                for(int j = 0; j < n; j ++){
                    int tmp = m - 1;
                    for(int i = m - 1; i >= 0; i --){
                        if(board[i][j] > 0){
                            board[tmp --][j] = board[i][j];
                        }
                    }
                    for(int i = tmp; i >= 0; i --) board[i][j] = 0;
                }
            }
        }
        return board;
    }

    // ---------------------------------------------------------------------------------allPathsSourceTarget
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }

    // ---------------------------------------------------------------------------------flattenBinaryToList
    public void flattenBinaryToList(TreeNode root) {
        dfs(root);
    }
    private TreeNode dfs(TreeNode node){
        if(node == null) return node;
        TreeNode left = dfs(node.left);
        TreeNode right = dfs(node.right);
        if(left != null){
            left.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        if(right != null){
            return right;
        }
        if(left != null){
            return left;
        }
        return node;
    }

    public Node flatten(Node head) {
        if( head == null) return head;
        // Pointer
        Node p = head;
        while( p!= null) {
            /* CASE 1: if no child, proceed */
            if( p.child == null ) {
                p = p.next;
                continue;
            }
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            Node temp = p.child;
            // Find the tail of the child
            while( temp.next != null )
                temp = temp.next;
            // Connect tail with p.next, if it is not null
            temp.next = p.next;
            if( p.next != null )  p.next.prev = temp;
            // Connect p with p.child, and remove p.child
            p.next = p.child;
            p.child.prev = p;
            p.child = null;
        }
        return head;
    }

    // ---------------------------------------------------------------------------------subSetsWithDup
    public List<String> subSetsWithDup(String set){
        List<String> result = new ArrayList<String>();
        if(set == null || set.length() == 0) return null;
        char[] array = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        verticaldfsII(array, sb, 0, result);
        return result;
    }

    private void verticaldfsII(char[] array, StringBuilder sb, int index, List<String> result) {
        if(index == array.length) {
            result.add(sb.toString());
            return;
        }
        verticaldfsII(array, sb.append(array[index]), index + 1, result);
        sb.deleteCharAt(sb.length() - 1);
        while(index < array.length - 1 && array[index] == array[index + 1]) {
            index++;
        }
        verticaldfsII(array, sb, index + 1, result);
    }

    // ---------------------------------------------------------------------------------minPathSum
    public int minPathSum(int[][] A) {
        if(A == null || A.length == 0) return 0;
        int n = A.length, m = A[0].length;
        int f[][] = new int[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                if(i == 0 && j == 0) f[i][j] = A[i][j];
                else if(i == 0 || j == 0) f[i][j] = (((i == 0) ? f[i][j-1] : f[i-1][j]) + A[i][j]);
                else {
                    f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + A[i][j];
                }
            }
        }
        return f[n-1][m-1];
    }

    // ---------------------------------------------------------------------------------firstUniqueCharacter
    public char firstUniqueCharacter(String str){
        int[] count = new int[256];
        for (char ch : str.toCharArray()) {
            count[ch]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }
        return '0';
    }

    // -----------------------------------------------------------------------------------decodeString
    public String decodeString(String s) {
        if(s == null || s.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            stack.offerLast(c);
        }
        return dfsI(stack);
    }

    private String dfsI(Deque<Character> stack){
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while(!stack.isEmpty()){
            char c = stack.pollFirst();
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            } else if(c == '['){
                String s = dfsI(stack);
                for(int i = 0; i < num; i ++){
                    sb.append(s);
                }
                num = 0;
            } else if(c == ']'){
                break; // just return;
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }

    // -----------------------------------------------------------------------------------mergeKSortList
    public ListNode mergeKLists(List<ListNode> lists){
        if(lists == null || lists.size() == 0) return null;
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.size(), (a, b) -> a.val - b.val);
        for(int i = 0; i < lists.size(); i ++){
            if(lists.get(i) != null){
                pq.offer(lists.get(i));
            }
        }
        ListNode head = dummy;
        while(!pq.isEmpty()){
            ListNode cur = pq.poll();
            head.next = cur;
            head = cur;
            if(cur.next != null){
                pq.offer(cur.next);
            }
        }
        return dummy.next;
    }

    // -----------------------------------------------------------------------------------kthMissingPositiveNumber
    public int findKthPositive(int[] A, int k) {
        int left = 0, right = A.length, m;
        while (left < right) {
            m = (left + right) / 2;
            if (A[m] - 1 - m < k)
                left = m + 1;
            else
                right = m;
        }
        return left + k;
    }

    // -----------------------------------------------------------------------------------FindMountainArray
    int findInMountainArray(int target, List<Integer> A) {
        int n = A.size(), l, r, m, peak = 0;
        // find index of peak
        l  = 0;
        r = n - 1;
        while (l < r) {
            m = (l + r) / 2;
            if (A.get(m) < A.get(m + 1))
                l = peak = m + 1;
            else
                r = m;
        }
        // find target in the left of peak
        l = 0;
        r = peak;
        while (l <= r) {
            m = (l + r) / 2;
            if (A.get(m) < target)
                l = m + 1;
            else if (A.get(m) > target)
                r = m - 1;
            else
                return m;
        }
        // find target in the right of peak
        l = peak;
        r = n - 1;
        while (l <= r) {
            m = (l + r) / 2;
            if (A.get(m) > target)
                l = m + 1;
            else if (A.get(m) < target)
                r = m - 1;
            else
                return m;
        }
        return -1;
    }

    // -----------------------------------------------------------------------------------ThreeSum
    public List<List<Integer>> allTriples(int[] array, int target){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0; i < array.length - 2; i++) {
            if(i > 0 && array[i] == array[i-1]) {
                continue;
            }
            int left = i+1;
            int right = array.length - 1;
            while(left < right) {
                int temp = array[left] + array[right];
                if(temp + array[i] == target) {
                    result.add(Arrays.asList(array[i], array[left], array[right]));
                    left++;
                    while(left < right && array[left] == array[left - 1]) {
                        left++;
                    }
                } else if (temp + array[i] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    // -----------------------------------------------------------------------------------longestSubstringWithRepeatingCharacters
    public int longestSubstringWithRepeatingCharacters(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] map = new int[32];
        int i = 0, j = 0, res = 0;
        for(i = 0; i < s.length(); i ++){
            while(j < s.length() && !exist(map, s.charAt(j))){
                int x = s.charAt(j) / 8;
                int y = s.charAt(j) % 8;
                map[x] |= (1 << y);
                res = Math.max(res, j - i + 1);
                j ++;
            }
            map[s.charAt(i)/8] &= (~(1 << (s.charAt(i)%8)));
        }
        return res;
    }

    private boolean exist(int[] map, char c){
        return ((map[c/8] >> (c%8)) & 1) == 1;
    }

    // ------------------------------------------------------------------generateParenthesis
    public List<String> generateParenthesis(int k){
        List<String> res = new ArrayList<String>();
        char[] cur = new char[k * 2];
        helper(cur, k, k, 0, res);
        return res;
    }

    public void helper(char[] cur, int left, int right, int level, List<String> res) {
        // base case, termination condition
        if(left == 0 && right == 0) {
            res.add(new String(cur));
            return;
        }
        // recursion rule: when we can add a '(' ? whenever there is some '(' we can still use
        if(left > 0) {
            cur[level] = '(';
            helper(cur, left - 1, right, level + 1, res);
            // it looks like we do not handle the back tracking problem. the code itself actually suffices the above
            // two points and it already does the correct moving. The judgement is not determined by cur, and it is a 'write' operation
        }
        // when can we add '(' ? when there is more '(' than ')'
        if(right > left) {
            cur[level] = ')';
            helper(cur, left, right - 1, level + 1, res);
        }
    }

    // ------------------------------------------------------------------addTwoNumbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }

        return list.val == 0 ? list.next : list;
    }

    // ------------------------------------------------------------------random10from7
    public int random10() {
        for(;;) {
//			to generate a uniformly distributed 0-24 number
            int random = 7 * RandomSeven.random7() + RandomSeven.random7();
//			we only care about the first 21 numbers and should ignore
//			and try again for the number >= 21
            if(random < 40) {
                return random % 10;
            }
        }
    }

    // ------------------------------------------------------------------courseSchedule
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // corner case
        if(numCourses < 0){
            return new int[0];
        }
        // if there is no prerequisites requirement
        if(prerequisites == null){
            int[] result = new int[numCourses];
            for(int i = 0; i < numCourses; i++){
                result[i] = i;
            }
        }
        List[] courses = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            courses[i] = new ArrayList<Integer>();
        }
        // construct the map
        for(int i= 0; i < prerequisites.length; i++){
            // inverted index
            courses[prerequisites[i][1]].add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        int[] order = new int[numCourses];
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            order[count] = course;
            count++;
            int n = courses[course].size();
            for(int i = n - 1; i >= 0; i--){
                int nextCourse = (int)courses[course].get(i);
                degree[nextCourse]--;
                if(degree[nextCourse] == 0){
                    queue.add(nextCourse);
                }
            }
        }
        if(count == numCourses){
            return order;
        }
        return new int[0];
    }

    // ------------------------------------------------------------------wordBreak
    public List<String> wordBreak(String s, List<String> wordDict) {
        // memory search
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null){
            return res;
        }
        Set<String> set = toSet(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return wordBreakHelper(memo, s, set);
    }

    // each possibilities should only be traversed once, just calculate how many possibilities.
    // O(n^2)
    private List<String> wordBreakHelper(Map<String, List<String>> memo, String s, Set<String> set){
        if(memo.containsKey(s)){
            return memo.get(s);
        }
        List<String> res = new ArrayList<>();
        if(set.contains(s)){ // base case, contain the entire string
            res.add(s);
        }
        // try to split the given string
        for(int i = 1; i < s.length(); i ++){
            // contains break or not break
            String prefix = s.substring(0, i); // O(n)
            if(set.contains(prefix)){
                String suffix = s.substring(i, s.length());
                for(String su : wordBreakHelper(memo, suffix, set)){
                    res.add(prefix + " " + su);
                }
            }
        }
        memo.put(s, res);
        return res;
    }

    private Set<String> toSet(List<String> words){
        Set<String> set = new HashSet<>();
        for(String s : words){
            set.add(s);
        }
        return set;
    }

    // ------------------------------------------------------------------climbStairs
    public int climbStairs(int n) {
        if(n < 0)
            return 0;
        if(n == 1)
            return 1;
        int[] store = new int[n];
        store[0] = 1;
        store[1] = 2;
        for(int i = 2; i < n; ++i)
            store[i] = store[i-1] + store[i-2];
        return store[n-1];
    }

    // ------------------------------------------------------------------trapRainWater
    public int trapRainWater(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int res = 0;
        int left = 0, right = heights.length - 1, leftHeight = heights[left], rightHeight = heights[right];
        while(left < right){
            if(leftHeight < rightHeight){
                left ++;
                res += Math.max(0, leftHeight - heights[left]);
                leftHeight = Math.max(leftHeight, heights[left]);
            }else {
                right --;
                res += Math.max(0, rightHeight - heights[right]);
                rightHeight = Math.max(rightHeight, heights[right]);
            }
        }
        return res;
    }

    // ------------------------------------------------------------------isBST
    public boolean isBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if(root == null) return false;
        if(root.val < min || root.val > max) return false;
        return isBST(root.left, min, root.val - 1) && isBST(root.right, root.val + 1, max);
    }

    // ------------------------------------------------------------------alienDictionary
    public String alienDictionary(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);
        return topologicalSorting(graph);
    }

    private Map<Character, Set<Character>> constructGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        // create nodes
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }
        // create edges
        for (int i = 0; i <  words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }
        return graph;
    }

    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character u : graph.keySet()) {
            indegree.put(u, 0);
        }
        for (Character u : graph.keySet()) {
            for (Character v : graph.get(u)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }
        return indegree;
    }

    private String topologicalSorting(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getIndegree(graph);
        // as we should return the topo order with lexicographical order
        // we should use PriorityQueue instead of a FIFO Queue
        Queue<Character> queue = new PriorityQueue<>();
        for (Character u : indegree.keySet()) {
            if (indegree.get(u) == 0) {
                queue.offer(u);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character head = queue.poll();
            sb.append(head);
            for (Character neighbor : graph.get(head)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------numDupDigitsAtMostN
    public int numDupDigitsAtMostN(int N) {
        int count=0;
        for(int i=0;i<=N;i++) {
            String[] tr = String.valueOf(i).split("");
            Set set = new HashSet();
            for(String str : tr) {
                set.add(str);
            }
            if(set.size()!=tr.length){
                count++;
            }

        }
        return count;
    }

    // ------------------------------------------------------------------Remove duplicate letters
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) return "";
        int[] res = new int[26];
        boolean[] visited = new boolean[26];
        char[] ch = s.toCharArray();
        for(char c : s.toCharArray()){
            res[c - 'a'] ++;
        }
        Deque<Character> stack = new LinkedList<>();
        int i;
        for(char c : ch){
            i = c - 'a';
            res[i] --;
            if(visited[i]){
                continue;
            }
            while(!stack.isEmpty() && c < stack.peek() && res[stack.peek() - 'a'] != 0){
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            visited[i] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------Interval sections
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        ArrayList<int[]> result = new ArrayList<>();
        int n = a.length;
        int m = b.length;
        int i = 0;
        int j = 0;
        int left;int right;
        while(i < n && j < m){
            int low = Math.max(a[i][0],b[j][0]);
            int high = Math.min(a[i][1],b[j][1]);
            if(low <= high){
                result.add(new int[]{low,high});
            }
            if(a[i][1] < b[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    // ------------------------------------------------------------------ Sum of Nodes with Even-Valued Grandparent
    public int sumEvenGrandparent(TreeNode root) {
        if(root == null) return 0;
        return dfs(root, 1, 1);
    }
    private int dfs(TreeNode root, int parent, int grandparent){
        if(root == null) return 0;
        return dfs(root.left, root.val, parent) + dfs(root.right, root.val, parent) + (grandparent % 2 == 0 ? root.val : 0);
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

// -----------------------------------------------------------------------------------LRUCache
class LRUCache<K, V> {

     class Node<K, V> {
        Node<K, V> next;
        Node<K, V> prev;
        K key;
        V value;
        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
        void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // make if final for the pre-define size limit of the cache
    private final int limit;
    private Node<K, V> head;
    private Node<K, V> tail;
    private Map<K, Node<K, V>> map;

    public LRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<K, Node<K,V>>();
    }
    public void set(K key, V value) {
        // defaultly create an node when you use set
        Node<K, V> node = null;
        if(map.containsKey(key)) {
            // Case 1.0 if the key already in the cache, we need to update its value and move it to head(most recent position)
            node = map.get(key);
            node.value = value;
            remove(node); // remove the node becase it has been override
        } else if(map.size() < limit) {
            // Case 2.0 if the key is not in the cache, and there are still have space for new node
            node = new Node<K, V>(key, value);
        } else {
            // Case 3.0. if the key is not in cache and we do not have space any more, we need to evict the tail and reuse the node let it maintain the new key, value and put it to end
            node = tail;
            remove(node);
            node.update(key, value);

        }
        append(node);// append the node to the head
    }
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if(node == null) return null;
        remove(node);
        append(node);
        return node.value;
    }
    // return the appended node
    private Node<K, V> append(Node<K, V> node) {
        map.put(node.key, node);
        if(head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }
    // return the deleted node
    private Node<K, V> remove(Node<K, V> node){
        // Step 1: delete the element in map
        map.remove(node.key);
        // Step 2: delinked the node with others
        if(node.prev != null) {
            node.prev.next = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
        // Step 3: Maintain the head and tail pointers
        if(node == head) {
            head = head.next;
        }
        if(node == tail) {
            tail = tail.prev;
        }
        node.next = node.prev = null;
        return node;
    }
}

// -----------------------------------------------------------------------------------Leaderboard
class Leaderboard {
    Map<Integer, Integer> map; // id : score
    TreeMap<Integer, Integer> sorted; // score:freq of score
    public Leaderboard() {
        map = new HashMap<>();
        sorted = new TreeMap<>(Collections.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)) {
            map.put(playerId, score);
            sorted.put(score, sorted.getOrDefault(score, 0) + 1);
        } else {
            int preScore = map.get(playerId);
            sorted.put(preScore, sorted.get(preScore) - 1);
            if (sorted.get(preScore) == 0) {
                sorted.remove(preScore);
            }
            int newScore = preScore + score;
            map.put(playerId, newScore);
            sorted.put(newScore, sorted.getOrDefault(newScore, 0) + 1);
        }
    }

    public int top(int K) {
        int count = 0;
        int sum = 0;
        for (int key : sorted.keySet()) {
            int times = sorted.get(key);
            for (int i = 0; i < times; i++) {
                sum += key;
                count++;
                if (count == K) {
                    break;
                }
            }
            if (count == K) {
                break;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        int preScore = map.get(playerId);
        sorted.put(preScore, sorted.get(preScore) - 1);
        if (sorted.get(preScore) == 0) {
            sorted.remove(preScore);
        }
        map.remove(playerId);
    }
}

class RandomSeven{

    public static int random7(){
        return 0;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
