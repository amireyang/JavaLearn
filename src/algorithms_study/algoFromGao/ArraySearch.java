package algorithms_study.algoFromGao;

import java.util.Arrays;

public class ArraySearch{
    /**
     * 题目描述
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 思路
     * 先查第一列，找出这个数应该处于哪一行，之后查找对应的行。
     */

    public static boolean arraySearch(int[][] a, int target){
        if( target < a[0][0] || target > a[a.length -1][a[0].length - 1] ){
            return false;
        }
        int lo = 0;
        int hi = a.length - 1;

        //lg(a.length)
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(target < a[mid][0]){
                hi = mid - 1;
            }
            else if(target > a[mid][0]){
                lo = mid + 1;
            }
            else{
                return true;
            }
        }


        if(lo == 0){
            return Arrays.binarySearch(a[lo], target) >= 0;
        }
        else{
            return Arrays.binarySearch(a[lo - 1], target) >= 0;
        }
    }

    public static void main( String[] args ){
        int[][] a = {{1, 2, 4}, { 5, 9, 10}, {15, 18, 20}};
        System.out.println(Arrays.binarySearch( a[0], 1 ));
        System.out.println(arraySearch( a, 17 ));
    }
}
