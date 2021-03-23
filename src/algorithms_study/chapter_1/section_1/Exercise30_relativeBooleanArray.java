package algorithms_study.chapter_1.section_1;

import java.util.Arrays;

public class Exercise30_relativeBooleanArray {

    /**
     * 初始化一个boolean数组arr[N][N]，当i和j互质时，arr[i][j] = true; else = false;
     * 互质：若N个整数的最大公因数是1，则这N个整数互质。
     * @param arr N阶的boolean数组
     * @return 一个赋值后的boolean数组。
     */
    public static boolean[][] initiBooleanArray(boolean[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (gcd(i, j) == 1); //简化式
            }
        }
        return arr;
    }

    public static int gcd(int p, int q){
        if(q == 0){
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        final int N = 10;
        boolean[][] arr = new boolean[N][N];
        arr = initiBooleanArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}

