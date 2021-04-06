package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 0:45
 *   @Description :
 *
 */
interface MountainArray {
    public int get(int index);
    public int length();
}

public class FindInMountainArray {

    // trinary sort
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length(), peak = 0; // the index of peak
        int left = 0, right = n - 1;
        // find the peak first
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(mountainArr.get(mid) < mountainArr.get(mid + 1)){
                left = peak = mid + 1; // should record the peak
            } else {
                right = mid;
            }
        }
        // find the target in the left of peak
        left = 0;
        right = peak;
        while(left <= right) {
            int mid = left + ((right - left) >> 1);
            if(mountainArr.get(mid) < target){
                left = mid + 1;
            } else if (mountainArr.get(mid) > target){
                right = mid -1;
            } else {
                return mid;
            }
        }
        // find the target in the right of peak
        left = peak;
        right = n - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(mountainArr.get(mid) > target){
                left = mid + 1;
            } else if(mountainArr.get(mid) < target){
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
