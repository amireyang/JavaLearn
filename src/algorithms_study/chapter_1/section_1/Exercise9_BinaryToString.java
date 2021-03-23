package algorithms_study.chapter_1.section_1;

public class Exercise9_BinaryToString {
    /**
     * 将一个整数用二进制表示并转换为一个String类型的值s
     * @param number 要进行二进制转换的数字
     * @return 返回一个String类型的值
     */
    public static String integerToBinary(int number){
        String str = "";
        for (int i = number; i > 0 ; i  /= 2) {
            str += i % 2;
        }
        return str;
    }

    //测试
    public static void main(String[] args) {
        System.out.println(integerToBinary(2));
        System.out.println(integerToBinary(10));
        System.out.println(integerToBinary(100));
    }
}
