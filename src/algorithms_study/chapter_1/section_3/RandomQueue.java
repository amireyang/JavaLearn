package algorithms_study.chapter_1.section_3;

import java.util.Arrays;
import java.util.Iterator;

/**
 *Ex35、36
 */
public class RandomQueue<T> implements Iterable<T> {
    private int N = 0;
    private T[] a = (T[]) new Object[2];
    private int first = 0;
    private int last = 0;

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 使用尾插法入队,当数组满时，调整数组大小为原来的2倍；
     */
    public void enqueue(T item){
        if(N == a.length){
            resize(2 * a.length);
        }
        a[last] = item;
        last++;
        if (last % a.length == 0){
            last = 0;
        }
        N++;
    }

    public T dequque(){
        if (isEmpty()){
            throw new RuntimeException("the RandomQueue is empty.");
        }
        if(N == 1 || N == a.length){
            N--;
            return a[N];
        }
        int r = (int)(Math.random() * N);
        T xitem = a[r];
        a[r] = a[N - 1];
        a[N - 1] = null;
        N--;
        last--;
        if(N >0 && N == a.length / 4){
            resize(a.length /2);
        }
        return xitem;
    }

    /**
     * 动态调整数组大小
     */
    private void resize(int len){
        T[] x = (T[]) new Object[len];
        int firstTemp = this.first;
        first = firstTemp % len;
        do{
            x[firstTemp % len] = a[firstTemp % a.length];
            firstTemp ++;
        }while(firstTemp % a.length != last);
        a = x;
        last = firstTemp % len;
    }

    /**
     * 随机返回一个元素但不删除
     */

    public T sample(){
        int r = (int)(Math.random() * N);
        return a[r];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(a[i]).append(" ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator(){
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<T>{

        int[] num = new int[size()];//存储换回元素的下标
        {
            for (int i = 0; i < size(); i++) {
                num[i] = i;
            }
        }

        int count = N;
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasNext(){
            return count != 0;
        }

        @Override
        public T next(){
            int r = (int)(Math.random() * count);
            T item = a[num[r]];
            int temp = num[r];
            num[r] = num [count - 1];
            num[count - 1] = num[r];
            count--;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomQueue<String> rq = new RandomQueue<>();
        rq.enqueue("duyongyang");
        rq.enqueue("duhaoyuan");
        rq.enqueue("quanyingqiao");
        rq.enqueue("huangsong");

        Iterator iter = rq.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }

}
