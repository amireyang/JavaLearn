package algorithms_study.chapter_1.section_3;


import java.util.Arrays;

/**
 *Ex37
 */
public class Josephus implements Runnable {
    private int N;
    private int deathNum;
    private int[] a;

    public Josephus(int total, int deathNum){
        this.N = total;
        this.deathNum = deathNum;
        a = new int[total];
        for (int i = 0; i < total; i++) {
            a[i] = i + 1;
        }
    }

    @Override
    public void run(){
        int total = N;
        while(N != 1){
            int count = 0;
            int i = -1;
            while(true){
                i++;
                if(i == a.length){
                    i = 0;
                }

                //该玩家死亡，跳过
                if (a[i] == 0){
                    continue;
                }
                else {
                    //没死亡则进行报数
                    count++;
                }
                //检测玩家是否报到deathNum，是该玩家死亡
                if(count == deathNum){
                    a[i] = 0;
                    N--;
                    break;
                }

            }
            System.out.printf("第%2d次： %s",total - N, Arrays.toString(a));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Josephus j = new Josephus(7,8);
        j.run();
    }
}
