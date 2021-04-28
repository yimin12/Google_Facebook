package Contest.NineChapter.HighFreq.String;

import DataStructure.Design.Tree.Trie;

/**
 * Description
 * Given k strings, find the longest common prefix (LCP).
 *
 * Example
 * Example 1:
 * 	Input:  "ABCD", "ABEF", "ACEF"
 * 	Output:  "A"
 *
 *
 * Example 2:
 * 	Input: "ABCDEFG", "ABCEFG" and "ABCEFA"
 * 	Output:  "ABC"
 */
public class LongestCommonPrefix {


    public String longestCommonPrefixI(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs){
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)){
                low = middle + 1;
            }else{
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++){
            if (!strs[i].startsWith(str1)){
                return false;
            }
        }
        return true;
    }

    /**
     * Trie method to handle common prefix
     */
    public class TrieNode{
        char c ;
        TrieNode[] children = new TrieNode[256];
        public TrieNode(){}
        public TrieNode(char c){
            this.c = c;
        }
    }

    public TrieNode build(String[] ss){
        TrieNode root = new TrieNode();
        for(String s : ss){
            if(s.length() == 0){
                return null;
            }
            TrieNode cur = root;
            for(char c : s.toCharArray()){
                if(cur.children[c - 'A'] == null){
                    cur.children[c - 'A'] = new TrieNode(c);
                }
                cur = cur.children[c - 'A'];
            }
        }
        return root;
    }
    public String longestCommonPrefix(String[] ss){
        StringBuilder sb = new StringBuilder();
        if(ss == null || ss.length == 0) return sb.toString();
        TrieNode root = build(ss);
        while(count(root.children) == 1){
            for(TrieNode node : root.children){
                if(node != null){
                    sb.append(node.c);
                    root = node;
                    break;
                }
            }
        }
        return sb.toString();
    }

    private int count(TrieNode[] next){
        int res = 0;
        for(TrieNode node : next){
            if(node != null){
                res ++;
            }
        }
        return  res;
    }
}
