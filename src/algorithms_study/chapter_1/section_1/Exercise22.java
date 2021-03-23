package algorithms_study.chapter_1.section_1;

import java.util.Arrays;

public class Exercise22 {
    //方法重载，有点秀
    public static int rank(int key, int[] a){
        return rank(key, a, 0, a.length, 1);
    }

    private static int rank(int key, int[] a, int lo, int hi, int deep){
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        for (int i = 0; i < deep; i++) {
            System.out.print(" ");
        }
        System.out.println("lo: " + lo + " hi: " + hi);

        if(a[mid] > key) {
            return rank(key, a, lo, mid - 1,deep + 1);
        }
        else if(a[mid] < key){
            return rank(key, a, mid + 1, hi, deep + 1);
        }
        else return mid;
    }

    public static void main(String[] args) {
        int[] arr = {2,7, 25, 46, 75, 6, 35 ,46, 21, 7, 8, 9, 15, 23};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        rank(6, arr);
    }
}
