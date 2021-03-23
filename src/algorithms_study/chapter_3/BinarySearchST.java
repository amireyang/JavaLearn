package algorithms_study.chapter_3;

import edu.princeton.cs.algs4.BinarySearch;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchST<K extends Comparable<K>, V> implements Iterable<K>{
    private int N;
    private K[] keys;
    private V[] vals;

    public BinarySearchST(){
        keys = (K[]) new Comparable[2];
        vals = (V[]) new Object[2];
        N = 0;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int rank(K k){
        int lo = 0, hi = N - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if (keys[mid].compareTo(k) == 0){
                return mid;
            }
            else if (keys[mid].compareTo(k) < 0){
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    public V get(K k){
        if (isEmpty()){
            return null;
        }
        int r = rank(k);
        if (r < N && keys[r].compareTo(k) == 0){
            return vals[r];
        }
        else {
            return null;
        }
    }

    public boolean contains(K k){
        if (isEmpty()){
            return false;
        }
        int r = rank(k);
        return r < N && keys[r].compareTo(k) == 0;
    }

    private void resize(int length){
        K keyTemp[] = (K[]) new Comparable[length];
        V valueTemp[] = (V[]) new Comparable[length];
        for (int i = 0; i < N; i++){
            keyTemp[i] = keys[i];
            valueTemp[i] = vals[i];
        }
        keys = keyTemp;
        vals = valueTemp;
    }

    public void put(K k, V v){
        if (N == keys.length){
            resize(keys.length * 2);
        }
        int r = rank(k);
        if (r < N && keys[r].compareTo(k) == 0){
            vals[r] = v;
            return;
        }
        for (int i = N; i > r; i--){
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }
        keys[r] = k;
        vals[r] = v;
        N++;
    }

    public void delete(K k){
        if (contains(k)){
            int r = rank(k);
            for (int i = r; i < N - 1; i++){
                keys[i] = keys[i + 1];
                vals[i] = vals[i + 1];
            }
            N--;
        }

        if (N > 0 && N == keys.length / 4){
            resize(keys.length / 2);
        }
    }

    public K min(){
        if (isEmpty()){
            return null;
        }
        return keys[0];
    }

    public K max(){
        if (isEmpty()){
            return null;
        }
        return keys[N - 1];
    }

    public K select(int n){
        if (n > N || n < 0){
            return null;
        }
        return keys[n - 1];
    }

    public K celling(K k){
        if (contains(k)){
            return k;
        }
        int r = rank(k);
        if (r == N){
            return null;
        }
        else {
            return keys[r];
        }

    }

    public K floor(K k){
        if (contains(k)){
            return k;
        }
        int r = rank(k);
        if (r == 0){
            return null;
        } else{
            return keys[r - 1];
        }
    }

    @Override
    public  Iterator<K> iterator(){
        return new StIterator();
    }

    private class StIterator implements Iterator<K>{
        int r = 0;
        @Override
        public void remove(){
            delete(keys[r - 1]);
        }

        @Override
        public boolean hasNext(){
            return r < N;
        }

        @Override
        public K next(){
            if (isEmpty()){
                throw new NoSuchElementException();
            }
            return keys[r++];
        }
    }


    public static void main(String[] args){
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        st.put("L", 9);
        st.put("Y", 99);
        st.put("M", 999);
        st.put("R",9999);
        for (String s : st){
            System.out.println(s);
        }
    }

}
