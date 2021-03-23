package algorithms_study.chapter_1.section_2;

import edu.princeton.cs.algs4.StdDraw;

public class Ex10_VisualCounter {


    public static void main(String[] args) throws Exception {
        VisualCounter vs = new VisualCounter(10,8);
        for (int i = 1; i <= 10; i++) {
            if(i % 3 == 0){
                vs.increment();
            }else{
                vs.decrement();
            }
        }
    }


}

class VisualCounter{
    private int N;
    private int max;
    private int count;
    private int flag;

    public VisualCounter(){
    }

    public VisualCounter(int N, int max){
        count = 0;
        flag = 0;
        this.N = N;
        this.max = max;
        StdDraw.setXscale(-5.0, 5.0);
        StdDraw.setYscale(-5.0, 5.0);
    }

    public void increment() throws Exception {
        if(count <= N){
            count++;
            flag++;
            StdDraw.text(0,5.0 - 0.5 * flag, "the count is" + count);
        }else{
            throw new Exception("the count is more than the max operation values");
        }
    }

    public void decrement() throws Exception{
        if(Math.abs(count) <= max){
            count--;
            flag++;
            StdDraw.text(0, 5.0 - 0.5 * flag, "the count is" + count);
        }else{
            throw new Exception("the abs of count is maore than the max");
        }
    }
}