package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 21:08
 *   @Description :
 *
 */

/**
 * 给定一个路径数组 paths，表示一张图。paths[i]==j 代表城市 i 连向城市 j，如果 paths[i]==i， 则表示 i 城市是首都，一张图里只会有一个
 * 首都且图中除首都指向自己之 外不会有环。 例如， paths=[9,1,4,9,0,4,8,9,0,1]， 由数组表示的图可以知道，城市 1 是首都，所以距离为 0，
 * 离首都距离为 1 的城市只有城 市 9，离首都距离为 2 的城市有城市 0、3 和 7，离首都距离为 3 的城市有城市 4 和 8， 离首都 距离为 4 的城市
 * 有城市 2、5 和 6。所以距离为 0 的城市有 1 座，距离为 1 的 城市有 1 座，距离 为 2 的城市有 3 座，距离为 3 的城市有 2 座，距离为 4
 * 的城市有 3 座。那么统计数组为nums=[1,1,3,2,3,0,0,0,0,0]，nums[i]==j 代表距离为 i 的城市有 j 座。要求实现一个 void 类型的函 数，
 * 输入一个路径数组 paths，直接在原数组上调整， 使之变为 nums 数组，即 paths=[9,1,4,9,0,4,8,9,0,1]经过这个函数处理后变成
 * [1,1,3,2,3,0,0,0,0,0]。 【要求】 如果 paths 长度为 N，请达到时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 */
public class PathsToNums {



    // replace all value inplace, so that the space complexity is O(1)
    public static void solution(int[] paths){
        if(paths == null || paths.length == 0){
            return;
        }
        // step1: change it to negative distances
        pathsToDistance(paths);
        distanceToResult(paths);
    }

    private static void pathsToDistance(int[] paths){
        int capital = 0;
        for(int i = 0; i != paths.length; i ++){
            if(paths[i] == i){
                capital = i;
            } else if(paths[i] > -1){
                int cur = paths[i];
                paths[i] = -1; // you mark the entrance as -1
                int pre = i;
                while(paths[cur] != cur){
                    if(paths[cur] > -1){
                        int next = paths[cur];
                        paths[cur] = pre;
                        pre = cur;
                        cur = next;
                    } else {
                        break;
                    }
                }
                // you find the way to capital
                int value = paths[cur] == cur ? 0 : paths[cur];
                while(paths[pre] != -1){
                    int backPre = paths[pre];
                    paths[pre] = --value;
                    pre = backPre;
                }
                paths[pre] = --value;
            }
        }
        paths[capital] = 0;
    }

    public static void distanceToResult(int[] paths){
        for(int i = 0; i < paths.length;i ++){
            int index = paths[i];
            if(index < 0){
                paths[i] = 0;
                while(true){
                    index = -index;
                    if(paths[index] > -1){
                        paths[index] ++;
                        break;
                    } else {
                        int nextIndex = paths[index];
                        paths[index] = 1;
                        index = nextIndex;
                    }
                }
            }
        }
        paths[0] = 1;
    }


    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] paths = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
        printArray(paths);
        solution(paths);
        printArray(paths);

    }



}
