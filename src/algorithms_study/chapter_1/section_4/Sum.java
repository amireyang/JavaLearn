package algorithms_study.chapter_1.section_4;

import java.util.Arrays;

/**
 * 输入一个int数组，找出数组中两个数相加为0；
 */
public class Sum {
    /**
     * 找出数组中两个相加为0的整数对的对数,线性对数级别
     */
    public static int twoSum(int[] a){
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if(Arrays.binarySearch(a, -a[i]) > i){
                count++;
            }
        }
        return count;
    }

    /**
     * two sum线性级别的方法
     */
    public static int twoSumFaster(int[] a){
        int count = 0;
        for (int i = 0, j = a.length - 1; i < j;) {
            if (a[i] + a[j] < 0){
                i++;
            }
            else if(a[i] + a[j] > 0){
                j--;
            }
            else{
                count++;
                i++;
                j--;
            }
        }

        return count;
    }

    /**
     * 找出数组中三个数相加为0的组数, 平方对数阶算法
     *
     */
    public static int threeSum(int[] a){
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (Arrays.binarySearch(a, -a[i] - a[j]) > j){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * threeSum的线平方级别  你算法
     */
    public static int threeSumFaster(int[] a){
        int count = 0;
        for (int i = 0; i < a.length - 2; i++)
            for (int j = i + 1, k = a.length - 1; j < k;) {
                if(a[i] + a[j] + a[k] > 0){
                    k--;
                }
                else if(a[i] + a[j] + a[k] < 0){
                    j++;
                }
                else{
                    count++;
                    j++;
                    k--;
                }
            }
        return count;
    }

    /**
     *找出数组中四个数相加为0的组数
     *
     */
    public static int fourSum(int[] a){
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++)
                for (int k = j +1; k < a.length; k++) {
                    if (Arrays.binarySearch(a, -a[i] - a[j] - a[k]) > k)
                        count++;
                }
        }
        return count;
    }



    public static void main(String[] args) {
        int[] a = {1, -1, 2,-5, 4, 5, 6};
        Arrays.sort(a);
        System.out.println(twoSum(a));
        System.out.println(twoSumFaster(a));
        System.out.println(threeSum(a));
        System.out.println(threeSumFaster(a));
        System.out.println(fourSum(a));
    }
}
