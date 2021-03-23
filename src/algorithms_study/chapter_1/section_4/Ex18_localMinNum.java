package algorithms_study.chapter_1.section_4;

public class Ex18_localMinNum {
    public static int localMinNum(int[] a){
        int lo = 0;
        int hi = a.length;
        int localMInNum = -1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            int min = mid;
            if (mid != lo && a[min] >= a[mid - 1]){
                min = mid -1;
            }
            if (mid != hi && a[min] >= a[mid + 1]){
                min = mid + 1;
            }

            if(min == mid)
                return min;
            else if(min > mid){
                lo = min;
            }
            else{
                hi = min;
            }
        }
        return - 1;
    }

    public static void main(String[] args) {
        int[] a = { 5, 3, 1, 7, 8, 9,2,10};
        int index = localMinNum(a);
        if (index >= 0){
            System.out.println("local min number " + a[index]);
        }
        else{
            System.out.println("null");
        }
    }
}
