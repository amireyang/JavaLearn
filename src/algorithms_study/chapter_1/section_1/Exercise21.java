package algorithms_study.chapter_1.section_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Exercise21 {
    public static void readAndPrint(){
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> firNumber = new ArrayList<>();
        ArrayList<Integer> secNumber = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            name.add(in.next());
            firNumber.add(in.nextInt());
            secNumber.add(in.nextInt());
        }
        Iterator<String> nameIter = name.listIterator();
        Iterator<Integer> firNumberIter = firNumber.listIterator();
        Iterator<Integer> secNumberIter = secNumber.listIterator();

        while(nameIter.hasNext()){
            String str = nameIter.next();
            int ite1 = firNumberIter.next();
            int ite2 = secNumberIter.next();
            System.out.printf("%s  %d  %d  %.3f\n", str, ite1, ite2, ((double)ite1 / (double)ite2));
        }
    }

    public static void main(String[] args) {
        readAndPrint();
    }
}
