import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/2/28 20:52
 *   @Description :
 *
 */
public class Solution {
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> res = new ArrayList<Integer>();
        if(input == null || input.size() <= 1) return res;
        res = findCommon(input.get(0), input.get(1));
        for(int i = 2; i < input.size(); i++){
            res = findCommon(input.get(i), res);
        }
        return res;
    }

    private List<Integer> findCommon(List<Integer> a, List<Integer> b){
        List<Integer> cur = new ArrayList<Integer>();
        if(a == null || b == null || a.size() * b.size() == 0) return cur;
        int a_index = 0, b_index = 0;
        while(a_index < a.size() && b_index < b.size()){
            if(a.get(a_index) < b.get(b_index)){
                a_index ++;
            } else if(a.get(a_index) > b.get(b_index)){
                b_index ++;
            } else {
                cur.add(a.get(a_index));
                a_index++;
                b_index++;
            }
        }
        return cur;
    }


    public int maxSumDivK(int[] nums,int k){
        if(k == 0) return -1;
        int[] dp = new int[k];
        for(int num : nums){
            int[] tmp = Arrays.copyOf(dp, k);
            for(int i =0 ; i < k; i ++){
                dp[(num + dp[i]) % k] = Math.max(dp[(num + dp[i]) % k], num + dp[i]);
            }
        }
        return dp[0];
    }

    public int[] mergeSort(int[] array){
        if(array == null){
            return array;
        }
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int[] helper, int left, int right){
        if(left > right){
            return;
        }
        int mid = left + (right - left)/2;
        // divide
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1 , right);
        // conquer
        merge(array, helper, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right){
        for(int i = left; i <= right; i++){
            helper[i] = array[i];
        }
        int leftIndex = left, rightIndex = mid + 1;
        while(leftIndex <= mid && rightIndex <= right){
            if(helper[leftIndex] < helper[rightIndex]){
                array[left++] = helper[leftIndex++];
            } else {
                array[left++] = helper[rightIndex++];
            }
        }
        while(leftIndex <= mid){
            array[left++] = helper[leftIndex++];
        }
        // rightIndex is already in their position
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        for(int i = 0; i < nums.length - 2; i ++){
            List<List<Integer>> cur = twoSum(nums, i+1, nums.length - 1, -nums[i]);
            for(int j = 0; j < cur.size(); j ++){
                cur.get(j).add(nums[i]);
                Collections.sort(cur.get(j));
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int left, int right, int target){
        // because it is unsorted
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = left; i <= right; i ++){
            if(set.contains(target - nums[i])){
                List<Integer> cur = new ArrayList<>();
                cur.add(nums[i]);
                cur.add(target - nums[i]);
                res.add(cur);
            }
            set.add(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<Integer> one = Arrays.asList(0,2,2,2,4,6,7,7,9,9,10,12);
        List<Integer> two = Arrays.asList(0,0,0,1);
        List<Integer> three = Arrays.asList(1,3,5,5,6,7,9,11,11);
        List<Integer> four = Arrays.asList(0,0,2);
        List<List<Integer>> input = Arrays.asList(one, two, three, four);
        List<Integer> res = s.commonElementsInKSortedArrays(input);
        System.out.println(res.toString());
    }
}
