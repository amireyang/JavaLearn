package algorithms_study.chapter_1.section_3;

/**
 * Ex32
 */
public class Steque<T> {
    private int N;
    private Node first;
    private Node last;

    private class Node{
        T item;
        Node prior;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 添加一个元素
     * @param item 要添加的一个元素
     */
    public void push(T item){
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            last.prior = null;
            last.next = null;
        } else {
            Node x = new Node();
            x.item = item;
            x.prior = null;
            x.next = first;
            first.prior = x;
            first = first.prior;
        }
        N++;
    }


    /**
     * 删除首节点并返回
     * @return
     */
    public T pop(){
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

    public void enqueue(T item){
        Node x = new Node();
        x.item = item;
        if(isEmpty()){
            first = x;
            last = x;
        }else{
            x.next = null;
            x.prior = last;
            last.next = x;
            last = last.next;
        }
        N++;
    }



}
