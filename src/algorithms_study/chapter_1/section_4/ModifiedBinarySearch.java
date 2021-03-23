package algorithms_study.chapter_1.section_4;

import java.util.Arrays;

public class ModifiedBinarySearch {
    public static int modifiedBinarySearch(int[] a, int key){
        Arrays.sort(a);
        int index = Arrays.binarySearch(a, key);
        int i = -1;
        if(index >= 0){
            for (i = index - 1; i >= 0; i--) {
                if (a[i] == a[index]){

                }
                else{
                    break;
                }
            }
        }
        if(i >= 0){
            return ++i;
        }
        else{
            return index;
        }
    }

    //一种更好的方案
    public static int rank2(int[] a, int key) {
        int hi = a.length;
        int lo = 0;
        int mid = 0;
        while (lo <= hi) {
            mid = (hi + lo) / 2;
            if (a[mid] < key) {
                lo = mid + 1;
            } else if (a[mid] > key) {
                hi = mid - 1;
            } else if (mid > 0 && a[mid - 1] == key) {
                hi = mid ;
            } else {
                return mid;
            }
        }
        return -1;
    }



    public static void add(int a){

    }

    public static void main(String[] args) {
        int[] a = {-1, 1, 1,1, 2,2,3,5,6,7};
        System.out.println(modifiedBinarySearch(a,2));
        System.out.println(rank2(a, 2));
    }
}
