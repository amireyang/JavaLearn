package algorithms_study.chapter_2;

import java.util.LinkedList;

public class StackSort{
    public static void swim(int[] a, int k){
        while(k > 1 && a[k] > a[k / 2]){
            int temp = a[k];
            a[k] = a[k / 2];
            a[k / 2] = temp;
            k /= 2;
        }
    }

    public static int[] sink(int[] a, int k){
        while( 2 * k <= a.length - 1){
            int j = 2 * k;
            if (j < a.length - 1 && a[j] < a[j + 1]){
                j++;
            }
            if (a[k] > a[j]) break;
            else {
                int temp = a[k];
                a[k] = a[j];
                a[j] = temp;
            }
            k = j;
        }
        return a;
    }


    public static int delete(int[] a){
        int temp = a[1];
        a[1] = a[a.length -1];
        sink(a, 1);
        return temp;
    }

    public static void main(String[] args){
        int i = 1;
        System.out.println((++i) + (++i));
        LinkedList<? super Apple> ll = new LinkedList<>();
        ll.add(new Apple());

    }


}

class Fruit{
    private int number;
    public Fruit(){

    }

    public void eat(){
        System.out.println("吃水果");
    }

    public void setNumber(int number){
        this.number = number;
    }
}

class Apple extends Fruit{
    public Apple(){
        super();
    }

    public void eat(){
        System.out.println("吃苹果");
    }

}

class FuShiApple extends Apple{

}