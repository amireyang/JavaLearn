package algorithms_study.chapter_1.section_1;

public class Exercise13_ArrayReverse {
    /**
     * 打印一个二维数组的转置
     * @param arr 一个二维数组
     */
    public static void printArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%4d",arr[i][j]);
                if (j == arr[i].length-1) {
                    System.out.println();
                }
            }
        }
        System.out.println("数组的转置：");
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%4d", arr[j][i]);
                if (j == arr.length-1) {
                    System.out.println();
                }
            }
        }
    }

    //测试
    public static void main(String[] args) {
        final int m = 10;
        final int n = 5;
        int[][] arr = new int[m][n];
        //初始化数组
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] =(int)( Math.random() * 101);
            }
        }
        //打印数组及数组的转置
        printArray(arr);
    }

}
