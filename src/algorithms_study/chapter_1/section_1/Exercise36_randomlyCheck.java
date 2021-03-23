package algorithms_study.chapter_1.section_1;

import edu.princeton.cs.algs4.StdRandom;


public class Exercise36_randomlyCheck {
    public static void randomlyCheck(int M, int N, double[] arr){
        int[][] counter = new int[M][M];
        //打乱N次
        for (int i = 0; i < N; i++) {
            //每次打乱前进行初始化
            for (int j = 0; j < arr.length; j++) {
                arr[j] = j;
            }
            StdRandom.shuffle(arr);

            for (int j = 0; j < arr.length; j++) {
                counter[j][(int) arr[j]]++;
            }
        }

        System.out.print("   ");
        for (int i = 0; i < M; i++) {
            System.out.printf("%3d", i + 1);
        }
        System.out.println();
        for (int i = 0; i < M; i++) {
            System.out.printf("%2d:", i + 1);
            for (int j = 0; j < M; j++) {
                System.out.printf("%3d", counter[i][j]);

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        double[] a = new double[M];
        randomlyCheck(M, N, a);
    }
}
