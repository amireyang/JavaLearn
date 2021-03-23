package baozi;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank{
   private final double[] accounts;
   private Lock bankLock;
   private Condition sufficientFounds;

   public Bank(int n, double initialBalance){
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
      bankLock = new ReentrantLock();
      sufficientFounds = bankLock.newCondition();
   }
/*

   public void transfer(int from, int to, double amount) throws InterruptedException {
      bankLock.lock();
      try{
         while(accounts[from] < amount){
            sufficientFounds.await();
         }
         System.out.print(Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" total balance: %10.2f \n", getTotalBalance());
         sufficientFounds.signalAll();
      }finally {
         bankLock.unlock();
      }

   }
*/

//使用synchronized关键字
public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
   while(accounts[from] < amount){
      wait();
   }
   System.out.print(Thread.currentThread());
   accounts[from] -= amount;
   System.out.printf(" %10.2f from %d to %d", amount, from, to);
   accounts[to] += amount;
   System.out.printf(" total balance: %10.2f \n", getTotalBalance());
   notifyAll();


}





   public double getTotalBalance() {
      double sum = 0;
      bankLock.lock();
      try {
         for (double a : accounts) {
            sum += a;
         }
      }finally {
         bankLock.unlock();
      }
      return sum;
   }

   public int size(){
      return accounts.length;
   }

}