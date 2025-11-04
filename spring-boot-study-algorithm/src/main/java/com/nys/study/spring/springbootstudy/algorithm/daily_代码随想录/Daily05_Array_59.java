package com.nys.study.spring.springbootstudy.algorithm.daily_代码随想录;

import java.util.Arrays;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 59.螺旋矩阵II
 * @date 2024/5/28 13:36
 */
public class Daily05_Array_59 {

    public static void main(String[] args) {
        Daily05_Array_59 test = new Daily05_Array_59();
        System.out.println(Arrays.deepToString(test.generateMatrix(4)));

    }

    /*
        给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     */

    public int[][] generateMatrix(int n) {
        int resultNum = 1;
        int add = n - 1;
        int sub = 0;
        int[][] result = new int[n][n];
        int x = 0;
        int y = 0;
        while (resultNum <= n * n && x < n & y < n & x >= 0 & y >= 0) {
            for (; x < add; x++) {
                result[y][x] = resultNum;
                resultNum++;
            }
            for (; y < add; y++) {
                result[y][x] = resultNum;
                resultNum++;
            }
            for (; x > sub; x--) {
                result[y][x] = resultNum;
                resultNum++;
            }
            for (; y > sub; y--) {
                result[y][x] = resultNum;
                resultNum++;
            }
            sub += 1;
            add -= 1;
            x += 1;
            y += 1;
        }
        return result;
    }
}
