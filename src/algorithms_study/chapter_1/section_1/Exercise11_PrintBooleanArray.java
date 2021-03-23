package algorithms_study.chapter_1.section_1;

//打印一个二维布尔数组，用*表示真，空格表示假
public class Exercise11_PrintBooleanArray {
    public static void main(String[] args) {
        final int M = 10;
        final int N = 10;
        boolean[][] booArray = new boolean[M][N];
        //以0.5的概率初始化一个boolean数组
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                double random = Math.random();
                booArray[i][j] = (random >= 0.5);
            }
        }
        System.out.print(" ");
        //打印列号
        for (int i = 0; i < M; i++) {
            System.out.printf("%3d", i+1);
        }
        System.out.println();
        for (int i = 0; i < M; i++) {
            //打印行号
            System.out.printf("%d", i+1);
            for (int j = 0; j < N; j++) {
                if (booArray[i][j]){
                    System.out.print("  *");
                }else{
                    System.out.print("   ");
                }
                if(j == N -1){
                    System.out.println();
                }
            }
        }

    }
}
