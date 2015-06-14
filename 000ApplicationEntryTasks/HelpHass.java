
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * License: MIT
 * Copyright (c) 2015 Spas Kyuchukov
 * 
 * Tasks description:
 * https://github.com/HackBulgaria/Haskell-1/blob/master/Application/01-HelpHass/README.md
 */

public class HelpHass {
    
    private static class Vertex {
        private ArrayList<Vertex> connectedTo;
        private int idx;
        private Vertex(int index) {
            connectedTo = new ArrayList<Vertex>();
            idx = index;
        }
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        // Assume there can be at most 26 vertices - A ... Z = 0 ... 25
        int[] parent = new int[26];
        boolean[] visited = new boolean[26];
        Vertex[] vertices = new Vertex[26];
        for (int i = 0; i < 26; i++) {
            vertices[i] = new Vertex(i);
        }
        
        //System.out.println("Enter connected stations A...Z in format 'X Y' and empty new line for end.");
        String edge = in.nextLine();
        
        // empty new line ends input
        while (edge.length() > 0){
            int from = ((int)edge.charAt(0) - (int)'A');
            int to = ((int)edge.charAt(2) - (int)'A');
            vertices[from].connectedTo.add(vertices[to]);
            edge = in.nextLine();
        }
        
        int startVertexIdx = ((int)'H' - (int)'A');
        int targerVertexIdx = ((int)'L' - (int)'A');
        
        // BFS
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(vertices[startVertexIdx]);
        visited[startVertexIdx] = true;
        parent[startVertexIdx] = startVertexIdx;
        
        while (!queue.isEmpty()){
            Vertex current = queue.poll();
            if(current.idx == targerVertexIdx){
                break;
            }
            for (Vertex reachable : current.connectedTo) {
                if(!visited[reachable.idx]){
                    visited[reachable.idx] = true;
                    parent[reachable.idx] = current.idx;
                    queue.add(reachable);
                }
            }
        }
        
        // Print result
        StringBuilder path = new StringBuilder("L");
        int currentIndex = targerVertexIdx;
        int parentIndex = parent[currentIndex];
        while(currentIndex != parentIndex){
            path.insert(0, (char)((int)'A' + parentIndex) + " ");
            currentIndex = parentIndex;
            parentIndex = parent[currentIndex];
        }
        System.out.println(path);
    }
    
}
