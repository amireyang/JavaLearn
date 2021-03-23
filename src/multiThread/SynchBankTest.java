package multiThread;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchBankTest{
    private static final int NACCOUNT = 10;
    private static final double INITIAL_AMOUNT = 2.0;
   private static final double MAX_AMOUNT = 10000.0;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        SynchBank bank = new SynchBank(NACCOUNT, INITIAL_AMOUNT);
        for (int i = 0; i < NACCOUNT; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        double transferAmount = 10 * MAX_AMOUNT * Math.random();
                        int toAccount = (int) (NACCOUNT * Math.random());
                        bank.transfer(fromAccount, toAccount, transferAmount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }

            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}

class SynchBank{
    private final double[] account;
    private Lock myLock = new ReentrantLock(); //每个bank对象都有一个自己的ReentrantLock对象。如果两个线程识图访问同一个Bank对象，那么锁以串行方式提供服务。
    private Condition sufficientFunds = myLock.newCondition();

    public SynchBank(int n, double initialBalance){
        account = new double[n];
        Arrays.fill(account, initialBalance);
    }

    public void transfer(int fromAccount, int toAccount, double amount){
        myLock.lock();
        try {
            if (account[fromAccount] < amount) sufficientFunds.await();
            System.out.print(Thread.currentThread());
            account[fromAccount] -= amount;
            System.out.printf(": %10.2f from Account %2d to Account %2d", amount, fromAccount, toAccount);
            account[toAccount] += amount;
            System.out.printf(", the Balance : %10.2f", getBalance());
            System.out.println();
            sufficientFunds.signalAll();
        }catch(InterruptedException e){

        }
        finally{
            myLock.unlock();
        }
    }

    public double getBalance(){
        double sum = 0.0;

        for(double a : account){
            sum += a;
        }

        return sum;
    }

}

