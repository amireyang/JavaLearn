package algorithms_study.chapter_1.section_3.code_in_book;

import java.util.Scanner;
import java.util.Stack;

public class Evaluate {

    public static void evaluate(String s){
        String[] a = s.split(" ");
        Stack<String> ops = new Stack<>();
        Stack<Double> val = new Stack<>();

        for (String v : a) {
            if (v.equals("("));
            else if (v.equals("+") ||
                    v.equals("-") ||
                    v.equals("*") ||
                    v.equals("/") ||
                    v.equals("sqrt")) ops.push(v);
            else if(v.equals(")")) {
                double value = val.pop();
                String op = ops.pop();
                if (op.equals("+")) value = val.pop() + value;
                else if (op.equals("-")) value = val.pop() - value;
                else if (op.equals("*")) value = val.pop() * value;
                else if (op.equals("/")) value = val.pop() / value;
                else if (op.equals("sqrt")) value = Math.sqrt(value);
                val.push(value);
            }
            else val.push(Double.parseDouble(v));
        }
        System.out.println(val.pop());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        evaluate(s);
    }

}
