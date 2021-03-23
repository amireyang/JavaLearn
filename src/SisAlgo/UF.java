package SisAlgo;

public class UF {
    private int count; //联通分量的数量
    private int[] id; //分量数量

    public UF(int N){
        id = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            id[i] = i;
        }
    }

    //读入数组进行初始化
    public UF(int[] x){

    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    //返回p所在联通分量的标识
    public int find(int p){
        return id[p];
    }

    public void union(int p, int q){
        if (!connected(p,q)){
            int pid = id[p];
            int qid = id[q];

            for (int i = 1; i < id.length; i++) {
                if (id[i] == qid){
                    id[i] = pid;
                }
            }
            count--;
        }
    }

    public static void main(String[] args) {

    }
}
