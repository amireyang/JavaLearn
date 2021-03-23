import java.util.ArrayList;

public class jvm {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for(int i = 0; i < 500; i++){
            byte[] arr =new byte[1024 *100];//100kb
            list.add(arr);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
