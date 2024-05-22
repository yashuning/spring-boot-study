package com.nys.study.spring.springbootstudy.algorithm.daily_代码随想录;

import java.util.Arrays;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 27. 移除元素
 * @date 2024/5/12 16:36
 */
public class Daily02_Array_27 {

    public static void main(String[] args) {
        Daily02_Array_27 test = new Daily02_Array_27();
        int[] arr = new int[]{0,1,2,2,3,0,4,2};
        int result = test.removeElement3(arr, 2);
        System.out.println(result);
        System.out.println(Arrays.toString(arr));
    }

    /*
        题解1：2023.02.01
        原数组不保持顺序做法
     */
    public int removeElement(int[] nums, int val) {
        // 1、头找要删除元素
        // 2、尾找不删除元素
        int start = 0;
        int end = nums.length - 1;
        if (nums.length == 0) {
            return 0;
        }
        while (start < end) {
            while ((nums[start] != val) && (start < end)) {
                start++;
            }
            while (nums[end] == val && start < end) {
                end--;
            }
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
        }
        if (nums[start] != val) {
            return start + 1;
        } else {
            return start;
        }
    }

    /*
        题解2 2024-05-12
        原数组不保持顺序做法
        这次虽然想的比较快，但是没有前一次写的好
     */
    public int removeElement2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int end = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (end >= 0 && nums[end] == val) {
                nums[end] = 0;
                end--;
            }
            if (i > end || end < 0) {
                break;
            }
            if (nums[i] == val) {
                nums[i] = nums[end];
                nums[end] = 0;
                end--;
            }
        }
        return end + 1;
    }

    /*
     * 题解3 2024-05-13 结果保持原数组顺序
     * 双指针法（快慢指针法）： 通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。
     *      快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
     *      慢指针：指向更新 新数组下标的位置
     */
    public int removeElement3(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int slowIndex = 0;

        for (int firstIndex = 0; firstIndex < nums.length; firstIndex++){
            if (nums[firstIndex] != val) {
                if (firstIndex != slowIndex) {
                    nums[slowIndex] = nums[firstIndex];
                }
                slowIndex++;
            }
        }

        return slowIndex;
    }

}
