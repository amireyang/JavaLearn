package algorithms_study.chapter_1.section_2;

import java.util.Objects;
import java.util.Random;

public class Rational implements Comparable<Rational>{
    private long numerator;
    private long denominator;

    public Rational(long num, long deno){
        if(deno == 0){
            throw new IllegalArgumentException("worng denominator");
        }
        long r = gcd(Math.abs(num), Math.abs(deno));
        this.numerator = num / r;
        this.denominator = deno / r;

        if(denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }
    }

    Rational plus(Rational b){

        long newDeno = this.denominator * b.denominator;
        long newNum = this.numerator * b.denominator + b.numerator * this.denominator;
        return new Rational(newNum, newDeno);

    }

    Rational minus(Rational b){

        long newDeno = this.denominator * b.denominator;
        long newNum = this.numerator * b.denominator - b.numerator * this.denominator;
        return new Rational(newNum, newDeno);

    }

    Rational times(Rational b){

        long newDeno = this.denominator * b.denominator;
        long newNum = this.numerator * b.numerator;
        return new Rational(newNum , newDeno);
    }

    Rational divides(Rational b){
        long newDeno = this.denominator * b.numerator;
        long newNum = this.numerator * b.denominator;
        return new Rational(newNum , newDeno );
    }

    @Override
    public int compareTo(Rational other){
        if(minus(other).numerator > 0){
            return 1;
        }
        if(minus(other).numerator < 0){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object x){
        if(this == x){
            return true;
        }
        if(x == null || getClass() != x.getClass()){
            return false;
        }

        Rational that = (Rational) x;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode(){
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }

    private static long gcd(long q, long p){
        if (p == 0){
            return q;
        }
        long r = q % p;
        return gcd(p, r);
    }



    public static void main(String[] args) {
        Rational r1 = new Rational(3,-5);
        Rational r2 = new Rational(4,7);

        System.out.println("4/7 + 3/-5:" + r2.plus(r1));
        System.out.println("4/7 - 3/-5:" + r2.minus(r1));
        System.out.println("4/7 * 3/-5:" + r2.times(r1));
        System.out.println("4/7 / 3/-5:" + r2.divides(r1));

        System.out.println(r1.compareTo(r2));
        System.out.println(r1.equals(r2));
    }
}
