package algorithms_study.chapter_4;

import edu.princeton.cs.algs4.DepthFirstSearch;

import java.util.*;

public class Graph{
    private Bag<Integer>[] adj;
   
    private final int V;
    private int E;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for( int i = 0; i < V; i++ ){
            adj[i] = new Bag<>();
        }
    }


    public Graph(String str){
        String[] s = str.split( " " );
        int[] a = new int[s.length];
        for( int i = 0; i < s.length; i++ ){
            a[i] = Integer.parseInt( s[i] );
        }
        this.V = a[0];
        int e = a[1];
        adj = (Bag<Integer>[]) new Bag[V];
        for( int i = 0; i < V; i++ ){
            adj[i] = new Bag<>();
        }
        int i = 2;
        while(i / 2 <= e){
            int v1 = a[i++];
            int v2 = a[i++];
            addEdge( v1,v2 );
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v1, int v2){
        adj[v1].add(v2);
        adj[v2].add(v1);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    static class DepthFirstSearch{
        private boolean[] marked;
        private int[] edgeTo;
        private final int s;

        public DepthFirstSearch(Graph G, int s){
            edgeTo = new int[G.V()];
            this.s = s;
            marked = new boolean[G.V()];
            dfs(G, s);
        }

        private void dfs(Graph G, int v){
            marked[v] = true;
            for( Integer i : G.adj[v] ){
                if(!marked[i]){
                    edgeTo[i] = v;
                    dfs( G, i );
                }
            }
        }

        public boolean hasPathTo(int w){
            return marked[w];
        }

        public Iterable<Integer> pathTo(int v){
            if(!hasPathTo(v)) return null;
            ArrayDeque<Integer> stack =new ArrayDeque<>(  );
            for(int x = v; x != this.s; x = edgeTo[x]){
                stack.push( x );
            }
            stack.push( s );
            return stack;
        }

        public void show(){
            System.out.println(Arrays.toString( edgeTo ));
        }

    }


    public static void main( String[] args ){
        Graph g = new Graph( "6 8 0 1 0 2 0 5 1 2 2 3 2 4 3 4 3 5" );
        Graph g2 = new Graph("6 7 0 1 0 2 1 3 2 3 3 4 3 5 5 4");
        Graph g3 = new Graph("6 7 0 2 0 1 0 5 2 3 2 1 3 4 3 5");
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch( g, s );
        BreadthFirstSearch search2 = new BreadthFirstSearch( g3, s );
        g3.adj[1].forEach( x-> System.out.println(x) );
        for(int v = 0; v < g3.V(); v++){
            System.out.print(s + " to " + v + ": ");
            search2.pathTo( v ).forEach( x -> System.out.print(  x + " " ) );
            System.out.println();
        }
        search.show();

    }
}
