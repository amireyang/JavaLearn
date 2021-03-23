package algorithms_study.chapter_1.section_1;

import java.util.*;

public class Exercise35{
    /**
     * 模拟两个色子
     */
    public static void polyGame() {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        //概率
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36;
        }
        System.out.print("probility: ");
        for (int i = 2; i < dist.length; i++) {
            System.out.printf("%6.3f", dist[i]);
        }
        System.out.println();

        //几率
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i < 13; i++) {
            map.put(i, 0);
        }
        Random r = new Random();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int temp;
        for (int i = 0; i < N; i++) {
            //模拟两个色子
            temp = r.nextInt(6) + 1 + r.nextInt(6) + 1;
            map.put(temp, map.get(temp) + 1);
        }
        for (int i = 2; i < 13; i++) {
            System.out.printf( "%2d: %d\n",i, map.get(i));
        }

        //检验相等
        double frequency;
        boolean isAccurate = true;
        System.out.print("probility: ");
        for (int i = 2; i < 13; i++) {
            frequency = (double)map.get(i) / N;
            System.out.printf("%6.3f", frequency);
            if(frequency - dist[i] > 0.001) {isAccurate = false;}
        }
        System.out.println("\nis accurate? " + isAccurate);
    }
    public static void main(String[] args) {
        polyGame();
    }
}