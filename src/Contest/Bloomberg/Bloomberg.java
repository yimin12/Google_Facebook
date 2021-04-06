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
        String key;
        int value;
        Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private Comparator<Pair> pairComparator = new Comparator<Pair>() {
        public int compare(Pair left, Pair right) {
            if (left.value != right.value) {
                return left.value - right.value;
            }
            return right.key.compareTo(left.key);
        }
    };
    // O(n + nlogk);
    public String[] topKFrequentWords(String[] words, int k) {
        if (k == 0) {
            return new String[0];
        }

        HashMap<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (counter.containsKey(word)) {
                counter.put(word, counter.get(word) + 1);
            } else {
                counter.put(word, 1);
            }
        }

        PriorityQueue<Pair> Q = new PriorityQueue<Pair>(k, pairComparator);
        for (String word : counter.keySet()) {
            Pair peak = Q.peek();
            Pair newPair = new Pair(word, counter.get(word));
            if (Q.size() < k) {
                Q.add(newPair);
            } else if (pairComparator.compare(newPair, peak) > 0) {
                Q.poll();
                Q.add(newPair);
            }
        }

        String[] result = new String[k];
        int index = k - 1;
        while (!Q.isEmpty()) {
            result[index --] = Q.poll().key;
        }
        return result;
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

    // ---------------------------------------------------------------------------------Flatten a Multilevel Doubly Linked List
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

    // -----------------------------------------------------------------------------------word search
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++){
                if(exist(board, i, j, word, 0))
                    return true;
            }
        return false;
    }
    private boolean exist(char[][] board, int i, int j, String word, int ind){
        if(ind == word.length()) return true;
        if(i > board.length-1 || i <0 || j<0 || j >board[0].length-1 || board[i][j]!=word.charAt(ind))
            return false;
        board[i][j]='*';
        boolean result =    exist(board, i-1, j, word, ind+1) ||
                exist(board, i, j-1, word, ind+1) ||
                exist(board, i, j+1, word, ind+1) ||
                exist(board, i+1, j, word, ind+1);
        board[i][j] = word.charAt(ind);
        return result;
    }

    // -----------------------------------------------------------------------------------merge Interval
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0 || intervals[0].length == 0){
            return intervals;
        }
        Arrays.sort(intervals, (a, b)->a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<List<Integer>> res = new ArrayList<>();
        int curStart = 0, curEnd = 0;
        for(int i = 0; i < intervals.length; i ++){
            if(i == 0){
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
            } else {
                if(intervals[i][0] <= curEnd){
                    curEnd = Math.max(intervals[i][1], curEnd);
                } else {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(curStart);
                    cur.add(curEnd);
                    res.add(cur);
                    curStart = intervals[i][0];
                    curEnd = intervals[i][1];
                }
            }
        }
        List<Integer> cur = new ArrayList<>();
        cur.add(curStart);
        cur.add(curEnd);
        res.add(cur);
        int[][] merge = new int[res.size()][2];
        for(int i = 0; i <merge.length; i ++){
            cur = res.get(i);
            merge[i][0] = cur.get(0);
            merge[i][1] = cur.get(1);
        }
        return merge;
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
        // We will use sizes to understand which list's nodes should be frozen for a while.
        int s1 = size(l1);
        int s2 = size(l2);
        ListNode resHead = null;
        ListNode n = null;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            int v2 = 0;
            if (s1 >= s2) {
                v1 = l1 != null ? l1.val : 0;
                l1 = l1.next;
                s1--;
            }
            // Comparing with s1 + 1 since s1 might be decremented previously
            if (s2 >= s1 + 1) {
                v2 = l2 != null ? l2.val : 0;
                l2 = l2.next;
                s2--;
            }
            // Creating the resulting list in the reversed order.
            n = new ListNode(v1 + v2);
            n.next = resHead;
            resHead = n;
        }
        int carry = 0;
        resHead = null;
        // Now, let's perform the normalization.
        while (n != null) {
            n.val += carry;
            if (n.val >= 10) {
                n.val = n.val % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            ListNode buf = n.next;
            n.next = resHead;
            resHead = n;
            n = buf;
        }
        if (carry > 0) {
            n = new ListNode(1);
            n.next = resHead;
            resHead = n;
        }
        return resHead;
    }

    private int size(ListNode l) {
        int s = 0;
        while (l != null) {
            l = l.next;
            s++;
        }
        return s;
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
    // ------------------------------------------------------------------Find duplicate numbers
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length <= 1) return 0;
        // try to find the circle in the array
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // find the entry point
        fast = 0;
        while(slow != fast){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
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

    // ------------------------------------------------------------------Remove duplicate letters with specific k adjacent, 1D candicrush
    public String removeDuplicates(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
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

    // ------------------------------------------------------------------ Missing element in sorted array
    public int missingElement(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int missing = nums[right] - nums[left] - (right - left);
        if (k > missing) {
            return nums[right] + k - missing;
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            missing = nums[mid] - nums[left] - (mid - left);
            if (k > missing) {
                k -= missing;
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return nums[left] + k;
    }

    // ------------------------------------------------------------------ guess a number higher or lower
    public int guessNumber(int n) {
        int i = 1, j = n;
        while(i < j) {
            int mid = i + (j - i) / 2;
            if(guess(mid) == 0) {
                return mid;
            } else if(guess(mid) == 1) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }
    public int guess(int x){
        return 0;
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
    Map<Integer, Player> players;
    TreeSet<Player> scores;
    public Leaderboard() {
        players = new HashMap<>();
        scores = new TreeSet<Player>((a, b) ->{
            return b.score == a.score ? a.id - b.id : b.score - a.score;
        });
    }
    // Log(n) time
    public void addScore(int playerId, int score) {
        Player cur;
        if(players.containsKey(playerId)){
            cur = players.get(playerId);
            scores.remove(cur);
            cur.score += score;
            scores.add(cur);
        } else {
            cur = new Player(playerId, score);
            players.put(playerId, cur);
            scores.add(cur);
        }
    }

    // O(K) to iterate the tree map
    public int top(int K) {
        Iterator<Player> iterator = scores.iterator();
        int res = 0;
        while(K -- > 0 && iterator.hasNext()){
            res += iterator.next().score;
        }
        return res;
    }

    // O(logn) for remove in treeSet
    public void reset(int playerId) {
        Player cur = players.get(playerId);
        scores.remove(cur);
        cur.score = 0;
    }

    private class Player{
        int id, score;
        public Player(int id, int score){
            this.id = id;
            this.score = score;
        }
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
