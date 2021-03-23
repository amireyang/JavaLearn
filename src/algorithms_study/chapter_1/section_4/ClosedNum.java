package algorithms_study.chapter_1.section_4;

import java.util.Arrays;

public class ClosedNum {
    /**
     * 找出一个double数组，两个数之差的绝对值最小的两个数
     * 反对一个
     */
    public static void closedNum(double a[]){
        Arrays.sort(a); //运行时间为线性对数级别的
        int index = 0;
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i] - a[i + 1] < min){
                min = a[i] - a[i + 1];
                index = i;
            }
        }
        System.out.println("最接近的两个数：" + a[index] + " " + a[index + 1]);
    }

    /**
     *差的绝对值最大的一对数。
     */
    public static void farestNum(double a[]){
        double max = a[1];
        double min = a[2];
        for (int i = 3; i < a.length; i++) {
            if (a[i] < min){
                min = a[i];
            }

            if (a[i] > max){
                max = a[i];
            }
        }
        System.out.println("最远的两个数 " + min + " " + max);
    }

    /**
     * a test client
     */

    public static void main(String[] args) {
        double[] a = { 1, 2,3, 5, 6,7, 8, 4};
        closedNum(a);
        farestNum(a);
    }
}
