package SisAlgo;

import algorithms_study.chapter_4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.lang.System;
import java.util.*;

public class SisAlgo {

    private static final int lastline = 227657;
    private int[][] Data = new int[lastline + 1][7];
    private int[][] Data1 = new int[lastline + 1][7];
    private int Cum_time = 3600 * 56;
    private int Max_number_contact_N2N = 380;
    private static final int num_Node = 98;                     //节点数目
    int[][] contactnum = new int[num_Node+1][num_Node+1];   //每对节点之间联系数目
    int[][][][] contact = new int[num_Node+1][num_Node+1][Max_number_contact_N2N+1][2];//每对节点之间联系始末时间 [][][][0] ~ [][][][1] 是联系始末时间

    private int[][] com = new int[num_Node+1][21];        //节点所属的社区编号
    private final double beta = 0.4;
    private final int Du =  60*40;             //节点和AP之间联系时间大于该阈值则属于该社区

    private final int Num_packet = 100;      //网络中发送包数目
    private int source, dest;   		  //源节点和目的节点
    private int[][] received = new int[4][num_Node+1];              //网络中节点是否收到包


    private int[] impact_community = new int[21];                    //社区的影响力
    private int[] impact_node = new int[num_Node+1];                 //节点的影响力

    private int[] delay = new int[4];                     //目的节点收到包时间
    private int[] num_copy = new int[4];                  //网络中拷贝数目
    private int[] num_delivered =  new int[4];             //传递到目的节点包数目

    private int start_time = Cum_time+1;
    private int TTL = 3600*24;

    private int end_time = start_time + TTL - 1;

    //我们的变量定义
    private double[][] belongDegree = new double[num_Node + 1][21]; //社区隶属度
    private ArrayList<Integer>[] list = new ArrayList[21];//社区的数组，用于存储属于该社区内的节点
    private EdgeWeightedDigraph G;
    private int[] shortestPath = new int[21]; //记录社区的最短路径之和
    private int[][] importance = new int[99][21]; // 节点在某社区的活跃度
    private final double g = 0.5;
    private final double alpha = 0.5;

    double[][] nodeShortestPath = new double[num_Node + 1][num_Node + 1]; //记录节点之间的最短路径长度
    String[][] shortestPathStr = new String[num_Node + 1][num_Node + 1];//记录最短路径

    private double[] fai = new double[num_Node + 1];

    int step = 180; //步长：3分钟
    private int real_num_line = 0;
    private int[][] contactnum_nc = new int[num_Node + 1][21]; //每对节点和社区之间联系数目
    private int[][][][] contact_nc = new int[num_Node + 1][21][Max_number_contact_N2N + 1][2];//每对节点和社区之间联系始末时间

    private int t; //时间

    private double a = 0.5, b = 0.5;

    private int[][] comTime = new int[num_Node + 1][21];

    {
        for( int i1 = 1; i1 < list.length; i1++ ){
            list[i1] = new ArrayList<>(  );
        }
    }

    //返回社区i在t时刻的社区号，不存在返回0
    private int comId(int i, int t){
        if (i >= 1 && i <= 20){
            return i;
        }
        int k, m;
        for (k = 1; k <= 20; k++){
            for (m = 1; m <= contactnum_nc[i][k]; m++){
                if (t < contact_nc[i][k][m][0]){
                    break;
                }
                else if (t == contact_nc[i][k][m][0]){
                    return k;
                }
                else if (t <= contact_nc[i][k][m][1]){
                    return k;
                }
            }
        }
        return 0;
    }

    public void com_time(int t){
        int k, m;
        for (int i = 1; i <=num_Node ; i++){
            if (i <= 20){
                comTime[i][i] = 1;
                continue;
            }
            for (k = 1; k <= 20; k++){
        for (m = 1; m <= contactnum_nc[i][k]; m++){
            if (t < contact_nc[i][k][m][0]){
                comTime[i][k] = 0;
                break;
            } else if (t == contact_nc[i][k][m][0]){
                comTime[i][k] = 1;
                break;
            } else if (t <= contact_nc[i][k][m][1]){
                comTime[i][k] = 1;
                break;
            }
        }
    }

}
    }







    private int isConnected(int i, int j, int t){
        if( (i<1) || (i>98) || (j<1) || (j>98) || (t<0) ) return 0;  //一共有98个节点；不在该范围
        for(int k=1; k<=contactnum[i][j]; k++)
        {    if(t<contact[i][j][k][0]) return 0;
        else if(t==contact[i][j][k][0]) return 1;
        else if(t<=contact[i][j][k][1]) return 1;
        }
        return 0;
    }


    private int isNextRelay_our(int i, int j){
        if (j == dest) return 1;
        //目的节点所属社区
        int Cd = comId(dest, t);
        //节点i所属社区
        int Ci = comId(i, t);
        //节点j所属社区
        int Cj = comId(j, t);

        //节点j不属于任何社区，返回0；
        if (Cj == 0){
            return 0;
        }

        //节点i和节点j属于相同社区，且B（j，Cd）> B(i, Cd),转发。
        if (Ci == Cj){
            //i,j同社区且和目的节点同社区
            if (Ci == Cd){
                if (belongDegree[j][Cd] > belongDegree[i][Cd]){
                    return 1;
                }
            } else{//i，j同社区且和目的节点不同社区
                double cbi = shortestPath[Ci] == 0 ? 0 : (double) importance[i][Ci] / shortestPath[Ci];
                double cbj = shortestPath[Cj] == 0 ? 0 : (double) importance[j][Cj] / shortestPath[Cj];
                if (cbj > cbi){
                    return 1;
                }
            }
        } else{ //节点i和节点j属于不同社区
            //节点j和目的节点同社区
            if (Cj == Cd){
                return 1;
            }
            //节点j和目的不同社区
            else if (belongDegree[j][Cd] > belongDegree[i][Cd]){
                return 1;
            } else if (belongDegree[j][Cd] == 0 && belongDegree[i][Cd] == 0){
                if (fai[j] > fai[i]){
                    return 1;
                }
            }
        }
        return 0;
    }

    private int isNextRelay_lable(int j)  //在lable算法中，是否选择节点j作为下一个中继节点
    {   if(j==dest) return 1;
        int k;
        for(k=1; k<=20; k++)
            if( (com[dest][k]==1) && (com[j][k]==1)  ) return 1;

        return 0;
    }

    public void start() throws IOException{

        int i,j,k,l,g = 0;
        File f = new File("src/SisAlgo/contacts.Exp6.dat");
        if( f.isFile() && f.exists() ){
            try{
                InputStreamReader read = new InputStreamReader( new FileInputStream( f ), "utf-8" );
                BufferedReader bufferedReader = new BufferedReader( read );
                String lineText = null;
                for( int line = 1; line <= lastline; line++ ){
                    lineText = bufferedReader.readLine();
                    String[] dataPerLine = lineText.split( "\\s+" );
                    Data[line][1] = Integer.parseInt( dataPerLine[0] );
                    Data[line][2] = Integer.parseInt( dataPerLine[1] );
                    Data[line][3] = Integer.parseInt( dataPerLine[2] );
                    Data[line][4] = Integer.parseInt( dataPerLine[3] );
                    Data[line][5] = Integer.parseInt( dataPerLine[4] );
                    Data[line][6] = Integer.parseInt( dataPerLine[5] );
                }

            } catch( FileNotFoundException | UnsupportedEncodingException e ){
                e.printStackTrace();
            }
        }

        //(2)根据数组 Data[][]统计每对节点联系次数到数组contactnum[][], 每次联系始末时间到contact[][][][2]
        for(i=1; i<=num_Node; i++)
            for(j=1; j<=num_Node; j++)
                contactnum[i][j]=0;
        for(i=1; i<=lastline; i++)
            if(Data[i][3]<=Cum_time)
            {
                j = Data[i][1];
                k = Data[i][2];
                if(k<=98)
                {    l=Data[i][5];        contactnum[j][k]=l;
                    contact[j][k][l][0]= Data[i][3];  contact[j][k][l][1]= Data[i][4];
                }
            }

        //(3)对Data[][]进行处理，排序
        for(int h=1; h<=lastline; h++)
        {  k = Data[h][1];   g = Data[h][2];
            if((k>20)&&(k<99)&&(g>0)&&(g<21))//提取满足第一列数字在21到98之间并且第二列数字在1到20之间的数据
            {
                real_num_line++;    //读出Data1的真实行数
                Data1[real_num_line][1]=Data[h][1];
                Data1[real_num_line][2]=Data[h][2];
                Data1[real_num_line][3]=Data[h][3];
                Data1[real_num_line][4]=Data[h][4];
                Data1[real_num_line][5]=Data[h][5];
                Data1[real_num_line][6]=Data[h][6];

                // printf("num_line=%ld %d %d %d %d %d %d h=%d\n", real_num_line, Data1[real_num_line][1], Data1[real_num_line][2], Data1[real_num_line][3],
                //  Data1[real_num_line][4], Data1[real_num_line][5], Data1[real_num_line][6], h);
                //getchar();
            }
        }

        //添加最后一行作为结束标志，第一个元素为99，其他为0
        real_num_line++;
        Data1[real_num_line][1]=99;
        Data1[real_num_line][2]=0;
        Data1[real_num_line][3]=0;
        Data1[real_num_line][4]=0;
        Data1[real_num_line][5]=0;
        Data1[real_num_line][6]=0;

        //对Data1按第三列相遇开始时间升序
        int id=21;
        int startid=1, endid;
        for(int h=1; h<=real_num_line; h++)
        {  //printf("h=%d  Data1[h][1]=%d",h,Data1[h][1]); getchar();
            if(Data1[h][1]>id)
            {
                endid=h-1;
                //printf("h=%d startid=%d endid=%d id=%d ", h, startid, endid, id); getchar();
                int min;//=3000000;
                int minid;
                for(k=1;k<=(endid-startid+1 -1);k++)
                {   min=Data1[startid+k-1][3];
                    minid=startid+k-1;
                    for(g=(startid+k-1 +1); g<=endid; g++)
                    {   if(Data1[g][3]<min)
                    //{  min=Data1[g][3];    minid=g;  printf("出现更小值%d (%d %d) ",startid+k-1,minid, min); getchar();  }
                    {  min=Data1[g][3];    minid=g;    }
                    }
                    if(minid != (startid+k-1) )
                    {  int swap;
                        swap=Data1[startid+k-1][2];
                        Data1[startid+k-1][2]=Data1[minid][2];
                        Data1[minid][2]=swap;

                        swap=Data1[startid+k-1][3];
                        Data1[startid+k-1][3]=Data1[minid][3];
                        Data1[minid][3]=swap;

                        swap=Data1[startid+k-1][4];
                        Data1[startid+k-1][4]=Data1[minid][4];
                        Data1[minid][4]=swap;

                        swap=Data1[startid+k-1][5];
                        Data1[startid+k-1][5]=Data1[minid][5];
                        Data1[minid][5]=swap;

                        swap=Data1[startid+k-1][6];
                        Data1[startid+k-1][6]=Data1[minid][6];
                        Data1[minid][6]=swap;
                    }
                }
                startid=h;
                id=Data1[h][1];

            }
        }

        //对数组Data1[][4]可能进行改变
        for(int h=1; h<=real_num_line-2; h++)
        {
            if( Data1[h][1] == Data1[h+1][1] )
            {   if( Data1[h+1][3] >=  Data1[h][3] )
                    g = Data1[h+1][3]-1;
                if( g >= Data1[h][3] ) Data1[h][4] = g;     
            }
        }




        //根据 Data1[][] 得到节点在某时间段所属社区
        for(i=1; i<=num_Node; i++) for(j=1; j<=20; j++) contactnum_nc[i][j]=0;
        for(i=1; i<=real_num_line-2; i++)
        {    //printf(" ",i);
            //if( (Data1[i][4]-Data1[i][3]) < pasttime ) continue;

            j = Data1[i][1];          k = Data1[i][2];      l=Data1[i][5];                        contactnum_nc[j][k]=l;
            contact_nc[j][k][l][0]= (int)Math.floor( Data1[i][3] / step );           contact_nc[j][k][l][1]= (int)Math.floor( Data1[i][4] / step );
        }




        //(3)根据节点和AP（编号为1-20）之间联系次数是否大于阈值lambta,判定是否属于该社区
        //前20个节点分别分配到1~20号社区。
        for(j=1; j<=20; j++)
            for(i=1; i<=20; i++)
                if(i==j)
                    com[j][j]=1;       //节点j在社区j中      	//	int com[Num_node+1][21];        //节点所属的社区编号
                else
                    com[j][i]=0;

        int min=20, sum;  int belong;
        for(j=21; j <= num_Node; j++)
        {	  sum=0;
            for(i=1; i<=20; i++)
            {
                for(l=1; l<=contactnum[j][i]; l++)
                    sum += contact[j][i][l][1] - contact[j][i][l][0] + 1;
                if( sum > Du )     com[j][i]=1;    else  com[j][i]=0;
            }
        }



        //(4)统计社区影响力
        int imp;
        for(i=1; i<=20; i++) { imp=0;  for(j=1; j<=num_Node; j++) if(com[j][i]==1) imp++;   impact_community[i]=imp; }


        //(5)节点影响力
        for(i=1; i<=num_Node; i++)
        { imp=0;  for(j=1; j<=20; j++) if(com[i][j]==1) imp+=impact_community[j]; impact_node[i]=imp; }


        //(6)根据数组 Data[][]统计每对节点联系次数到数组contactnum[][], 每次联系始末时间到contact[][][][2]
        for(i=1; i<=num_Node; i++) for(j=1; j<=num_Node; j++) contactnum[i][j]=0;
        for(i=1; i<=lastline; i++)
        //if(Data[i][3]>Cum_time)
        {  
            j = Data[i][1];         
            k = Data[i][2];
            if(k<=98)
            {    l=Data[i][5];        contactnum[j][k]=l;
                contact[j][k][l][0]= Data[i][3];  contact[j][k][l][1]= Data[i][4];
            }
        }


        //(6)运行三个算法
        for(int y =1; y<=Num_packet; y++)
        {
            System.out.printf("第%d个包", y);

            //初始化
            Random r = new Random(  );

            int s1 = r.nextInt( 98 ) + 1;
            int s2 = r.nextInt( 98 ) + 1;
            while( (s1<=20) || (s1==s2) || (s2<=20)  )
            {   s1 = r.nextInt( 98 ) + 1;
                s2 = r.nextInt( 98 ) + 1;
            }
            source = s1;  dest = s2;
            for(j=1; j<=3; j++)   for(k=1; k<=num_Node; k++) received[j][k]=0;  //所有节点没有收到包
            for(j=1; j<=3; j++)   received[j][source] = 1;                      //源节点收到包

            //发送包
            int flag=0;  t=start_time;    int part_num=0;

            com_time(t);
            System.out.println();
            System.out.println("节点1");
            System.out.println(Arrays.toString(com[1]));
            System.out.println(Arrays.toString(comTime[1]));

            System.out.println("节点2");
            System.out.println(Arrays.toString(com[2]));
            System.out.println(Arrays.toString(comTime[2]));

            System.out.println("节点3");
            System.out.println(Arrays.toString(com[3]));
            System.out.println(Arrays.toString(comTime[3]));

            System.out.println("节点35");
            System.out.println(Arrays.toString(com[35]));
            System.out.println(Arrays.toString(comTime[35]));

            System.out.println("节点38");
            System.out.println(Arrays.toString(com[40]));
            System.out.println(Arrays.toString(comTime[40]));

            System.out.println("节点38");
            System.out.println(Arrays.toString(com[80]));
            System.out.println(Arrays.toString(comTime[80]));

            System.out.println("节点60");
            System.out.println(Arrays.toString(com[60]));
            System.out.println(Arrays.toString(comTime[60]));

            System.out.println(comId(80,t));



            while( (flag<3) && (t<=end_time) )
            {
                //=============================节点的中介中心性========================================

                //对节点进行遍历，将节点添加到对应的社区中
                for( int m = 1; m <= num_Node ; m++ ){
                    for( int n = 1; n <= 20; n++ ){
                        if(com[m][n] ==  1){ //节点m属于社区n
                            list[n].add( m );
                        }
                    }
                }

                //初始化图
                G = new EdgeWeightedDigraph( contactnum );
                //统计社区最短路径和最短路径字符串
                for(i = 1; i <= num_Node; i++){
                    DijkstraSP sp = new DijkstraSP( G,i );
                    for( j = 1; j <= num_Node; j++){
                        if( j != i && sp.hasPathTo( j )){
                            nodeShortestPath[i][j] = sp.distTo( j );
                            shortestPathStr[i][j] = sp.pathString( j );
                        }
                    }
                }


                //计算中介中心性Cb(u)
                //1、计算分子，社区内任意两节点v、w是否经过u，经过则为1，否则为0
                //2、计算分母，v到w的最短路径

                for( int c = 1; c <= 20; c++ ){
                    for( j = 0; j < list[c].size(); j++ ){
                        for( k = 0; k < list[c].size(); k++ ){
                            if( list[c].get(j) != list[c].get(k) && nodeShortestPath[list[c].get(j)][list[c].get(k)] != 0 ){
                                shortestPath[c] += nodeShortestPath[list[c].get(j)][list[c].get(k)];
                            }
                        }
                    }
                }

                for( i = 1; i <= 98; i++ ){
                    for( int c = 1; c <= 20; c++ ){
                        int count = 0;
                        if( com[i][c] == 1 ){
                            for( j = 0; j < list[c].size(); j++ ){
                                for( k = 0; k < list[c].size(); k++ ){
                                    if( list[c].get(j) != list[c].get(k) &&
                                            list[c].get(j) != i &&
                                            i != list[c].get(k) &&
                                            nodeShortestPath[list[c].get(j)][list[c].get(k)] != 0 ){
                                        String s = shortestPathStr[list[c].get(j)][list[c].get( k )];
                                        if( !s.equals( "" ) ){
                                            for( String s3 : s.split( " " ) ){
                                                if( i == Integer.parseInt( s3 ) ){
                                                    count++;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            importance[i][c] = count;//节点i在社区c的活跃度
                        }
                    }
                }
                //========================================================================================================

                //=================================统计fai(u)=========================
                for( int m = 1; m <=98 ; m++ ){
                    //记录任意两个节点之间的最短是否经过m，经过则加一
                    double count = 0.0;
                    //存储最短路径
                    double sp = 0;
                    //记录count / sp 之和。
                    double total = 0.0;
                    for( j = 1; j <= 20; j++ ){
                        for( k = j + 1; k <= 20; k++ ){
                            //listJ存储社区J中的节点
                            List< Integer > listJ = new ArrayList<>( List.copyOf( list[j] ) );
                            //listK存储社区K中的节点
                            List< Integer > listK = new ArrayList<>( List.copyOf( list[k] ) );
                            Iterator<Integer> iterJ = listJ.iterator();
                            while(iterJ.hasNext()){
                                //从社区J中取出一个节点，如果社区K包含这个节点，则把这个节点从社区J和社区K中删除。
                                Integer x = iterJ.next();
                                if(listK.contains( x )){
                                    listK.remove( x );
                                    iterJ.remove();
                                }
                            }
                            //对删除相同节点的社区J的节点集合和社区K的节点集合中的节点进行遍历
                            for( Integer integer : listJ ){
                                for( Integer integer1 : listK ){
                                    if( integer != m && integer1 != m ){
                                        if( nodeShortestPath[integer][integer1] != 0 ){
                                            sp += nodeShortestPath[integer][integer1];
                                            for( String s : shortestPathStr[integer][integer1].split( " " ) ){
                                                if( m == Integer.parseInt( s ) ){
                                                    count += 1.0;
                                                    break;
                                                }
                                            }
                                        }
                                        total +=count / sp;
                                        count = 0.0;
                                        sp = 0.0;
                                    }
                                }
                            }

                        }
                    }
                    fai[m] = total;
                }



                //=============================统计节点i到社区c的隶属度 ========================================

                int[][] contact_node_com = new int[num_Node + 1][21]; //记录节点到社区的隶属度
                int[] contact_node = new int[num_Node + 1];     //记录节点与其邻居节点集合的联系次数之和

                //初始化
                for(int n = 1; n <= num_Node; n++){
                    contact_node[n] = 0;				//定义记录隶属度临时变量
                    for(int m = 1; m <= 20; m++){
                        contact_node_com[n][m] = 0;
                    }

                }
                //contact_node[i]存储节点i与其邻居集合中所有节点联系次数的总和。
                for(int n = 1; n <= num_Node; n++){ //对节点进行遍历

                    //统计节点n的所有邻居节点的联系次数之和
                    for(int m=1; m <= num_Node; m++){
                        if(n!=m && m != dest && isConnected(n,m,t) ==  1){ //如果m和n有关联，则将i和j的联系次数记录到contact_node[i]。
                            contact_node[n] += contactnum[n][m];

                            int Cm =  comId(m, t); //返回m在t时刻所在的社区。
                            //将n和m的联系次数记录到contact_node[n][Cm]
                            contact_node_com[n][Cm] += contactnum[n][m];

                        }

                    }
                }

                //计算B（u，c）
                for(int n = 1; n <= num_Node; n++){
                    for(int c = 1; c <= 20; c++){
                        if(contact_node[n] != 0){
                            belongDegree[n][c] = ((double) contact_node_com[n][c] * a + (double) contactnum[n][dest] * isConnected(n,dest,t)  * b) / (contact_node[n] + contactnum[n][dest] * isConnected(n,dest,t));
                        }
                        else{
                            belongDegree[n][c] = 0;
                        }
                    }
                }


                //我们算法开始
                if(received[1][dest]==0) //目的节点没有收到包
                    for(j=1; j<=num_Node; j++)
                    {    if(received[1][j]==1)
                        for(k=1; k<=num_Node; k++)
                        {
                            if( (received[1][k]==0) && (isNextRelay_our(j,k)==1) && (isConnected(j,k,t)==1) )
                            {   received[1][k]=1;
                                if(k==dest) //目的节点收到包
                                {
                                    delay[1] += (t - start_time + 1); //目标节点收到包所用时间
                                    int sum_copy=0;     for(l=1; l<=num_Node; l++)  if( received[1][l]==1 )  sum_copy++;
                                    num_copy[1] += sum_copy;
                                    num_delivered[1] ++;      flag++; break;
                                }
                            }//if k>j
                        }//for k
                        if(received[1][dest]==1) break;  //目的节点收到包
                    }//for j
                //我们算法结束

                //Epidemic算法开始
                if(received[2][dest]==0) //目的节点没有收到包
                    for(j=1; j<=num_Node; j++)
                    {    if(received[2][j]==1)
                        for(k=1; k<=num_Node; k++)
                        {   if( (received[2][k]==0) && (isConnected(j,k,t)==1) )
                        {   received[2][k]=1;
                            if(k==dest) //目的节点收到包
                            {
                                delay[2] += (t - start_time + 1);
                                int sum_copy=0;     for(l=1; l<=num_Node; l++)  if( received[2][l]==1 )  sum_copy++;
                                num_copy[2] += sum_copy;
                                num_delivered[2] ++;      flag++; break;
                            }
                        }//if k>j
                        }//for k
                        if(received[2][dest]==1) break;  //目的节点收到包
                    }//for j
                //Epidemic算法结束

                //Lable算法开始
                if(received[3][dest]==0) //目的节点没有收到包
                    for(j=1; j<=num_Node; j++)
                    {    if(received[3][j]==1)
                        for(k=1; k<=num_Node; k++)
                        {   if( (received[3][k]==0) && (isNextRelay_lable(k)==1)  && (isConnected(j,k,t)==1) )
                        {   received[3][k]=1;
                            if(k==dest) //目的节点收到包
                            {
                                delay[3] += ( t - start_time + 1 );
                                int sum_copy=0;     for(l=1; l<=num_Node; l++)  if( received[3][l]==1 )  sum_copy++;
                                num_copy[3] += sum_copy;
                                num_delivered[3] ++;      flag++; break;
                            }
                        }//if k>j
                        }//for k
                        if(received[3][dest]==1) break;  //目的节点收到包
                    }//for j
                //Lable算法结束

                t++;
            }//while

            System.out.printf("%n时间=%d 本次发包结束%n",t);
        }//for(i=1; i<=Num_packet; i++)


        //(7)显示运行结果
        double ratio1,ratio2,ratio3;
        ratio1=(num_delivered[1]-num_delivered[2])*1.0/Num_packet;
        ratio2=(delay[1]*1.0/num_delivered[1]-delay[2]*1.0/num_delivered[2])/(delay[2]*1.0/num_delivered[2]);
        ratio3=(num_copy[1]*1.0/num_delivered[1]-num_copy[2]*1.0/num_delivered[2])/(num_copy[2]*1.0/num_delivered[2]);

        System.out.printf("我们算法:  传递率=%f, 平均延迟=%f 拷贝数目=%f%n", num_delivered[1]*1.0/Num_packet, delay[1]*1.0/num_delivered[1], num_copy[1]*1.0/num_delivered[1]);
        System.out.printf("3 ratios(相对Epidemic)：%f, %f, %f%n", ratio1, ratio2, ratio3);
        ratio1=(num_delivered[1]-num_delivered[3])*1.0/Num_packet;
        ratio2=(delay[1]*1.0/num_delivered[1]-delay[3]*1.0/num_delivered[3])/(delay[3]*1.0/num_delivered[3]);
        ratio3=(num_copy[1]*1.0/num_delivered[1]-num_copy[3]*1.0/num_delivered[3])/(num_copy[3]*1.0/num_delivered[3]);
        System.out.printf("3 ratios(相对Lable)   ：%f, %f, %f%n", ratio1, ratio2, ratio3);

        System.out.printf("Epidemic:  传递率=%f, 平均延迟=%f 拷贝数目=%f%n", num_delivered[2]*1.0/Num_packet, delay[2]*1.0/num_delivered[2], num_copy[2]*1.0/num_delivered[2]);

        ratio1=(num_delivered[3]-num_delivered[2])*1.0/Num_packet;
        ratio2=(delay[3]*1.0/num_delivered[3]-delay[2]*1.0/num_delivered[2])/(delay[2]*1.0/num_delivered[2]);
        ratio3=(num_copy[3]*1.0/num_delivered[3]-num_copy[2]*1.0/num_delivered[2])/(num_copy[2]*1.0/num_delivered[2]);
        System.out.printf("Lable  :    传递率=%f, 平均延迟=%f 拷贝数目=%f%n", num_delivered[3]*1.0/Num_packet, delay[3]*1.0/num_delivered[3], num_copy[3]*1.0/num_delivered[3]);
        System.out.printf("3 ratios(相对Epidemic)：%f, %f, %f%n", ratio1, ratio2, ratio3);



        System.out.printf("%nPress any key to end.");
        Scanner in = new Scanner( System.in );
        in.nextLine();
    }//主函数结束

    public static void main( String[] args ){
        SisAlgo sisAlgo = new SisAlgo();
        try {
            sisAlgo.start();
        } catch( IOException e ){
            e.printStackTrace();
        }
    }


}
