package com.nys.study.spring.springbootstudy.algorithm.daily_代码随想录;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 209.长度最小的子数组
 * @date 2024/5/27 19:35
 */
public class Daily04_Array_209 {

    public static void main(String[] args) {
        Daily04_Array_209 test = new Daily04_Array_209();
        // 2,3,1,2,4,3
        int[] arr = new int[]{2,3,1,2,4,3};
        System.out.println(test.minSubArrayLen(7, arr));
    }


    /*
        给定一个含有 n 个正整数的数组和一个正整数 target 。
        找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

        1、右指针不断后移，直到sum>=target
        2、左指针不断后移，直到sum<=target
        3、每次符合条件，result = min(result, right - left + 1)
        4、所有元素仍然不满足，返回0
     */

    public int minSubArrayLen(int target, int[] nums) {
        int leftIndex = 0;
        int length = Integer.MAX_VALUE;
        int sum = 0;
        for (int rightIndex = 0; rightIndex < nums.length; rightIndex++){
            sum += nums[rightIndex];
            while (leftIndex<=rightIndex && sum >= target){
                if (sum >= target){
                    length = Math.min(length, rightIndex-leftIndex+1);
                }
                sum = sum - nums[leftIndex];
                leftIndex++;
            }
        }
        if (length == Integer.MAX_VALUE){
            length = 0;
        }
        return length;
    }
}
