package algorithms_study.chapter_1.section_3;

import java.util.Scanner;
import java.util.Stack;

public class Ex10_InfixToPostfix {

    /**
     * 接受一个前缀表达式，输出其后缀表达式；
     * @param str 前缀表达式字符串
     * @return  后缀表达式
     */
    public static String infixToPostfix(String str){
        String[] a = str.split(" ");
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();

        for (String s : a) {
            if (s.equals("("))      ;
            else if(s.equals("+")) ops.push(s);
            else if(s.equals("-")) ops.push(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals("/")) ops.push(s);
            else if(s.equals("sqrt")) ops.push(s);
            else if(s.equals(")")){
                String op = ops.pop();
                String val = vals.pop();
                if (op.equals("sqrt")){
                    vals.push(val + " " + op + " ");
                }
                else{
                    vals.push(vals.pop() + " " + val + " " + op + " ");
                }
            }
            else{
                vals.push(s);
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(infixToPostfix(str));
    }
}
