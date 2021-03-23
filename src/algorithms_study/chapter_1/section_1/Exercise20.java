package algorithms_study.chapter_1.section_1;

public class Exercise20 {
    /**
     * 使用递归计算ln(N!)
     * @param N
     * @return
     */
    public static double log(int N){
        if(N == 0 || N == 1) return 0;
        return log(N-1) + Math.log(N);
    }

    public static void main(String[] args) {
        System.out.println(log(2));
    }
}
