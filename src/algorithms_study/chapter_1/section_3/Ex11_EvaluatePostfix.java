package algorithms_study.chapter_1.section_3;

import java.util.Scanner;
import java.util.Stack;



public class Ex11_EvaluatePostfix {

    /**
     * 后缀表达式计算，并打印结果值
     *
     * @param s 要计算的后缀表达式的值。
     */
    public static void evaluatePostfix(String s){
        String[] a = s.split(" ");
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        for (String str : a) {
            double v;
            if (str.equals("+")){
                v = vals.pop();
                v = vals.pop() + v;
                vals.push(v);
            }
            else if (str.equals("-")){
                v = vals.pop();
                v = vals.pop() - v;
                vals.push(v);
            }
            else if(str.equals("*")){
                v = vals.pop();
                v = vals.pop() * v;
                vals.push(v);
            }
            else if(str.equals("/")){
                v = vals.pop();
                v = vals.pop() / v;
                vals.push(v);
            }
            else if(str.equals("sqrt")){
                v = vals.pop();
                v = Math.sqrt(v);
                vals.push(v);
            }
            else {
                vals.push(Double.parseDouble(str));
            }
        }
        System.out.println(vals.pop());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        evaluatePostfix(s);
    }

}
