import java.util.*;
import java.io.*;

public class EveryPath {
    public static void main(String[] args){
        int[][] graph1 = {
                {0, 0, 0},
                {7, 0, 0},
                {0, 0, 0}
        };

        System.out.println(pathLength7(graph1, 1, 0));

        int[][] graph2 = {
                {0, 3, 4, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 3},
                {0, 0, 0, 0}
        };

        System.out.println(pathLength7(graph2, 0, 3));

        int[][] graph3 = {
                {0, 2, 1, 0},
                {0, 0, 0, 5},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        System.out.println(pathLength7(graph3, 0, 3));

        int[][] graph4 = {
                {0, 5, 0, 0},
                {0, 0, 2, 0},
                {0, 3, 0, 0},
                {0, 0, 0, 0}
        };
        System.out.println(pathLength7(graph4, 0, 2));

        int[][] graph5 = {
                {0, 6, 0},
                {0, 0, 2},
                {0, 0, 0}
        };
        System.out.println(pathLength7(graph5, 0, 2));

        int[][] graph6 = {
                {0, 7, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 7},
                {0, 0, 0, 0}
        };
        System.out.println(pathLength7(graph6, 0, 3));


        int[][] graph7 = {
                {0, 10, -3, 0},
                {0, 0, 0, 7},
                {0, 0, 0, 10},
                {0, 0, 0, 0}
        };
        System.out.println(pathLength7(graph7, 0, 3));

        int[][] graph8 = {
                {0, 1, 3, 0},
                {0, 0, 0, 6},
                {0, 0, 0, 4},
                {0, 0, 0, 0}
        };
        System.out.println(pathLength7(graph8, 0, 3));

        int[][] graph9 = {
                {0, 2, 0, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };
        System.out.println(pathLength7(graph9, 0, 3));

        int[][] graph10 = {
                {0, 7, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println(pathLength7(graph10, 0, 0));


    }

    public static String pathLength7(int[][] digraph, int u, int w){
        int[][] unweighted = makeUnweighted(digraph);//O(n^2)

        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();


        List<Integer> path = new ArrayList<>();
        path.add(u);
        q.add(path);
        paths.add(path);

        //O((n+E)*(2^n+2^n+n))=O(4^n)
        while(!q.isEmpty()){//O(n)
            List<Integer> currentPath = q.poll();
            int currentNode = currentPath.get(currentPath.size() - 1);

            for(int i = 0; i < unweighted.length; i++){//O(E) in total
                if(unweighted[currentNode][i] != 0 && !currentPath.contains(i)){//O(n)
                    List<Integer> newPath = new ArrayList<>(currentPath);//O(n)
                    newPath.add(i);//O(n)
                    q.add(newPath);//O(2^n)
                    paths.add(newPath);//O(2^n)
                }
            }
        }

//        for(int[] i : digraph){
//            System.out.println(Arrays.toString(i));
//        }

        int sum = 0;
        List<List<Integer>> desPath = new ArrayList<>();
        for(int i = 0; i < paths.size(); i++){ //O(2^n + 2^n)=O(2^n)
            List<Integer> cPath = paths.get(i); //O(1)
            for(int j = 1; j < cPath.size(); j++){ //Loops over all paths over the entirety of the outer loop
                sum += digraph[cPath.get(j-1)][cPath.get(j)]; //O(1)
            }
            //System.out.println(sum);
            if(sum == 7 && cPath.get(cPath.size() - 1) == w){ //O(1)
                desPath.add(cPath); //O(2^n)
            }
            sum = 0;
        }

//        for(List i : paths){
//            System.out.println(i);
//        }

        String s = "";
        for(List i : desPath){
            s += i;
        }
        if(s.isEmpty()){
            s = "[]";
        }
        return s;
    }

    /*
    n = number of vertices
    Time Complexity: O(n^2)
    Space Complexity: O(n^2)
     */
    public static int[][] makeUnweighted(int[][] adjM){
        int[][] unweighted = new int[adjM.length][adjM.length];
        for(int i = 0; i < adjM.length; i++){
            for(int j = 0; j < adjM[i].length; j++){
                if(adjM[i][j] != 0){
                    unweighted[i][j] = 1;
                }
                else
                    unweighted[i][j] = 0;
            }
        }

        return unweighted;
    }

}
