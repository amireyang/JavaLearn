package algorithms_study;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] a = {1, 2,3,-1, 4, 6, -5, -6, -7,-2, -9, 10};
        System.out.println(twoSum(a));
        System.out.println(threeSum(a));
        for (int i = 250; i < 100000; i += i) {
            double time = threeSum(i, 1000);
            System.out.printf("number of values: %d  Times: %f Seconds", i, time);
            System.out.println();
        }
    }




    /**
     * 欧几里得算法
     * 求两个数的最大公约数
     * @param p 一个整数
     * @param q 一个整数
     * @return p和q的最大公约数
     */
    public static int gcd(int p, int q){
        if(q == 0) { return p; }
        int r = p % q;
        return gcd(q,r);
    }

    /**
     * 求两个整数的最大公约数，非递归实现
     * @param p
     * @param q
     * @return
     */
    public static int gocLoop(int p, int q){
        if(q == 0){ return p;}
        int temp;
        while(p % q != 0){
            temp = p % q;
            p = q;
            q = temp;
        }
        return q;
    }


    /**
     * Fibonacci数列非递归实现
     * @param N
     * @return
     */
    public static int fibo(int N){
        int f = 0;
        int g = 1;
        for (int i = 0; i < N; i++) {
            f = f + g;
            g = f - g;
        }
        return f;
    }

    /**
     * fibonacci数列递归实现
     * @param N
     * @return
     */
    public static int fibo_recursion(int N){
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return fibo_recursion(N-1) + fibo_recursion(N - 2);
    }

    /**
     * Two Sum
     * 给定一个整数数组，找出数组中和为0的整数对。
     * @param a 进行计算的数组
     */
    public static int twoSum(int[] a){
        int cnt = 0;
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            if(Arrays.binarySearch(a, -a[i]) > i){
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * Tree Sum
     * 给定一个整数数组，找出数组中和为0的三个整数，这三个整数各不重复。
     */
    public static int threeSum(int[] a){
        int cnt = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                if (Arrays.binarySearch(a, -a[i]-a[j]) > j){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * 随机初始化一个数组，元素值在[-max, max]之间，
     * @param num 数组长度
     * @return 三个整数和为0的数量。
     */
    public static double threeSum(int num,int max){
        int cnt = 0;
        int[] a = new int[num];
        for (int i = 0; i < a.length; i++) {
            a[i] = -max + (int)(Math.random() * (max + 1));
        }
        Arrays.sort(a);
        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length - 2; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (Arrays.binarySearch(a, -a[i] - a[j]) > j){
                    cnt++;
                }
            }
        }
        return (System.currentTimeMillis() - start) / 1000.0;
    }

}
