package algorithms_study.chapter_1.section_2;

import java.util.Objects;

public class SmartDate implements Comparable<SmartDate>{
    private static int[] DAYS = {0, 31, 29, 31, 30 , 31, 30 ,31, 31, 30 ,31, 30 ,31};

    private int day;
    private int month;
    private int year;

    public SmartDate(int month, int day, int year) throws IllegalArgumentException{
        if(!isValid(month, day, year)){
            throw new IllegalArgumentException("inValid Date");
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public SmartDate(String date) throws IllegalArgumentException{
        String[] args = date.split("/");
        if(args.length != 3) {throw new IllegalArgumentException();}
        this.month = Integer.parseInt(args[0]);
        this.day = Integer.parseInt(args[1]);
        this.year = Integer.parseInt(args[2]);
        if(!isValid(month, day, year)){
            throw new IllegalArgumentException("Invalid Date!");
        }

    }

    private boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        }
        if(year % 100 == 0){
            return false;
        }
        return year % 4 == 0;

    }

    private boolean isValid(int month, int day, int year){
        if(month < 1 || month > 12)                { return false;}
        if (year < 2000 || year > 2099)            { return false;}
        if(day < 1 || day > DAYS[month])           { return false;}
        if(month == 2 && !isLeapYear(year) && day == 29)   { return false;}
        return true;
    }

    public int day(){
        return day;
    }

    public int month(){
        return month;
    }

    public int year(){
        return year;
    }

    public static String dayOfWeek(int day){
        switch(day){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }
        return null;
    }

    @Override
    public int compareTo(SmartDate other){
        if(this.year < other.year) return -1;
        if(this.year > other.year) return 1;
        if(this.month < other.month) return -1;
        if(this.month > other.month) return 1;
        if(this.day < other.day) return -1;
        if(this.day > other.day) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){
            return false;
        }

        if(otherObject == null){
            return false;
        }

        if(getClass() != otherObject.getClass()){
            return false;
        }

        SmartDate other = (SmartDate) otherObject;

        return day == other.day &&
                month == other.month &&
                year == other.year;

    }
    @Override
    public int hashCode(){
        return Objects.hash(month,day,year);
    }
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        SmartDate d1 = new SmartDate(1, 3,2019);
        SmartDate d2 = new SmartDate(1,4,2019);
        System.out.println(d1.compareTo(d2));
    }

}

/*
//范例
public class Date implements Comparable<Date> {
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int month;   // month (between 1 and 12)
    private final int day;     // day   (between 1 and DAYS[month]
    private final int year;    // year

    // do bounds-checking to ensure object represents a valid date
    public Date(int month, int day, int year) {
        if (!isValid(month, day, year)) throw new RuntimeException("Invalid date");
        this.month = month;
        this.day   = day;
        this.year  = year;
    }

    // create new data by parsing from string of the form mm/dd/yy
    public Date(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) {
            throw new RuntimeException("Date parse error");
        }
        month = Integer.parseInt(fields[0]);
        day   = Integer.parseInt(fields[1]);
        year  = Integer.parseInt(fields[2]);
        if (!isValid(month, day, year)) throw new RuntimeException("Invalid date");
    }

    public int month() { return month; }
    public int day()   { return day;   }
    public int year()  { return year;  }


    // is the given date valid?
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    // return the next Date
    public Date next() {
        if (isValid(month, day + 1, year))    return new Date(month, day + 1, year);
        else if (isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
        else                                  return new Date(1, 1, year + 1);
    }


    // is this Date after b?
    public boolean isAfter(Date b) {
        return compareTo(b) > 0;
    }

    // is this Date a before b?
    public boolean isBefore(Date b) {
        return compareTo(b) < 0;
    }

    // compare this Date to that one
    public int compareTo(Date that) {
        if (this.year  < that.year)  return -1;
        if (this.year  > that.year)  return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day   < that.day)   return -1;
        if (this.day   > that.day)   return +1;
        return 0;
    }

    // return a string representation of this date
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    // is this Date equal to x?
    public boolean equals(Object x) {
        if (x == this) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Date that = (Date) x;
        return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
    }

    public int hashCode() {
        int hash = 17;
        hash = 31*hash + month;
        hash = 31*hash + day;
        hash = 31*hash + year;
        return hash;
    }


    // sample client for testing
    public static void main(String[] args) {
        Date today = new Date(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }

}
 */