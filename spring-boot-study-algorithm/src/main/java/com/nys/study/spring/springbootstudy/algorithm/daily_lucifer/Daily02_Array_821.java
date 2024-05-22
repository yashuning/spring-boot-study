package com.nys.study.spring.springbootstudy.algorithm.daily_lucifer;

import java.util.Arrays;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 821. 字符的最短距离
 * @date 2024/5/6 16:38
 */
public class Daily02_Array_821 {

    /*
        给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
        返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
        两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。

        输入：s = "loveleetcode", c = "e"
        输出：[3,2,1,0,1,0,0,1,2,2,1,0]
        解释：
            字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
            距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
            距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
            对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
            距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
     */
    public static void main(String[] args) {
        Daily02_Array_821 a = new Daily02_Array_821();
        int[] ints = a.shortestToChar("loveleetcode", 'e');
        System.out.println(Arrays.toString(ints));
    }

    /*
        核心：数组的遍历（正向遍历和反向遍历）
        思路：
            1、将字符串转成数组
            2、遍历数组，从左往右找，未找到置位最大值，找到则用当前的变量位置相减得到距离值
            3、第二次遍历从右往左找，比较原有距离和新计算距离，更新
     */
    public int[] shortestToChar(String s, char c) {
        char[] sArr = s.toCharArray();
        int position = Integer.MAX_VALUE;
        int[] result = new int[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            if (c == sArr[i]) {
                result[i] = 0;
                position = i;
            } else {
                result[i] = Math.abs(position - i);
            }
        }
        for (int i = sArr.length - 1; i >= 0; i--) {
            if (c == sArr[i]) {
                result[i] = 0;
                position = i;
            } else {
                result[i] = Math.min(Math.abs(result[i]), Math.abs(position - i));
            }
        }
        return result;
    }

}
