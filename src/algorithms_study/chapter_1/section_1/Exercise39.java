package algorithms_study.chapter_1.section_1;

import java.util.Arrays;
import java.util.Random;

public class Exercise39 {
    public static void testBinarySearch(int N, int T) {
        //随机生成两个大小为N的6位正整数的数组
        int[] counter = new int[T];
        for (int i = 0; i < T; i++) {
            counter[i] = 0;
        }
        Random r = new Random();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        //T次实验
        for (int k = 0; k < T; k++) {
            //初始化数组
            for (int i = 0; i < N; i++) {
                arr1[i] = 100000 + (int)(900000 * Math.random());
                arr2[i] = 100000 + (int)(900000 * Math.random());


            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            //统计出现在两个数组中的元素个数
            for (int i = 0; i < N; i++) {
                int temp = Arrays.binarySearch(arr2, arr1[i]);
                if(temp >= 0){
                    int next1 = countNext(arr1, i);
                    int prior2 = countPrior(arr2, temp);
                    int next2 = countNext(arr2, temp);
                    i += next1;
                    counter[k] += (next1 + 1 + prior2 + next2 + 1);
                }
            }

        }
        int sum = 0;
        for (int i = 0; i < T; i++) {
            sum += counter[i];
        }
        System.out.printf("mean: %9.2f\n", (double)sum / T);
    }

    private static int countNext(int[] a, int num){
        int count = 0;
        for (int next = num + 1;next <a.length && a[next] == a[num]; next++) {
            count ++;
        }
        return count;
    }
    private static int countPrior(int[] a, int num){
        int count = 0;
        for (int prior = num - 1; prior >= 0 && a[prior] == a[num];prior--) {
            count ++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {1,3,45,6,7,8,3,5};
        Arrays.sort(a);
        int N = 1000;
        for (int i = 1; i <= 4; i++) {
            System.out.printf("N=%7d  ", N);
            testBinarySearch(N, 100);
            N *= 10;
        }
    }
}
