package algorithms_study.chapter_1.section_1;

public class Exercise24_gcd {
    public static int gcd(int p, int q){
        System.out.println("p: " + p + " q: " + q);
        if(q == 0) { return p; }
        int r = p % q;
        return gcd(q,r);
    }

    public static void main(String[] args) {
        gcd(5, 10);
    }
}
