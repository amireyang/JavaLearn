package algorithms_study.chapter_2;

public class PriorityQue<Item extends Comparable>{
    //用数组来存储完全二叉树，数组中位置为k的节点，子节点为2k和2k+1
    private Item[] pq;
    int N = 0;

    public PriorityQue(int maxN){
        pq = (Item[]) new Comparable[maxN];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void add(Item item){
        pq[++N] = item;
        swim( N );
    }

    public Item delMax(){
        Item max = pq[1];
        pq[1] = pq[N];
        pq[N--] = null;
        sink( 1 );
        return max;
    }

    //父节点小于子节点，下沉
    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            if( j + 1 <= N && pq[j].compareTo( pq[j + 1] ) <= 0 ){
                    j++;
            }
            //子结点大于父结点
            if(pq[j].compareTo( pq[k] ) <= 0){
                return;
            }
            Item temp = pq[j];
            pq[j] = pq[k];
            pq[k] = temp;
            k=j;
        }
    }

    //子结点大于父结点，上浮
    private void swim(int k){
        while(k > 1 && pq[k].compareTo( pq[k/2] ) > 0){
            Item temp = pq[k/2];
            pq[k/2] = pq[k];
            pq[k] = temp;

            k /= 2;
        }
    }

    public void show(){
        for( int i = 1; i <= N; i++ ){
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }

    public static void main( String[] args ){
        PriorityQue<Integer> pq = new PriorityQue<>( 10 );
        pq.add( 3 );
        pq.add( 5 );
        pq.add( 2 );
        pq.add( 17 );
        pq.add( 10 );
        pq.add( 20 );
        pq.add( 32 );
        pq.show();
        System.out.println("delete max: " + pq.delMax());
        System.out.println("delete max: " + pq.delMax());
        pq.show();
    }
}
