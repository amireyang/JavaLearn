package algorithms_study.chapter_1.section_4;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;

public class Ex12_PrintComNum {

    public static void sameEle(int[] a, int[] b){
        for (int i = 0, j = 0; i < a.length && j < b.length;){
            if(a[i] < b[j]){
                i++;
            }
            else if(a[i] > b[j]){
                j++;
            }
            else{
                System.out.println(a[i]);
                i++;
                j++;
            }
        }
    }


    public static void main(String[] args) {
        int[] a = {1,2,3,4,6,7,9};
        int[] b = {2,6,7,8,9};
        sameEle(a,b);
    }
}
