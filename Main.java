package a5;
import java.util.Stack;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods
        Graph testGraph = new GraphImpl();
        testGraph.addNode("0");
        testGraph.addNode("1");
        testGraph.addNode("5");
        testGraph.addNode("3");
        testGraph.addNode("4");
        testGraph.addNode("2");
        testGraph.addEdge("5", "2", 1.0);
        testGraph.addEdge("5", "0", 1.0);
        testGraph.addEdge("4", "0", 1.0);
        testGraph.addEdge("4", "5", 1.0);
        testGraph.addEdge("3", "1", 1.0);
        testGraph.addEdge("2", "3", 1.0);

        Stack<String> testGraphTopo = testGraph.topoSort();
        for (String s : testGraphTopo)
        {
            System.out.println(s);
        }
    }

    }


