package algorithms_study.chapter_1.section_1;

public class Exercise14 {

    /**
     * 计算2的N次方
     * @param N 要计算的次方
     * @return 返回2的N次方
     */
    public static int pow(int N){
        int sum = 1;
        for (int i = 0; i < N; i++) {
            sum *= 2;
        }
        return sum;
    }

    /**
     * 静态方法lg()接受一个整形参数N，返回不大于log2 N的最大整数。不适用Math库
     * @param N
     * @return
     */
    public static int lg(int N){
        if (N <=0) {
            throw new IllegalArgumentException();
        }

        int[] arr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int lo = 0;
        int hi = arr.length-1;
        int mid = (lo + hi) / 2;
        while(lo < hi){
            if(pow(arr[mid]) == N) {
                break;
            }
            else if(pow(arr[mid]) < N){
                lo = mid + 1;
                mid = (lo + hi) / 2;
            }
            else{
                hi = mid -1;
                mid = (lo + hi) / 2;
            }

        }
        if (pow(arr[mid]) > N){
            return --arr[mid];
        }

        return arr[mid];

    }

    public static void main(String[] args) {
        System.out.println(lg(100));
    }
}
