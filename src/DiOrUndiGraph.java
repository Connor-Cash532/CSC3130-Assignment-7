public class DiOrUndiGraph {
    public static void main(String[] args){
        int[][] directedMatrix = {
                // Edges: 0→1, 1→2, 2→3, 3→0 (directed cycle)
                {0, 1, 0, 0}, // Node 0 points to 1
                {0, 0, 1, 0}, // Node 1 points to 2
                {0, 0, 0, 1}, // Node 2 points to 3
                {1, 0, 0, 0}  // Node 3 points to 0
        };

        int[][] undirectedMatrix = {
                // Edges: 0-1, 0-2, 1-3, 2-3 (symmetric connections)
                {0, 1, 1, 0}, // Node 0 connected to 1 and 2
                {1, 0, 0, 1}, // Node 1 connected to 0 and 3
                {1, 0, 0, 1}, // Node 2 connected to 0 and 3
                {0, 1, 1, 0}  // Node 3 connected to 1 and 2
        };

        System.out.println(isDirected(directedMatrix));
        System.out.println(isDirected(undirectedMatrix));
    }

    /*
    n = number of Vertices
    Time Complexity: O(n^2)
    Space Complexity: O(1)
     */
    public static boolean isDirected(int[][] adjacencyMatrix){
        int edgeTo; //O(1)
        int edgeFrom; //O(1)
        int i; //O(1)
        int j; //O(1)
        for(i = 0; i < adjacencyMatrix.length; i++){ //O(n)
            for(j = 0; j < adjacencyMatrix[i].length; j++){ //O(n)
                edgeTo = adjacencyMatrix[i][j]; //O(1)
                edgeFrom = adjacencyMatrix[j][i]; //O(1)
                if(edgeTo != edgeFrom){ //O(1)
                    return false; //O(1)
                }
            }
        }

        return true;
    }
}
