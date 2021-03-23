package multiThread;

public class Test {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("Hello, World!");
        };
        Thread t = new Thread(r);
        System.out.println(Thread.currentThread());

        t.start();

    }
}
