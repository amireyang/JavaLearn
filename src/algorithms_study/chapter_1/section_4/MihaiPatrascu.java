package algorithms_study.chapter_1.section_4;

public class MihaiPatrascu {
/*    *//**
     * 用加、减、常数的额外内存实现二分查找
     *//*
    public static int mihaiPatrascu(int[] a, int key){
        int lo = 0;
        int hi = a.length - 1;
        while( lo <= hi ){
            int mid = findmid(lo, hi);
            if( key < a[mid] ){
                hi = mid - 1;
            }
            else if(key > a[mid]){
                lo = mid + 1;
            }
            else{
                return mid;
            }
        }
        return  -1;
    }

    public static void main( String[] args ){
        int[] a = {1,3,5,6,7,8,9,13};
        System.out.println(mihaiPatrascu( a, 6 ));
    }*/
}
