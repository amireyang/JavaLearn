package algorithms_study.chapter_1.section_4;

public class Ex20_DualSearch {
    //双调查找，对比key和局部最大值的大小，然后在相应的区间进行二分查找
    public static int dualSearch(int[] a, int key){
        int index = localMaxNum(a);
        if(index < 0){
            throw new RuntimeException("this array is not dual");
        }

        int lo1 = 0;
        int hi1 = index;
        int hi2 = index;
        int lo2 = a.length - 1;

        //在左边查找
        while(lo1 <= hi1) {
            int mid1 = (lo1 + hi1) / 2;
            if (key < a[mid1]) {
                hi1 = mid1 - 1;
            } else if (key > a[mid1]) {
                lo1 = mid1 + 1;
            } else {
                return mid1;
            }
        }
        //z在右边查找
        while(lo2 >= hi2){
            int mid2 = (lo2 + hi2) / 2;
            if (key < a[mid2]){
               hi2 = mid2 - 1;
           }
           else if(key > a[mid2]){
                lo2 = mid2 + 1;
           }else{
               return mid2;
           }
        }
        return -1;
    }

    private static int localMaxNum(int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            int max = mid;
            if(mid > 0 && a[max] < a[mid - 1]){
                hi = mid;
            }
            else if(mid <a.length - 1 && a[max] < a[mid + 1]){
                lo = mid;
            }
            else{
                return mid;
            }


        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,2, 3, 6,7, 5, 0};
        System.out.println(localMaxNum(a));
        System.out.println(dualSearch(a, 5));
    }

}
