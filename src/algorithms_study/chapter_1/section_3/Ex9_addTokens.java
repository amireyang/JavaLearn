package algorithms_study.chapter_1.section_3;

import java.util.Scanner;
import java.util.Stack;

public class Ex9_addTokens {


    public static void addTokens(String str){
        String[] a = str.split(" ");
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();

        for (String s : a) {
            if (s.equals("+") ||
                    s.equals("-") ||
                    s.equals("*") ||
                    s.equals("/") ||
                    s.equals("sqrt")) ops.push(s);
            else if(s.equals(")")) {
                String val = vals.pop();
                String op = ops.pop();
                if (op.equals("+") ||
                        op.equals("-") ||
                        op.equals("*") ||
                        op.equals("/")){
                    val = "( " + vals.pop() + " " + op + " " + val + " " + s;
                }
                if (op.equals("sqrt")) val = op + " ( " + val + " )";
                vals.push(val);
            }
            else{
                vals.push(s);
                }
        }

        System.out.println(vals.pop());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        addTokens(s);
    }

}
