package algorithms_study.chapter_1.section_3.code_in_book;

import java.util.Iterator;

public class ResizingArraryStack<T> implements Iterable<T>{
    private int N = 0;
    private T[] a = (T[]) new Object[1];

    public void push(T t){
        if(N == a.length){
            resize(2 * a.length);
        }
        a[N++] = t;
    }

    public T pop() throws Exception{
        if(N == 0){
            throw new Exception("the stack is empty");
        }
        T item = a[--N];
        a[N] = null; //避免对象游离
        if( N == a.length / 4){
            resize(a.length / 2);
        }
        return item;
    }

    private void resize(int n) {
        T[] temp = (T[]) new Object[n];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public int aSize(){
        return a.length;
    }

    @Override
    public Iterator<T> iterator(){
        return new ResizingArraryStackIterator();
    }
    private class ResizingArraryStackIterator implements Iterator<T>{
        int i = N;
        @Override
        public void remove(){

        }

        @Override
        public boolean hasNext(){
            return i > 0;
        }

        @Override
        public T next(){
            return a[--i];
        }
    }

    public static void main(String[] args) throws Exception {
        ResizingArraryStack<String> reSizingStack = new ResizingArraryStack<>();
        System.out.println("a.length = " + reSizingStack.aSize());
        reSizingStack.push("duyongyang");
        reSizingStack.push("duhaoyuan");
        System.out.println("a.length = " + reSizingStack.aSize());
        reSizingStack.push("quanyingqiao");
        System.out.println("a.length = " + reSizingStack.aSize());

        reSizingStack.pop();
        reSizingStack.pop();
        System.out.println("a.length = " + reSizingStack.aSize());
        reSizingStack.pop();
        System.out.println("a.length = " + reSizingStack.aSize());
    }
}
