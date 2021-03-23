package algorithms_study.chapter_1.section_2;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Scanner;

public class Ex1_Point2DExample {

    /**
     * 输入一个参数N,在单位正方形中生成N个随机点，然后统计两点之间的最近距离。
     * @param N
     */
    public static void printPoint(int N) {
        Point2D[] point = new Point2D[N];
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.square(0, 0, 0.5);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < N; i++) {
            double x = -0.5 + Math.random();
            double y = -0.5 + Math.random();
            point[i] = new Point2D(x, y);
            point[i].draw();
        }
        double min = Math.sqrt(2.0);
        if (N >= 2){
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    double dis = point[i].distanceTo(point[j]);
                    if (dis < min) {
                        min = dis;
                    }
                }
            }
        }
        System.out.println("两点之间的最短距离：" + min);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input N: ");
        printPoint(in.nextInt());
    }
}
