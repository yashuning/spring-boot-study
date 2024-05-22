package com.nys.study.spring.springbootstudy.algorithm.daily_lucifer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 数组形式的整数加法
 * @date 2024/4/21 16:09
 */
public class Daily01_Array_989 {

    public static void main(String[] args) {
        Daily01_Array_989 a = new Daily01_Array_989();
        int[] arr = new int[]{7};
        List<Integer> list = a.addToArrayForm(arr, 993);
        System.out.println(list);
    }

    /*
     * 原先第一次做题的思路，是将k转为数组，但是各种判断，以及两个数组不一致的判断逻辑特别乱
     *
     * 核心：数组的遍历
     */

    public List<Integer> addToArrayForm(int[] num, int k) {
        reverseArr(num);
        List<Integer> resultArr = new ArrayList<>();
        int add = 0;

        for (int i = 0; i <num.length;i++){
            int tmp  = k%10 + add;
            add = 0;
            int sum = tmp + num[i];
            if (sum >= 10){
                add += 1;
                resultArr.add(sum%10);
            }else {
                resultArr.add(sum);
            }
            k = k/10;
        }

        while (k > 0 || add > 0){
            int sum = k%10 + add;
            add = 0;
            if (sum >= 10){
                add += 1;
                resultArr.add(sum%10);
            }else {
                resultArr.add(sum);
            }
            k = k/10;
        }

        int[] resultArr1 = listToArr(resultArr);
        reverseArr(resultArr1);
        return arrToList(resultArr1);
    }


    /*
        技巧：看提示，本题中num.length <= 10^4，所以这个num可能会很大不好操作，那此时转k的操作会较为简单

        1、数字转数组
        2、两个数组反转
        3、相加
        4、结果反转

     */
//    public List<Integer> addToArrayForm(int[] num, int k) {
//        int[] kArr = numToArray(k);
//        reverseArr(num);
//        reverseArr(kArr);
//        int tmp = 0;
//        List<Integer> resultArr = new ArrayList<>();
//        if (kArr.length <= num.length){
//            int i;
//            for (i = 0; i< kArr.length; i++){
//                int sum = num[i] + kArr[i] + tmp;
//                tmp = 0;
//                if (sum>=10){
//                    tmp = tmp+1;
//                    resultArr.add(sum%10);
//                } else {
//                    resultArr.add(sum);
//                }
//            }
//            for (int j = i;j< num.length; j++){
//                int sum = num[j] + tmp;
//                tmp = 0;
//                if (sum>=10){
//                    tmp = tmp+1;
//                    resultArr.add(sum%10);
//                } else {
//                    resultArr.add(sum);
//                }
//            }
//        }else {
//            int i;
//            for (i = 0; i< num.length; i++){
//                int sum = num[i] + kArr[i] + tmp;
//                tmp = 0;
//                if (sum>=10){
//                    tmp = tmp+1;
//                    resultArr.add(sum%10);
//                } else {
//                    resultArr.add(sum);
//                }
//            }
//            for (int j = i;j< kArr.length; j++){
//                int sum = kArr[j] + tmp;
//                tmp = 0;
//                if (sum>=10){
//                    tmp = tmp+1;
//                    resultArr.add(sum%10);
//                } else {
//                    resultArr.add(sum);
//                }
//            }
//        }
//        if (tmp > 0){
//            resultArr.add(1);
//        }
//
//        int[] resultArr1 = listToArr(resultArr);
//        reverseArr(resultArr1);
//        return arrToList(resultArr1);
//    }

    private int[] numToArray(int k){
        int[] arr = new int[String.valueOf(k).length()];
        int tmp = 10;
        // 考虑 = 的情况
        for (int i = arr.length-1; i>=0; i--){
            arr[i] = k % 10;
            k = k / 10;
        }
        return arr;
    }

    private void reverseArr(int[] arr){
        int mid = arr.length/2;
        int head = 0;
        if (mid == head){
            return;
        }
        int tail = arr.length - 1;
        while (head < mid){
            int tmp = arr[head];
            arr[head] = arr[tail];
            arr[tail] = tmp;
            head ++;
            tail --;
        }
    }

    private int[] listToArr(List<Integer> list){
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private List<Integer> arrToList(int[] arr){
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

}
