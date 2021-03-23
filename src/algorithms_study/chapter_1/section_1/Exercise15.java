package algorithms_study.chapter_1.section_1;

import java.util.HashMap;
import java.util.Map;

public class Exercise15 {
    /**
     * 接受一个整形数组a[]和一个整数M，返回一个大小为M的数组，
     * 其中第i个元素为整数i在参数数组中出现的次数；
     * @param a
     * @param M
     * @return
     */
    public static int[] histogram(int[] a, int M){
        int max = a[0];
        int min = a[0];
        //找到数组中的最大值和最小值
        for (int i = 1; i < a.length; i++) {
            if(a[i] > max) {
                max = a[i];
            }
            if(a[i] < min){
                min = a[i];
            }
        }
        //利用最大值和最小值初始化Map;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = min; i <= max; i++) {
            map.put(i, 0);
        }

        //统计数组a中每一个数出现的次数
        for (int i = 0; i < a.length; i++) {
            int temp = map.get(a[i]) + 1;
            map.put(a[i], temp);
        }

        //构建结果数组
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            if(map.get(i) != null) {
                result[i] = map.get(i);
            }else{
                result[i] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final int M = 20;
        int[] arr = {1,18,4,13,7,12,2,5,9,6,10};
        int[] result = histogram(arr, M);
        for (int i : result) {
            System.out.printf("%3d", i);
        }

    }

}
