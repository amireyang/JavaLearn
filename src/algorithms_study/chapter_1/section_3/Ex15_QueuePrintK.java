package algorithms_study.chapter_1.section_3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Ex15_QueuePrintK {
    public static String printK(String str, int k){
        ArrayDeque<String> que = new ArrayDeque<>();
        String[] s = str.split(" ");
        for (int i = 0; i < s.length; i++) {
            que.add(s[i]);
        }
        int temp = s.length - k;
        for (int i = 0; i < temp; i++) {
            que.poll();
        }
        return que.poll();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("input value K; ");
        int k = Integer.parseInt(in.nextLine());
        System.out.println("input a series of Stirngs:");
        String str =  in.nextLine();
        System.out.println(printK(str,k));
    }
}
