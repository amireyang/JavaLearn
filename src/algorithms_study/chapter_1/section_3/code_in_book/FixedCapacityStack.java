package algorithms_study.chapter_1.section_3.code_in_book;

import java.util.Iterator;
import java.util.Stack;

public class FixedCapacityStack<T> implements Iterable<T>{
    private T[] a;
    private int N = 0;

    public FixedCapacityStack(int cap){
        a = (T[]) new Object[cap];
    }
    public void push(T t) {
        a[N++] = t;
    }

    public T pop(){
        T item =  a[--N];
        a[N] = null;
        return item;
    }

    //返回栈顶元素而不弹出它
    public T peek(){
        int i = N;
        return a[--i];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public boolean isFull(){
        return N == a.length;
    }

    public int size(){
        return N;
    }

    public  FixedCapacityStack<T> copy(){
        FixedCapacityStack<T> fixedStack1 = new FixedCapacityStack(this.size());
        FixedCapacityStack<T> fixedStack2 = new FixedCapacityStack(this.size());

        Iterator<T> iter1 = this.iterator();
        while(iter1.hasNext()){
            fixedStack1.push(iter1.next());
        }

        Iterator<T> iter2 = fixedStack1.iterator();
        while(iter2.hasNext()){
            fixedStack2.push(iter2.next());
        }
        return fixedStack2;

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (T t : this) {
            sb.append(t + " ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new fixedCapacityOfStackIterator();
    }
    private class fixedCapacityOfStackIterator implements Iterator<T> {
        private int i = N;
        @Override
        public void remove() {
            a[--N] = null;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }
    }

    public static void main(String[] args) throws Exception {
        FixedCapacityStack<String> fixedStack = new FixedCapacityStack<>(10);
        fixedStack.push("duyongyang");
        fixedStack.push("duhaoyuan");
        fixedStack.push("quanyingqiao");
        fixedStack.push("huangchen");

        FixedCapacityStack<String> newStack = fixedStack.copy();
        newStack.pop();
        System.out.println("newStack:" + newStack.toString());
        System.out.println(fixedStack.toString());
        System.out.println(fixedStack.pop());
    }

}
