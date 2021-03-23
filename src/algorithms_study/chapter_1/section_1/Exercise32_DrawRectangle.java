package algorithms_study.chapter_1.section_1;

import edu.princeton.cs.algs4.StdDraw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise32_DrawRectangle {


    private static void drawRectangle(int N, double l, double r, double[] arr){

        if(l >= r) throw new IllegalArgumentException("错误：l大于等于r");
        double unitlength = (r - l) / N;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, 0);
        }

        int length;
        for (int i = 0; i < arr.length; i++) {
            length = (int)(arr[i] / unitlength);
            if(length < N) {
                map.put(length, map.get(length) + 1);
            }
        }
        map.forEach((Integer i1, Integer i2)->{
            double x = 1.0 * (i1 + 1) / N;
            double y = i2 * 1.0 / N /2;
            double rw = 0.45 / N;
            double rh = i2 * 1.0 / N /2;
            StdDraw.filledRectangle(x,y,rw,rh);
        });


    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] str = s.split(" ");
        double[] arr = new double[str.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Double.parseDouble(str[i]);
        }

        //drawRectangle(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), arr);
        drawRectangle(10, 0.0, 10.0, arr);
    }
}
