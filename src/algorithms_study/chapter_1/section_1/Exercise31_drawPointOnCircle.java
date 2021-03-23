package algorithms_study.chapter_1.section_1;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class Exercise31_drawPointOnCircle {
    public static void drawPointOnCircle(int N, double p){
        StdDraw.setXscale(-2, 2);
        StdDraw.setYscale(-2, 2);
        StdDraw.circle(0.0,0.0,1.0);
        StdDraw.setPenRadius(.005);
        double angel = 360.0 / N;
        Point2D[] point = new Point2D[N];
        for (int i = 0; i < N; i++) {
            point[i] = new Point2D(0.0 + 1.0 * Math.cos(i * angel * Math.PI / 180.0), 0.0 + 1.0 * Math.sin(i * angel * Math.PI / 180));
            point[i].draw();
        }
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        for (Point2D p1 : point) {
            for (Point2D p2 : point)
            if (p1 != p2 && Math.random() < p){
                StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
            }
        }
    }

    public static void main(String[] args) {
        drawPointOnCircle(8, 1);

    }
}
