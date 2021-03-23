package algorithms_study.chapter_1.section_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleNodeLinkedList<T> implements Iterable<T> {
    private int N = 0;
    private Node first = null;
    private Node last = null;

    private class Node {
        T item;
        Node prior;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFromHead(T item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            last.prior = null;
            last.next = null;
        } else {
            Node x = new Node();
            x.item = item;
            x.next = first;
            x.prior = null;
            first.prior = x;
            first = first.prior;
        }
        N++;
    }

    public void addFromRear(T item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            first.prior = null;
            first.next = null;
        } else {
            Node x = new Node();
            x.item = item;
            x.prior = last;
            x.next = null;
            last.next = x;
            last = last.next;
        }
        N++;
    }

    public T deleFromHead() {
        if (isEmpty()) {
            throw new RuntimeException("thie list is empty");
        } else if (N == 1) {
            T item = first.item;
            first = null;
            last = null;
            N--;
            return item;
        } else {
            T item = first.item;
            Node firstTemp = first.next;
            first.next = null;
            first = firstTemp;
            first.prior = null;
            N--;
            return item;
        }
    }

    public T deleFromRear() {
        if (isEmpty()) {
            throw new RuntimeException("thie list is empty");
        } else if (N == 1) {
            T item = first.item;
            first = null;
            last = null;
            N--;
            return item;
        } else {
            T item = last.item;
            Node lastPrior = last.prior;
            last.prior =null;
            last = lastPrior;
            last.next = null;
            N--;
            return item;
        }
    }

    public T deleNode(Node x){
        if (isEmpty()){
            throw new RuntimeException("the list is empty");
        }
        if(x == first){
            T item = first.item;
            deleFromHead();
            return item;
        }
        else if(x == last ){
            T item = last.item;
            deleFromRear();
            return item;
        }
        else{

            Node xPrior = x.prior;
            Node xNext = x.next;
            xPrior.next = xNext;
            x.next.prior = xPrior;
            N--;
            return x.item;
        }

    }

    public void addBeforeNode(Node x, T item){
        if (x == first){
            addFromHead(item);
        }
        else{
            Node temp = new Node();
            temp.item = item;
            Node xPrior = x.prior;
            xPrior.next = temp;
            temp.prior = xPrior;
            temp.next = x;
            x.prior = temp;
            N++;
        }
    }

    public void addAfterNode(Node x, T item){
        if(x == this.last){
            addFromRear(item);
        }
        else{
            Node temp = new Node();
            temp.item = item;
            Node xNext = x.next;
            x.next = temp;
            temp.prior = x;
            temp.next = xNext;
            xNext.prior = temp;
            N++;
        }
    }

    public Iterator<T> iterator(){
        return new DoubleListIter();
    }

    private class  DoubleListIter implements Iterator<T>{
        Node temp = first;
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
            if (!hasNext()) throw new NoSuchElementException();
            T item = temp.item;
            temp = temp.next;
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
        DoubleNodeLinkedList<String> dll = new DoubleNodeLinkedList<>();
        dll.addFromRear("duyongyang");
        dll.addFromRear("duhaoyuan");
        System.out.println(dll.toString());
        dll.addFromHead("quanyingqiao");
        dll.addFromHead("huangchen");
        System.out.println(dll.toString());

        dll.deleFromRear();
        dll.deleFromHead();
        System.out.println(dll.toString());

        dll.addAfterNode(dll.first, "huangsong");
        System.out.println(dll.toString());

        dll.addBeforeNode(dll.first, "gaozhiyang");
        System.out.println(dll.toString());

        dll.deleNode(dll.first.next.next);
        System.out.println(dll.toString());
    }
}
