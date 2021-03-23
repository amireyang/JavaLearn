package multiThread;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;

public class UnsynchBankTest {
    private static final int NACCOUNT = 100;
    private static final double INITIAL_AMOUNT = 1000.0;
    private static final double MAX_AMOUNT = 1000.0;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNT, INITIAL_AMOUNT);
        for (int i = 0; i < NACCOUNT; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        double transferAmount = MAX_AMOUNT * Math.random();
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

class Bank{
    private final double[] account;

    public Bank(int n, double initialBalance){
        account = new double[n];
        Arrays.fill(account, initialBalance);
    }

    public void transfer(int fromAccount, int toAccount, double amount){
        if(account[fromAccount] < amount) return;
        System.out.print(Thread.currentThread());
        account[fromAccount] -= amount;
        System.out.printf(": %10.2f from Account %2d to Account %2d", amount, fromAccount, toAccount);
        account[toAccount] += amount;
        System.out.printf(", the Balance : %10.2f%n", getBalance());
    }

    public double getBalance(){
        double sum = 0.0;

        for(double a : account){
            sum += a;
        }

        return sum;
    }

}
