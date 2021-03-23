package SisAlgo;

import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SisGraph{
    private int V;
    private int E;
    private int[][] adj;
    private DepthFirstPaths path;

    public SisGraph(int V){
        this.V = V;
        this.E = 0;
        adj = new int[V + 1][V + 1];
        for( int i = 1; i <= adj.length; i++ ){
            for( int j = 1; j <= adj[0].length; j++ ){
                adj[i][j] = 0;
            }
        }
    }

    public SisGraph(int[][] isConnected, int[][] weight){
        this(isConnected.length - 1);
        for( int i = 1; i <= isConnected.length; i++ ){
            for( int j = 1; j < isConnected[1].length; j++ ){
                if( i !=j && isConnected[i][j] == 1 ){
                    addEdge( i,j,weight[i][j] );
                }
            }
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v][w] = 1;
    }

    public void addEdge(int v, int w, int weight){
        adj[v][w] = weight;
    }

    public ArrayList<Integer> connecOfNode(int v){
        ArrayList<Integer> arrayList = new ArrayList<>(  );
        for( int i = 1; i <= adj.length; i++ ){
            if( i == v ){
                continue;
            }
            if( adj[v][i] > 0 ){
                arrayList.add( i );
            }
        }
        return arrayList;
    }

    public int sumWeight(int v){
        ArrayList<Integer> a = connecOfNode( v );
        int sum = 0;
        for( Integer i : a ){
            sum += adj[v][i];
        }
        return sum;
    }

    private class DepthFirstPaths{
        private boolean[] marked;
        private int[] edgeTo;
        private int s;

        public DepthFirstPaths(int s){
            marked = new boolean[V];
            edgeTo = new int[V];
            this.s = s;
            dfs(s);
        }

        private void dfs( int v ){
            marked[v] = true;
            for( Integer i : connecOfNode( v ) ){
                if( !marked[i] ){
                    edgeTo[i] = v;
                    dfs( i );
                }
            }
        }

        public boolean hasPathTo(int v){
            return marked[v];
        }

        public Iterable<Integer> pathTo(int v){
            if(!hasPathTo( v )) return null;
            Stack<Integer> path = new Stack<>();
            for(int x = v; x != s; x = edgeTo[x]){
                path.push( x );
            }
            path.push( s );
            return path;
        }

    }

    public static void main( String[] args ){

    }
}
