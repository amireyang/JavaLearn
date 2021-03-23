package algorithms_study.chapter_1.section_3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex14_ResizingArrayQueue<T> implements Iterable<T> {
    private int N;
    private int maxSize;
    private int first;
    private int last;
    private T[] a = (T[]) new Object[2];

    public Ex14_ResizingArrayQueue(){
        N = 0;
        maxSize = 2;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }


    public void enQueue(T item){
        if (N == a.length) {
            resize(maxSize * 2);
        }
        a[last] = item;
        last = last + 1;
        if (last == a.length) last = 0;
        N++;
    }

    public T deQueue(){
        if(isEmpty()){
            System.out.println("the queue is empty!");
            System.exit(0);
        }
        T temp = a[first];
        a[first] = null;
        first = first + 1;
        if (first == a.length) first = 0;
        N--;
        if(N > 0 && N == a.length / 4){
            resize(maxSize / 2);
        }
        return temp;
    }

    //仔细看看这一步
    private void resize(int len){
        T[] temp = (T[]) new Object[len];
        int fi = first;
        int la = last;
        first = fi % len;
        do{
            temp[fi % len] = a[fi];
            fi++;
        }while(fi % maxSize != la);
        last = fi % len;
        a = temp;
        maxSize = len;
    }

    @Override
    public String toString() {
        return "first: " + first +
                "\nlast: " + last +
                "\na: " + Arrays.toString(a);
    }

    @Override
    public Iterator<T> iterator(){
        return new ResizingArrayQueueIterator();
    }

    private class ResizingArrayQueueIterator implements Iterator<T> {
        private int fi = first;
        private int la = last;
        @Override
        public void remove(){

        }

        @Override
        public boolean hasNext(){
            return fi % maxSize != la % maxSize;
        }

        @Override
        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            T temp = a[fi % maxSize];
            fi++;
            return temp;
        }

    }

    public static void main(String[] args) {
       Ex14_ResizingArrayQueue<String> resizeingQueue = new Ex14_ResizingArrayQueue<>();
        resizeingQueue.enQueue("duyongyang");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("duhaoyuan");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("quanyingqiao");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("quanyingqiao");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("wangweiqin");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("huangchen");
        System.out.println(resizeingQueue.toString());
        resizeingQueue.enQueue("wangweiqin");


        for (int i = 0; i < 7; i++) {
            System.out.println("出队" + resizeingQueue.deQueue());
            System.out.println(resizeingQueue.toString());
            System.out.println();
        }


    }

}
