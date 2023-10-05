package a5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.*;


public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
                             // key: name of node. value: a5.Node object associated with name
    private int nodeCount;
    private int edgeCount;

    public GraphImpl() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(String name) {
        if (!nodes.containsKey(name) && name != null){
            Node n = new NodeImpl(name);
            nodes.put(name, n);
            nodeCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(String src, String destination, double weight) {
        if (src == null || destination == null){
            return false;
        }
        if (nodes.containsKey(src) && nodes.containsKey(destination)) {
            Node x = nodes.get(src);
            Node y = nodes.get(destination);
            Edge e = new EdgeImpl(x, y, weight);
            edgeCount++;
            x.getEdges().add(e);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteNode(String name) {
        if (!nodes.containsKey(name))
        {
            return false;
        }
        else {
            Node x = nodes.get(name);
            for (Node n : nodes.values()) {
                x.deleteEdge(n);
                n.deleteEdge(x);
            }
        }
        nodes.remove(name);
        nodeCount--;
        return true;
    }


    @Override
        public boolean deleteEdge(String src, String dest) {
            Node a = nodes.get(src);
            Node b = nodes.get(dest);
            if (a == null || b == null) {
                return false;
            }
            if (!a.nodeExists(b))
            {
                return false;
            }
            edgeCount--;
            return a.deleteEdge(b);
        }


    @Override
    public int numNodes() {
        return nodeCount;  //Dummy return value.  Remove when you implement!
    }

    @Override
    public int numEdges() {
        return edgeCount;  //Dummy return value.  Remove when you implement!
    }
    
    @Override
    public Stack<String> topoSort() {

        Stack<String> stack = new Stack<>();
        Map<Node, Boolean> val = new HashMap<>();
        for (Node n : nodes.values()) {
            val.put(n, false);
        }

        for (Node n : nodes.values()) {
            if (!val.get(n)) {
                depthFirstSearch(n, val, stack);
            }
        }

        return reverseStack(stack);

    }
    private void depthFirstSearch(Node n, Map<Node, Boolean> val, Stack<String> stack) {
        val.put(n, true);

        for (Edge edge : n.getEdges()) {
            Node nDest = edge.getDestination();
            if (!val.get(nDest)) {
                depthFirstSearch(nDest, val, stack);
            }
        }

        stack.push(n.getName());
    }

    public Stack reverseStack(Stack<String> stack) {
        Queue<String> queue = new LinkedList<>();
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        while (!queue.isEmpty()) {
            stack.add(queue.remove());
        }
        return stack;
    }
}
