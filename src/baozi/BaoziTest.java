package baozi;
import javax.swing.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaoziTest {


    public static void main(String[] args) {
        Baozi baozi = new Baozi("薄皮","猪肉大葱");
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(new Thread(new Baozipu(baozi)));
        es.submit(new Thread(new Customer(baozi)));
    }

}

class Baozi{
    private String pi;
    private String xian;
    private boolean flag;
    public Baozi(){
    }

    public Baozi(String pi, String xian){
        this.pi = pi;
        this.xian = xian;
        this.flag = false;
    }

    public boolean getFlag(){
        return flag;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public void setPi(String pi){
        this.pi = pi;
    }

    public void setXian(String xian){
        this.xian = xian;
    }

    @Override
    public String toString(){
        return pi + xian;
    }

}

class Baozipu implements Runnable{
    private Baozi baozi;
    public int count =0;

    public Baozipu(Baozi baozi){
        this.baozi = baozi;
    }


    @Override
    public void run(){
        while(true){
            synchronized (baozi){
                if(!baozi.getFlag()){
                    if(count % 2 == 1) {
                        baozi.setPi("冰皮");
                        baozi.setXian("猪肉大葱");

                    }else{
                        baozi.setPi("薄皮");
                        baozi.setXian("韭菜鸡蛋");
                    }
                    System.out.println("做"+baozi.toString() + "馅的包子");
                    count++;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("包子做好了，可以取餐了");
                    baozi.setFlag(true);
                    baozi.notify();
                }
            }
        }
    }
}

class Customer implements Runnable{
    private Baozi baozi;

    public Customer(Baozi baozi){
        this.baozi = baozi;
    }

    @Override
    public void run(){
        while(true){
            synchronized (baozi){
                if(baozi.getFlag()) {
                    System.out.println("吃" + baozi.toString() + "馅的包子");
                    System.out.println("========================");
                    baozi.setFlag(false);
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒后执行的代码
                }
                baozi.notify();
            }
        }
    }
}