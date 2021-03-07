package Algorithm.Graph.PlayGraph.Traverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/1/9 23:25
 *   @Description :
 *     Leetcode 773  数字华容道
 */
public class SlidingPuzzle {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int rows, cols;
    private String target;

    public int slidingPuzzle(int[][] board){
        if(board == null || board.length == 0 || board[0].length == 0){
            return -1;
        }
        rows = board.length;
        cols = board[0].length;
        target = generateTarget(rows * cols);
        Queue<String> queue = new LinkedList<String>();
        HashMap<String, Integer> visited = new HashMap<>(); // record the path
        String initialState = boradToString(board);
        if(initialState.equals(target)) return 0;

        queue.add(initialState);
        visited.put(initialState, 0);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            ArrayList<String> nexts = getNexts(cur);
            for(String next : nexts){
                if(!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if(next.equals(target)){
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getNexts(String s){
        int[][] cur = stringToBoard(s);
        int zero; // find the place of zero
        for(zero = 0; zero < rows * cols; zero++){
            if(cur[zero/cols][zero%cols] == 0) break;
        }
        ArrayList<String> res = new ArrayList<>();
        int zx = zero / cols, zy = zero % cols;
        for(int d = 0; d < dirs.length; d++){
            int neiX = zx + dirs[d][0], neiY = zy + dirs[d][1];
            if(inArea(neiX, neiY)){
                swap(cur, zx, zy, neiX, neiY);
                res.add(boradToString(cur));
                swap(cur, zx, zy, neiX, neiY); // reset
            }
        }
        return res;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2){
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    private int[][] stringToBoard(String s){
        int[][] board = new int[rows][cols];
        for(int i = 0; i < rows * cols; i++){
            board[i/cols][i%cols] = s.charAt(i) - '0';
        }
        return board;
    }

    private String boradToString(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private String generateTarget(int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n; i++){
            sb.append(i);
        }
        sb.append(0);
        target = sb.toString();
        return target;
    }

    public static void main(String[] args) {
        int[][] board = {{0,2,3}, {1,4,6}, {7,5,8}};
        int[][] board1 = {{0,1,3}, {2, 4, 5}};
        SlidingPuzzle sp = new SlidingPuzzle();
        int i = sp.slidingPuzzle(board);
        System.out.println(i);
    }
}
