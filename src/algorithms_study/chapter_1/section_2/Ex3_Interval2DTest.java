package algorithms_study.chapter_1.section_2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;
import java.util.Scanner;

public class Ex3_Interval2DTest {
    public static void printInterval2D(int N, double min, double max){
        if(min >= max || min <= 0 || max <= 0 || N < 0){
            throw new IllegalArgumentException();
        }
        StdDraw.setXscale(-max, max);
        StdDraw.setYscale(-max, max);
        StdDraw.square(0,0,min / 2);
        StdDraw.square(0,0,max / 2);

        Random r = new Random();
        Interval2D[] interval = new Interval2D[N];
        for (int i = 0; i < N; i++) {
            double x1 = -(min / 2 + r.nextDouble() * (max - min) /2);
            double x2 = (min / 2 + r.nextDouble() * (max - min) /2);
            double y1 = -(min / 2 + r.nextDouble() * (max - min) /2);
            double y2 = (min / 2 + r.nextDouble() * (max - min) /2);
            interval[i] = new Interval2D(new Interval1D(x1,x2), new Interval1D(y1, y2));
        }

        int counter = 0;
        for (int i = 0; i < N -1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (interval[i].intersects(interval[j])){
                    System.out.println("interval[" + i + "]" + " intersects of " + "interval[" + j + "]");
                    counter++;
                }
            }
        }
        System.out.println("有包含关系的间隔数量：" + counter);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("input N, min, max");
        printInterval2D(in.nextInt(), in.nextDouble(), in.nextDouble());
    }

}
