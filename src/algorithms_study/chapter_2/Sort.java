package algorithms_study.chapter_2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Sort{
    /**
     * 选择排序
     */
    public static int[] selectionSort(int[] a){
        for( int i = 0; i < a.length; i++ ){
            int min = i;
            for( int j = i + 1; j < a.length; j++ ){
                if( a[j] < a[min] ){
                    min = j;
                }
            }
            if(min != i){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
        System.out.println( Arrays.toString(a));
        return a;

    }

    /**
     * 插入排序
     *
     */
    public static int[] insectionSrot(int[] a){
        for( int i = 1; i < a.length ; i++ ){
            for( int j = i; j >= 1 && a[j] < a[j -1]; j-- ){
                int temp = a[j - 1];
                a[j - 1] = a[j];
                a[j] = temp;
            }
        }
        System.out.println(Arrays.toString( a ));
        return a;
    }

    /**
     *
     * 希尔排序
     */

    public static int[] shellSort(int[] a){
        int h = 1;
        while(h < a.length / 3){
            h = 3 * h + 1;
        }

        while(h >= 1){
            for( int i = h; i < a.length; i ++ ){
                for( int j = i; j >= h && a[j] < a[j - h]; j -= h ){
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
            h /= 3;
        }
        System.out.println(Arrays.toString( a ));
        return a;
    }

    /**
     *归并排序
     */
    //辅助数组
    private static int[] aux;

    public static int[] mergeSort(int[] a){
        aux = new int[a.length];
        sort( a,0, a.length - 1 );
        System.out.println(Arrays.toString( a ));
        return a;
    }

    private static void sort(int[]a, int lo, int hi){
        if(lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort( a, lo, mid );
        sort( a, mid+1, hi );
        merge(a, lo, mid, hi);
    }


    private static void merge(int[] a, int lo, int mid, int hi){
        //将[lo,mid]和[mid+1，hi]两部分按顺序合并
        int i = lo;
        int j = mid + 1;

        //将a[lo,hi]复制到辅助数组里
        for( int k = lo; k <= hi; k++ ){
            aux[k] = a[k];
        }

        for( int k = lo; k <= hi; k++ ){
            if( i > mid ) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(aux[i] < aux[j]) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

    }


    /**
     * 快速排序
     */

    public static int[] quickSort(int[] a){
        StdRandom.shuffle( a );
        qSort( a,0,a.length - 1 );
        System.out.println(Arrays.toString( a ));
        return a;
    }

    private static void qSort(int[] a, int lo, int hi){
        if( lo >= hi ) return;
        int j = partition( a,lo,hi );
        qSort( a, lo, j - 1);
        qSort(a,j + 1, hi);
    }

    private static int partition(int[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        while(true){
            while( a[++i] <= a[lo] ){
                if( i == hi ){
                    break;
                }
            }
            while( a[--j] >= a[lo] ){
            }
            if( i >= j ){
                break;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        int temp = a[lo];
        a[lo] = a[j];
        a[j] = temp;
        return j;
    }


    /**
     * 使用二分计算一个数的平方根。
     * @param n
     * @param e
     * @return
     */

    public static double sqrt(double n, double e){
        double lo = 0;
        double hi = n;
        double mid = (lo + hi) / 2;
        while(Math.abs(Math.pow( mid, 2 ) - n) > e ){
            if( Math.pow( mid, 2 ) > n ){
                hi = mid;
            }
            else if(Math.pow( mid, 2 ) < n){
                lo = mid;
            }
            else {
                return mid;
            }
            mid = (lo + hi) / 2;
        }
        return mid;
    }
    public static void main( String[] args ){
        int[] a = {1, 5, 6, 4, 2, 46, 2, 6, 9, 10, 0, 19, 12, 7};
        //selectionSort( a );
        //insectionSrot( a );
        //shellSort( a );
        //mergeSort( a );
        quickSort( a );
        System.out.println(sqrt( 10, 0.000001 ));
    }
}
