package com.nys.study.spring.springbootstudy.algorithm.daily_代码随想录;

import java.util.Arrays;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 977.有序数组的平方
 * @date 2024/5/22 10:55
 */
public class Daily03_Array_977 {

    public static void main(String[] args) {
        Daily03_Array_977 test = new Daily03_Array_977();
        int[] a = new int[]{-4, 1, 3, 5, 9, 11};
        int[] ints = test.sortedSquares2(a);
        System.out.println(Arrays.toString(ints));
    }

    /*
        给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

            输入：nums = [-4,-1,0,3,10]
            输出：[0,1,9,16,100]
            解释：平方后，数组变为 [16,1,0,9,100]
            排序后，数组变为 [0,1,9,16,100]
     */

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int firstIndex = 0;
        int slowIndex = 0;
        int resultIndex = 0;
        while (firstIndex < nums.length && nums[firstIndex] < 0) {
            firstIndex++;
            slowIndex++;
        }
        slowIndex--;
        while (firstIndex < nums.length && slowIndex >= 0) {
            if (Math.pow(nums[firstIndex], 2) < Math.pow(nums[slowIndex], 2)) {
                result[resultIndex] = (int) Math.pow(nums[firstIndex], 2);
                firstIndex++;
            } else {
                result[resultIndex] = (int) Math.pow(nums[slowIndex], 2);
                slowIndex--;
            }
            resultIndex++;
        }
        while (firstIndex < nums.length) {
            result[resultIndex] = (int) Math.pow(nums[firstIndex], 2);
            firstIndex++;
            resultIndex++;
        }
        while (slowIndex >= 0) {
            result[resultIndex] = (int) Math.pow(nums[slowIndex], 2);
            resultIndex++;
            slowIndex--;
        }
        return result;
    }

    /*
        优化写法
     */
    public int[] sortedSquares2(int[] nums) {
        int[] resultArr = new int[nums.length];
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int resultIndex = nums.length - 1;

        while (rightIndex >= leftIndex) {
            if (nums[rightIndex] * nums[rightIndex] > nums[leftIndex] * nums[leftIndex]) {
                resultArr[resultIndex] = nums[rightIndex] * nums[rightIndex];
                rightIndex--;
            } else {
                resultArr[resultIndex] = nums[leftIndex] * nums[leftIndex];
                leftIndex++;
            }
            resultIndex--;
        }

        return resultArr;
    }

}
