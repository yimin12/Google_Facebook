package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/1 23:04
 *   @Description :
 *
 */
public class ZumaGame {

    public int findMinStep(String board, String hand){
        if(board == null || board.length() == 0) return 0;
        if(hand == null || hand.length() == 0) return -1;
        int[] dict = new int[26];
        for(char c : hand.toCharArray()){
            dict[c-'A'] ++;
        }
        return find(board, dict);
    }

    // handle the famous case
    private int find(String board, int[] dict){
        if(board.isEmpty()) return 0;
        int res = 2 * board.length() + 1;
        for(int i = 0; i < board.length();){
            int j = i ++;
            while(i < board.length() && board.charAt(i) == board.charAt(j)) i ++;
            int inc = 3 - i + j;
            if(i - j == 5) continue;
            if(dict[board.charAt(j) - 'A'] >= inc){
                int used = (inc <= 0) ? 0 : inc;
                dict[board.charAt(j) - 'A'] -= used;
                int temp = find(board.substring(0, j) + board.substring(i), dict);
                if(temp >= 0) res = Math.min(res, used + temp);
                dict[board.charAt(j) - 'A'] += used;
            }
        }
        return res == (2 * board.length() + 1) ? -1 : res;
    }
}
