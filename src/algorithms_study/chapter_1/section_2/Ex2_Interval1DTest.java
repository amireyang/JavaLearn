package algorithms_study.chapter_1.section_2;

import edu.princeton.cs.algs4.Interval1D;

import java.util.Scanner;

public class Ex2_Interval1DTest {
    public static void testInterval(){
        Scanner in = new Scanner(System.in);
        System.out.print("Input N: ");
        int N = in.nextInt();
        Interval1D[] inter = new Interval1D[N];
        int count = 0;
        System.out.println("Input a series of double values: ");
        while(count < N){
            inter[count] = new Interval1D(in.nextDouble(), in.nextDouble());
            count++;
        }
        if(N >= 2) {
            for (int i = 0; i < inter.length - 1; i++) {
                for (int j = i + 1; j < inter.length; j++) {
                    if (inter[i].intersects(inter[j])) {
                        System.out.println(inter[i] + "intersects" + inter[j]);
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        testInterval();
    }
}
