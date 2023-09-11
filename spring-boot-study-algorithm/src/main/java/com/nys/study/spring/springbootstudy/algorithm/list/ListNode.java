package com.nys.study.spring.springbootstudy.algorithm.list;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 链表节点定义
 * @date 2023/2/8 9:03 下午
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
