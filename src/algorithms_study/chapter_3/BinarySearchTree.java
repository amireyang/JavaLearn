package algorithms_study.chapter_3;

import com.sun.jdi.PathSearchingVirtualMachine;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree< T extends Comparable<T>, V>{
    private Node root;

    private class Node{
        private T key;
        private V value;
        private Node left, right;
        private int N;

        public Node(T key, V value, int N){
            this.key = key;
            this. value = value;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null){
            return 0;
        } else{
            return x.N;
        }
    }

    public V get(T key){
        return get(root, key);
    }

    private V get(Node x, T key){
        if (x == null){
            return null;
        }
        int com = key.compareTo(x.key);
        if (com < 0){
            return get(x.left, key);
        } else if (com > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public void put(T key, V value){
        root = put(key, value, root);
    }

    private Node put(T key, V value, Node x){
        if (x == null){
            return new Node(key, value, 1);
        }
        int com = key.compareTo(x.key);
        if (com < 0) x.left = put(key, value, x.left);
        else if (com > 0) x.right = put(key, value, x.right);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public T max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null) return x;
        else{
            return max(x.right);
        }
    }

    public T min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        else {
            return min(x.left);
        }
    }

    public T floor(T key){
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, T key){
        if (x == null) return null;
        int com = key.compareTo(x.key);
        if (com == 0) return x;
        else if (com < 0) return floor(x.left, key);
        else {
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        }
    }

    public int rank(T key){
        return rank(key, root);
    }

    public int rank(T key, Node x){
        if (x == null) return 0;
        int com = key.compareTo(x.key);
        if (com == 0) return size(x.left);
        else if (com < 0) return rank(key, x.left);
        else return x.left.N + 1 + rank(key, x.right);
    }

    public T select(int n){
        Node x = select(n, root);
        if (x == null) return null;
        else return x.key;
    }

    private Node select(int n, Node x){
        if (x == null) return null;
        if (size(x.left) == n) return x;
        else if (size(x.left) > n) return select(n, x.left);
        else return select(n - size(x.left) - 1, x.right);
    }

    public void deleteMin(){
        root = deleteMin(root);

    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if (x.right == null) return null;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(T key){
        root = delete(root, key);
    }

    private Node delete(Node x, T key){
        if (x == null) return null;
        int com = key.compareTo(x.key);

        if (com > 0){
            x.right = delete(x.right, key);
        }
        else if (com < 0){
            x.left = delete(x.left, key);
        }
        else {
           if (x.right == null) return x.left;
           if (x.left == null) return x.right;
           Node t = x;
           x = min(t.right);
           x.right = deleteMin(t.right);
           x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void print(){
        print(root);
    }

    private void print(Node x){
        if (x == null) return;
        System.out.println(x.key);
        print(x.left);
        print(x.right);
    }

    public Iterable<T> keys(){
        return keys(min(), max());
    }

    public Iterable<T> keys(T lo, T hi){
        Deque<T> queue = new ArrayDeque<T>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Deque<T> queue, T lo, T hi){
        if (x == null) return;
        int comLo = lo.compareTo(x.key);
        int comHi = hi.compareTo(x.key);
        if (comLo < 0){
            keys(x.left, queue, lo, hi);
        }
        else if (comLo <= 0 && comHi >= 0){
            queue.addLast(x.key);
        }
        else {
            keys(x.right, queue, lo, hi);
        }
    }

    
    @Override
    public String toString() {
        String s = "";
        Deque<Node> queue = new ArrayDeque<Node>();
        queue.addLast(root);
        while(!queue.isEmpty()){
            Node x = queue.pollFirst();
            if (x.left != null) {
                queue.addLast(x.left);
            }
            if (x.right != null) {
                queue.addLast(x.right);
            }
            s += x.key.toString() + " ";
        }
        return s;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree<String, Integer>();
        bst.put("duyongyang", 12);
        bst.put("duhaoyuan", 3);
        bst.put("quanyingqiao", 34);
        bst.put("gaozhiyang",33);
        bst.put("huangsong",33);
        bst.print();
    }

}
