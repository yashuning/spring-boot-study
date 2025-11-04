package com.nys.study.spring.springbootstudy.algorithm.list;

import java.util.Arrays;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2024/3/7 17:57
 */
public class Test {
    public static void main(String[] args) {
        int[] ints = {3, 2, 4};
        int[] result = a(ints, 6);
        System.out.println(Arrays.toString(result));
        String s = "123";
        char[] charArray = s.toCharArray();
    }

    private static int[] a(int[] arr, int target){
        int r1 = -1;
        int r2 = -1;
        for (int i = 0; i<arr.length-1; i++){
            for (int j = 1; j< arr.length;j++){
                if (((arr[i] + arr[j]) == target) && r1 == -1){
                    r1 = i+1;
                    r2 = j+1;
                    break;
                }
            }
        }
        return new int[]{r1, r2};
    }
}
