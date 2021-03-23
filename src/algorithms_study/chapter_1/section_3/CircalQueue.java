package algorithms_study.chapter_1.section_3;

import java.util.Iterator;

/**
 * Ex29
 * 使用一个实例变量last的实现队列，只要链表部位不为，last.next指向first；
 */
public class CircalQueue<T> implements Iterable<T> {
    private int N = 0;
    private Node last = new Node();

    private class Node{
        T item;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void enQue(T item){
        if(isEmpty()){
           last.item = item;
           last.next = last;
        }else {
            Node x = new Node();
            x.item = item;
            x.next = last.next;
            last.next = x;
            last = last.next;
        }
        N++;
    }

    public T deQue(){
        if(isEmpty()){
            throw new RuntimeException("the queue is empty");
        }
        else if (size() == 1){
            T item = last.item;
            last = null;
            N--;
            return item;
        }
        else{
            T item = last.next.item;
            last.next = last.next.next;
            N--;
            return item;
        }
    }

    public Iterator<T> iterator(){
        return new CircalQueueIter();
    }

    private class  CircalQueueIter implements Iterator<T>{
        Node lastTemp = last;
        int count = N;
        @Override
        public void remove(){

        }

        @Override
        public boolean hasNext(){
            return count != 0;
        }

        @Override
        public T next(){
            T item = lastTemp.next.item;
            lastTemp = lastTemp.next;
            count--;
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
        CircalQueue<String> cq = new CircalQueue<>();
        cq.enQue("duyongyang");
        cq.enQue("duhaoyuan");
        cq.enQue("gaozhiyang");
        System.out.println(cq.toString());
        cq.deQue();
        System.out.println(cq.toString());
        cq.deQue();
        System.out.println(cq.toString());
        cq.deQue();
        System.out.println(cq.toString());
    }
}
