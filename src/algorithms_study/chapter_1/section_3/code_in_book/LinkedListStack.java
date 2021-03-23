package algorithms_study.chapter_1.section_3.code_in_book;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Iterable<T>{
    private int N;
    private Node first;
    private class Node{
        T item;
        Node next;
    }
    //默认构造器，对所有域进行赋予默认值
    public LinkedListStack(){

    }

    public boolean isEmpty(){
        return first == null; // or return N == 0
    }

    public int size(){
        return N;
    }

    public void push(T item) {
        Node oldFist = first;
        first = new Node();
        first.item = item;
        first.next = oldFist;
        N++;
    }

    public T pop(){
        T item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public T peek(){
        return first.item;
    }


    @Override
    public Iterator<T> iterator(){
        return new LinkedListStackIterator();
    }

    private class LinkedListStackIterator implements Iterator<T>{
        private Node temp = first;
        @Override
        public void remove(){

        }

        @Override
        public boolean hasNext(){
            return temp != null;
        }

        @Override
        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            T s =  temp.item;
            temp = temp.next;
            return s;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(T t : this){
            sb.append(t + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<String> linkListStack = new LinkedListStack<>();
        linkListStack.push("duyongyang");
        linkListStack.push("duhaoyuan");
        linkListStack.push("quanyianqiao");
        for (String s : linkListStack) {
            System.out.println(s);
        }
    }
}
