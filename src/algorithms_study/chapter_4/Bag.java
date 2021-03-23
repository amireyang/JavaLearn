package algorithms_study.chapter_4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *Ex34
 */
public class Bag<T> implements Iterable<T> {
    private int N = 0;
    private T[] a = (T[]) new Object[2];

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void add(T t){
        if(N == a.length){
            resize(2 * a.length);
        }
        a[N] = t;
        N++;
    }

    private void resize(int len){
        T[] aNew = (T[]) new Object[len];
        for (int i = 0; i < a.length; i++) {
            aNew[i] = a[i];
        }
        a = aNew;
    }

   @Override
    public Iterator<T> iterator(){
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<T>{
        int r = N;
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return r > 0;
        }

        @Override
        public T next(){
            if(isEmpty()){
                throw new NoSuchElementException();
            }
            int randomNum = (int)(Math.random() * r);
            T item = a[randomNum];
            if(randomNum != r -1) {
                a[randomNum] = a[r - 1];
                a[r - 1] = item;
            }
            r--;
            return item;
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        Iterator iter = this.iterator();
        s.append("[ ");
        while(iter.hasNext()){
            s.append(iter.next()).append(" ");
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        Bag<String> rb = new Bag<>();
        rb.add("duyongyang");
        rb.add("duhaoyuan");
        rb.add("quanyingqiao");
        rb.add("gaozhiyang");
        rb.add("huangsong");
        for (int i = 0; i < 5; i++){
            System.out.println(rb.toString());
        }
    }

}
