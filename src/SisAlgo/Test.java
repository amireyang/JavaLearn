package SisAlgo;

import java.io.*;
import java.util.*;

public class Test{
    public static void main( String[] args ) throws IOException{

        List<Integer> list1 = new ArrayList<>(  );
        List<Integer> list2 = new ArrayList<>(  );
        list1.add( 1 );
        list1.add( 2 );
        list1.add( 3 );
        list2.add( 3 );
        list2.add( 2 );
        list2.add( 0 );


      /*  int[][] Data = new int[227658][7];
        File f = new File("src/SisAlgo/contacts.Exp6.dat");
        if( f.isFile() && f.exists() ){

            try{
                InputStreamReader read = new InputStreamReader( new FileInputStream( f ), "utf-8" );
                 BufferedReader bufferedReader = new BufferedReader( read );
                String lineText = null;
                for( int i = 1; i <= 227657; i++ ){
                    lineText = bufferedReader.readLine();
                    String[] dataPerLine = lineText.split( "\\s+" );
                    Data[i][1] = Integer.parseInt( dataPerLine[0] );
                    Data[i][2] = Integer.parseInt( dataPerLine[1] );
                    Data[i][3] = Integer.parseInt( dataPerLine[2] );
                    Data[i][4] = Integer.parseInt( dataPerLine[3] );
                    Data[i][5] = Integer.parseInt( dataPerLine[4] );
                    Data[i][6] = Integer.parseInt( dataPerLine[5] );
                }
            } catch( FileNotFoundException e ){
                e.printStackTrace();
            }
        }
        System.out.println(Data[0].length);*/
    }
}
