package com.nys.study.spring.springbootstudy.algorithm.daily_lucifer;

import java.util.Stack;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 394. 字符串解码
 * @date 2024/4/20 22:11
 */
public class Daily04_Stack_String_394 {

    public static void main(String[] args) {
        Daily04_Stack_String_394 test = new Daily04_Stack_String_394();
        String s = test.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        System.out.println(s);
    }

    /*
        思路：使用栈解决
        两个栈：字母栈，数字栈
        主要在左右括号的解决，遇到一个右括号，就需要解析出来对应左括号的内容，以及弹出对应数字栈的数字
     */
    public String decodeString(String s) {
        char[] sArr = s.toCharArray();
        String result = "";
        Stack<String> charStack = new Stack();
        Stack<Integer> numStack = new Stack();
        String num = "";
        int i = 0;
        while (i < sArr.length){
            // 解析数字
            if (Character.isDigit(sArr[i])){
                while (Character.isDigit(sArr[i])){
                    num = num.concat(String.valueOf(sArr[i]));
                    i++;
                }
                i--;
                numStack.push(Integer.valueOf(num));
                num = "";
            } else if("]".equals(String.valueOf(sArr[i]))){
                // 遇到右括号，解析栈中对应左括号的内容
                String partString = "";
                while (!charStack.isEmpty()) {
                    String ss = charStack.pop();
                    while (!"[".equals(ss)){
                        partString = String.valueOf(ss).concat(partString);
                        ss = charStack.pop();
                    }
                    partString = genRepeatString(numStack.pop(), partString);
                    if (charStack.isEmpty()){
                        result = result.concat(partString);
                    }else {
                        charStack.push(partString);
                    }
                    break;
                }
            } else if("[".equals(String.valueOf(sArr[i]))) {
                charStack.push(String.valueOf(sArr[i]));
            } else {
                if (charStack.isEmpty()){
                    result = result.concat(String.valueOf(sArr[i]));
                }else {
                    charStack.push(String.valueOf(sArr[i]));
                }
            }
            i++;
        }
        return result;
    }

    private String genRepeatString(Integer repeatNum, String partString){
        String result = "";
        for(int i = 0;i<repeatNum;i++){
            result = result.concat(partString);
        }
        return result;
    }

}
