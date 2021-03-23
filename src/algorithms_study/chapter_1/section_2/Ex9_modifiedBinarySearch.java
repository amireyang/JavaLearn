package algorithms_study.chapter_1.section_2;

import edu.princeton.cs.algs4.Counter;

import java.util.Arrays;

public class Ex9_modifiedBinarySearch {
    public static int binarySearch(int[] arr, int key, Counter counter){
        int lo = 0;
        int hi = arr.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(key < arr[mid]){
                hi = mid - 1;
                counter.increment();
            }else if(key > arr[mid]){
                lo = mid + 1;
                counter.increment();
            }else{
                counter.increment();
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,23,12,45,7,16,23,54,69,5,8,32,41,2,1,6};
        Arrays.sort(arr);
        Counter counter = new Counter("binarySearchCounter");
        binarySearch(arr, 5, counter);
        System.out.println("the amount of key checked is " + counter.tally());
    }
}
