package com.nys.study.spring.springbootstudy.algorithm.daily_代码随想录;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 704. 二分查找
 * @date 2024/5/12 16:07
 */
public class Daily01_Array_704 {

    public static void main(String[] args) {
        //nums = [-1,0,3,5,9,12], target = 9
        Daily01_Array_704 test = new Daily01_Array_704();
        int[] nums = new int[]{-1,0,3,5,9,12};
        System.out.println(test.search(nums, 2));
    }

    /*
        二分查找，前提：数组升序
        时间复杂度分析：
     */
    public int search(int[] nums, int target) {
        int midPoint = nums.length/2;
        int start = 0;
        int end = nums.length-1;
        while (start <= end){
            if (nums[midPoint] == target){
                return midPoint;
            }
            if (nums[midPoint] > target){
                end = midPoint - 1;
            }
            if (nums[midPoint] < target){
                start = midPoint + 1;
            }
            midPoint = (start + end)/2;
        }
        return -1;
    }

}
