package algorithms_study.chapter_1.section_3;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Ex_19 delete the last node
 * Ex_20 delete NO.K element
 * Ex_21 find(MyLinkedlist<T> l, T key)
 * Ex_24 removeAfter()
 */
public class MyLinkedList<T> implements Iterable<T> {
    private int N;
    private Node first;
    private Node last;

    private class Node{
        private T item;
        private Node next;
    }

    public MyLinkedList(){
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
    public void append(T item){
        Node x = new Node();
        x.item = item;
        if(isEmpty()){
            first = x;
            last = x;
        }else{
            last.next = x;
            last = last.next;
        }
        N++;
    }


    /**
     * 删除首节点并返回
     * @return
     */
    public T delete(){
        if (N == 1) {
            T item = first.item;
            first = null;
            last = null;
            N--;
            return item;
        }
        T item = first.item;
        first= first.next;
        N--;
        return item;
    }

    /**
     * 删除链表第K个元素，如果它存在的话
     * @param K 第K个元素
     */
    public T delete(int K){
        if(N >= K){
            if (N == K){
                deleteLast();
            }
            Node temp = first;
            for (int i = 0; i < K - 1; i++) {
                temp = temp.next;
            }
            T item = temp.next.item;
            temp = temp.next.next;
            N--;
            return item;
        }
        return null;
    }

    public T deleteLast(){

        if (N == 1){
            first = null;
            last = null;
        }
        Node temp = first;
        for (; temp.next.next != null; temp = temp.next){

        }
        T item = temp.next.item;
        temp.next = temp.next.next;
        last = temp;
        N--;
        return item;
    }

    public boolean find(MyLinkedList<T> l, T key){
        for (Node temp = l.first; temp != null; temp = temp.next){
            if (temp.item == key){
                return true;
            }
        }
        return false;
    }


    public void removeAfter(Node node){
        if(node != null && node.next != null){
            int count = 0;
            for(Node temp = node.next; temp != null; temp = temp.next) {
                count++;
            }
            N -= count;
            node.next = null;
            this.last = node;
        }
    }

    public void insertAfter(Node fir, Node sec){
        if (fir != null && sec != null){
            Node temp = sec;
            if (fir == last){
                fir.next = sec;
                sec.next = null;
                last = sec;
            }
            sec.next = fir.next;
            fir.next = sec.next;
            N++;
        }
    }

    public void remove(MyLinkedList<T> l, T key){
        int count = 0;
        Node fir = new Node();
        Node las = fir;
        for (Node temp = l.first; temp != null; temp = temp.next){
            if (!temp.item.equals(key)){
                if(count == 0){
                    fir = temp;
                    las = temp;
                }
                else{
                    las.next = temp;
                    las = las.next;
                }
                count ++;
            }
        }
        if(las.next != null){
            las.next = null;
        }
        first = fir;
        last = las;
        N = count;
     }

    /**
     * 找出链表中最大的值
     * T必须实现了Comparable接口
     *
     */
     public  T max(Node first){
        if(isEmpty()){return null;}
        T item = first.item;
        for(Node temp = first.next; temp != null; temp = temp.next){
            //进行类型转换
            if(((Comparable)temp.item).compareTo(item) > 0){
                item = temp.item;
            }
        }

        return item;
     }


    /**
     * 递归实现max
     */

    private T maxRec(Node first){
        //结束条件
        if (first == null){
            return null;
        }
        if( first.next == null){
            return first.item;
        }
        //等价关系
        T item = maxRec(first.next);
        if (((Comparable)item).compareTo(first.item) < 0){
            item = first.item;
        }
        return item;
    }

    /**
     *将链表反转（破坏性的）
     */
    public Node reverse(Node first){
        if( first.next == null){
            return first;
        }
        Node oldFirst = first;
        Node reverse = null;
        while(first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        this.first = reverse;
        this.last = oldFirst;
        return reverse;
    }

    public Node reverseRcur(Node first){
        Node newFirst = first;
        while(newFirst.next != null){
            newFirst = newFirst.next;
        }
        this.last = reverseRcurHelper(first);
        this.first = newFirst;
        return newFirst;
    }

    private  Node reverseRcurHelper(Node head){
        if (head == null || head.next == null){
            return head;
        }

        Node temp = reverseRcurHelper(head.next);
        temp.next = head;
        head.next = null;
        return head;

    }

    @Override
    public Iterator<T> iterator(){
        return new MyLinkedListIter();
    }

    private class MyLinkedListIter implements Iterator<T>{
        Node firstTemp = first;
        @Override
        public void remove(){

        }

        @Override
        public boolean hasNext(){
            return N != 0;
        }

        @Override
        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            T item =  firstTemp.item;
            firstTemp = firstTemp.next;
            N--;
            return item;
        }

    }
     @Override
     public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Node temp = first; temp != null; temp = temp.next){
            sb.append(temp.item).append(" ");
        }
        sb.append("]");
        return sb.toString();
     }

    public static void main(String[] args) {
        MyLinkedList<String> l = new MyLinkedList<>();
        l.append("duyongyang");
        l.append("duhaoyuan");
        l.append("gaozhiyang");
        l.append("huangsong");
        l.append("wangweiqin");
        l.append("zzzzz");
        System.out.println(l.toString());
        //普通反转
        l.reverse(l.first);
        System.out.println("反转" + l.toString());
        System.out.println(l.N);
        //递归反转
        l.reverseRcur(l.first);
        System.out.println("递归反转" + l.toString());
        System.out.println(l.N);
        //删除wangweiqin
        l.remove(l,"wangweiqin");
        System.out.println("删除wangweiqin" + l.toString());
        System.out.println(l.N);
        l.removeAfter(l.first.next.next);
        System.out.println("删除gaozhiyang之后的所有节点：" + l.toString());
        System.out.println(l.N);
        System.out.println(l.N);
        System.out.println();
        Iterator iter = l.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

}
