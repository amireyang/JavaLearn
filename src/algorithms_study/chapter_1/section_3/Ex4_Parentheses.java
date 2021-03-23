package algorithms_study.chapter_1.section_3;

import algorithms_study.chapter_1.section_3.code_in_book.LinkedListStack;

import java.util.Scanner;

public class Ex4_Parentheses {
    /**
     * 从标准输入中读取一个文本流，并使用栈判定其中的括号是否配对完整；如[()]{}{}[()()]()}应该打印true；
     * 思路：遇到左括号则入栈，遇到右括号则出栈，出栈前先进行检测，为空返回false；最后返回stack.isEmpty();
     * 这里我们使用code_in_book包里面的LInkedlistStack
     * @return
     */
    public static boolean parentheses(String s){
        LinkedListStack<Character> linkedListStack = new LinkedListStack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
                if (c == 'c' || c == '[' || c == '{') {
                    linkedListStack.push(c);
                } else if (c == ('(') && (linkedListStack.isEmpty() || linkedListStack.pop() != ')') ||
                        c == ']' && (linkedListStack.isEmpty() || linkedListStack.pop() != ']') ||
                        c == '}' && (linkedListStack.isEmpty() || linkedListStack.pop() != '}') ) {

                        return false;
                }
            }
            return linkedListStack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         String s = in.nextLine().trim();
        System.out.println(parentheses(s));
    }
}
