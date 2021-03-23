package algorithms_study.chapter_4;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new ArrayDeque<>(  );
        marked[s] = true;
        queue.add( s );
        while(!queue.isEmpty()){
            int v = queue.poll();
            for( Integer i : G.adj( v ) ){
                if(!marked[i]){
                    edgeTo[i] = v;
                    marked[i] = true;
                    queue.add( i );
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
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

}