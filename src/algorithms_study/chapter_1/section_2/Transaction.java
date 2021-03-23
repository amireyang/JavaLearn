package algorithms_study.chapter_1.section_2;


import java.util.Arrays;
import java.util.Objects;

public class Transaction implements Comparable<Transaction>{
    private String who;
    private SmartDate date;
    private double amount;
    private int number;

    public Transaction(String who, SmartDate date, double amount){
        this.who = who;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(String tran){
        String[] trans = tran.split(" ");
        String[] dates = trans[1].split("/");
        int month = Integer.parseInt(dates[0]);
        int day = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);

        who = trans[0];
        date = new SmartDate(month,day,year);
        amount = Double.parseDouble(trans[2]);

    }

    public String who(){
        return who;
    }

    public SmartDate when(){
        return date;
    }

    public double amount(){
        return amount;
    }

    public String toString(){
        return "[" + who + ", " + date.toString() + ", " + amount + "]";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                number == that.number &&
                Objects.equals(who, that.who) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(who, date, amount, number);
    }

    @Override
    public int compareTo(Transaction other) {
        return date.compareTo(other.date);
    }

    public static void main(String[] args) {
        Transaction[] t = new Transaction[3];
        t[0] = new Transaction("杜永洋 2/15/2019 10000");
        t[1] = new Transaction("杜浩源 3/1/2020 210000");
        t[2] = new Transaction("全映桥 5/3/2018 232342");
        Arrays.sort(t);
        for (Transaction e : t) {
            System.out.println(e.toString());
        }
    }
}

