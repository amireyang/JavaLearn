package algorithms_study.chapter_1.section_1;

import java.util.Arrays;

public class Exercise28_duplicateKey {

    public static void main(String[] args) {
        int[] whileList = {1, 6, 5, 6, 8, 9, 5, 6, 10, 3};
        int[] deleteDup = deleteDupKey(whileList);
        System.out.println(Arrays.toString(deleteDup));
    }

    /**
     * 删除白名单数组中return false;的重复元素
     * @param arr
     */
    public static int[] deleteDupKey(int[] arr){
        Arrays.sort(arr);
        int[] result = new int[arr.length];
        result[0] = arr[0];
        int count = 0;
        //对whilteList中的元素在result中使用二分查找，没找到则复制过去。
        for (int i = 1; i < arr.length; i++) {
            if(!binarySearch(result, arr[i], count)){
                result[++count] = arr[i];
            }
        }

        return Arrays.copyOf(result, ++count);
    }

    /**
     * 二分查找
     * @param arr 被查找的数组
     * @param key 目标值
     * @return boolean值
     */
    private static boolean binarySearch(int[] arr, int key, int hi){
        int lo = 0;
        int mid;

        while(lo <= hi){
            mid = (lo + hi) / 2;
            if(arr[mid] > key){
                hi = mid - 1;
            }else if(arr[mid] < key){
                lo = mid + 1;
            }else{
                return true;
            }
        }

        return false;
    }
}
