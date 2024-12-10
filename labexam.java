import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of vertices from the user
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Validate that the number of vertices is greater than 0
        if (vertices <= 0) {
            System.out.println("Error: The number of vertices must be greater than 0.");
            return;
        }

        // Create a graph instance with the specified number of vertices
        Graph graph = new Graph(vertices);

        // Read the number of edges
        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        // Validate that the number of edges is not negative
        if (edges < 0) {
            System.out.println("Error: The number of edges cannot be negative.");
            return;
        }

        // Add edges to the graph based on user input
        System.out.println("Enter the edges (format: v1 v2):");
        for (int i = 0; i < edges; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            graph.addEdge(v1, v2);
        }

        // Display the adjacency matrix and adjacency list
        graph.printAdjMatrix();
        graph.printAdjList();

        scanner.close();
    }
}









public class Graph {
    private int[][] adjMatrix; // Adjacency Matrix
    private int[][] adjList; // Adjacency List (fixed-size 2D array)
    private int[] listSizes; // Tracks the size of each adjacency list
    private int vertices;

    // Constructor: Initializes graph structures based on the number of vertices
    public Graph(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
        adjList = new int[vertices][vertices]; // Fixed-size 2D array for adjacency list
        listSizes = new int[vertices]; // Keeps track of neighbors for each vertex
    }

    // Adds an edge between two vertices
    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= vertices || v2 < 0 || v2 >= vertices) {
            System.out.println("Error: Vertices out of bounds!");
            return;
        }
        if (v1 == v2) {
            System.out.println("Error: Self-loops are not allowed!");
            return;
        }

        // Update adjacency matrix (since the graph is undirected, we update both directions)
        adjMatrix[v1][v2] = 1;
        adjMatrix[v2][v1] = 1;

        // Update adjacency list for both vertices
        adjList[v1][listSizes[v1]++] = v2;
        adjList[v2][listSizes[v2]++] = v1;
    }

    // Prints the adjacency matrix
    public void printAdjMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Prints the adjacency list
    public void printAdjList() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < listSizes[i]; j++) {
                System.out.print(adjList[i][j] + " ");
            }
            System.out.println();
        }
    }
}
