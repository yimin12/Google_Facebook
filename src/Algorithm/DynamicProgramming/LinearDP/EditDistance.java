package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/8 0:30
 *   @Description :
        Given two wordsword1_and_word2, find the minimum number of operations required to convertword1_to_word2.
        You have the following 3 operations permitted on a word:
        Insert a character
        Delete a character
        Replace a character
        Example 1:
        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation:
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')
 */
public class EditDistance {

    public int editDistance(String one, String two){
        if(one == null || two == null){
            return -1;
        }
        int[][] distance = new int[one.length() + 1][two.length() + 1];
        for(int i = 0; i <= one.length(); i++){
            for(int j = 0; j <= two.length(); j++){
                if(i == 0){
                    distance[i][j] = j;
                } else if(j == 0){
                    distance[i][j] = i;
                } else if(one.charAt(i - 1) == two.charAt(j - 1)){
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = Math.min(distance[i-1][j] + 1, distance[i][j - 1] + 1);
                    distance[i][j] = Math.min(distance[i][j], distance[i-1][j-1] + 1);
                }
            }
        }
        return distance[one.length()][two.length()];
    }
}
