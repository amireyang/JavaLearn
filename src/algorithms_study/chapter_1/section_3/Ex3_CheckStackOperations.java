package algorithms_study.chapter_1.section_3;

import java.util.Stack;

public class Ex3_CheckStackOperations {
    /**
     * 从0 ~ N -1，N个数依次一系列的入栈和出栈的混合操作，输入一种序列，检查该序列的合法性
     * @param a 一个数组，数组长度决定了N的值
     * @return 该序列是否合法
     */
    public static boolean checkStackOpeartion(int[] a) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j <= a.length) {
            if (!stack.isEmpty() && stack.peek() == a[i]) {
                stack.pop();
                i++;
            } else {
                if (j < a.length) {
                    stack.push(j);
                }
                j++;
            }
        }
        System.out.println("numbers in stack: " + stack.toString());
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        int[] a = {4,6,8,7,5,3,2,9,0,1};
        int[] b = {9,8,7,6,5,4,3,2,1,0};
        int[] c = {4,6,8,7,5,3,2,9,0,1};
        System.out.println(checkStackOpeartion(a));
        System.out.println(checkStackOpeartion(b));
        System.out.println(checkStackOpeartion(c));
    }
}
