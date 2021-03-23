package algorithms_study.chapter_1.section_1;

import java.util.Arrays;

public class Exercise29_equalKey {
    /**
     * 接受一个整形有序数组，返回数组arr中比key小的元素数量
     * @param arr 整形有序的
     * @param key 目标值
     * @return
     */
    public static int rank(int[] arr, int key){
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while(lo <= hi){
            mid = (lo + hi) / 2;
            if(key < arr[mid]){
                hi = mid - 1;
            }else if(key > arr[mid]){
                lo = mid + 1;
            }else{
                //向前找重复元素
                int i = 0;
                for (i = mid - 1; i >= 0 && arr[i] == arr[mid]; i--) {
                }
                return ++i;
            }
        }

        return lo;
    }

    /**
     * 接受一个整形有序数组，返回数组中等于key的元素数量
     * @param arr 整形有序数组
     * @param key 目标键值
     * @return
     */
    public static int count(int[] arr, int key) {

        int result = binarySearch(arr, key);
        if(result != -1){
            int counter = 1;

            for (int i = result + 1; i < arr.length && arr[i] == arr[result]; i++) {
                counter ++;
            }

            for (int i = result - 1; i >= 0 && arr[i] == arr[result]; i--) {
                counter++;
            }
            return counter;
        }
        return 0;
    }

    /**
     * 二分查找
     * @param arr 被查找的数组
     * @param key 目标值
     * @return boolean值
     */
    private static int binarySearch(int[] arr, int key){
        int lo = 0;
        int hi = arr.length-1;
        int mid;

        while(lo <= hi){
            mid = (lo + hi) / 2;
            if(arr[mid] > key){
                hi = mid - 1;
            }else if(arr[mid] < key){
                lo = mid + 1;
            }else{
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {11,85,48,3,1,64,9,8,2,3,1,51,6,1,1,3,1,5,51,8,9,5,4};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int key = 1  ;
        System.out.println("比" + key + "小的元素数量：" + rank(arr, key));
        System.out.println("和" + key + "相等的元素数量：" + count(arr, key));
    }
}
