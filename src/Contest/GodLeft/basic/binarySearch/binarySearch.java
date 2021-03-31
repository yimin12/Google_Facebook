package Contest.GodLeft.basic.binarySearch;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/27 23:59
 *   @Description :
 *
 */
public class binarySearch {

    public static boolean binarySearch(int[] sorted, int num){
        if(sorted == null || sorted.length == 0){
            return false;
        }
        int left = 0, right = sorted.length - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(sorted[mid] == num){
                return true;
            } else if(sorted[mid] < num){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return sorted[left] == num;
    }

    public static int findFirstValue(int[] sorted, int value){
        if(sorted == null || sorted.length == 0){
            return -1;
        }
        int left = 0, right = sorted.length - 1;
        int index = -1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(sorted[mid] >= value){
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static int findLastSmaller(int[] sorted, int value){
        if(sorted == null || sorted.length == 0){
            return -1;
        }
        int left = 0, right = sorted.length - 1;
        while(left < right - 1){
            int mid = left + ((right - left) >> 1);
            if(sorted[mid] >= value){
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if(sorted[right] < value){
            return right;
        } else if(sorted[left] < value){
            return left;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findLastSmaller(new int[]{6}, 5));
    }
}

