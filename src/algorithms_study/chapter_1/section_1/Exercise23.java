package algorithms_study.chapter_1.section_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise23 {
    public static int biSearch(int[] a, int key){
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if (key == a[mid]) return mid;
            else if(key < a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return - 1;
    }


    public static void main(String[] args) {
        int[] whileList = { 1, 3, 5, 34, 23, 46, 21, 2, 16};
        Arrays.sort(whileList);
        Scanner inSymbol = new Scanner(System.in);
        Scanner inNumber = new Scanner(System.in);
        System.out.println("请输入操作符 ‘+’ 或 ‘-’：");
        String operateSymbol = inSymbol.next();
        System.out.println("请输入数字：");
        ArrayList<Integer> result = new ArrayList<>();
        int value;
        while(inNumber.hasNext()) {
            value = inNumber.nextInt();
            if (operateSymbol.equals("+")) { //打印不在白名单上的值
                if (biSearch(whileList, value) == -1) result.add(value);
            }
            else{//打印在白名单上的值
                if(biSearch(whileList, value) != -1 ) result.add(value);
            }
        }
        System.out.println(result.toString());
    }
}
