/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: Ryan Ferguson
 * Penn email: <rfergu1@seas.upenn.edu>
 * Date: 2022-10-16
 */

import java.util.*;

public class GraphUtils {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 78327812893L;

    /**
     * Given a graph, this method returns the smallest number of edges from the src
     * node to the dest node, or 0 when src = dest, or −1 for any invalid input.
     * Invalid inputs are defined as: any of graph, src, or dest is null; no path
     * exists from src to dest; any of src or dest do not exist in graph.
     *
     * @param graph directed or undirected graph
     * @param src   source node
     * @param dest  destination node
     * @return the smallest number of edges from the src to dest, or -1 for any
     *         invalid input
     *  O(n+m) since this is just a modification of BFS
     */
    public static int minDistance(Graph graph, String src, String dest) {
        //handle invalid inputs
        if(graph==null) return -1;
        if(src == null) return -1;
        if(dest == null) return -1;
        if(!graph.containsNode(src)) return -1;
        if(!graph.containsNode(dest)) return -1;
        if(!graph.bfs(src,dest)) return -1; //no path exists from source to dest
        //check if source = destination
        if(src.equals(dest)) return 0;
        //otherwise essentially run BFS keeping track of levels of each vertex using hashmap
        Set<String> marked = new HashSet<>();
        Map<String,Integer> level = new HashMap<>();
        Queue<String> toExplore = new LinkedList<>();
        marked.add(src);
        toExplore.add(src);
        level.put(src,0); //set level of source to 0;
        while (!toExplore.isEmpty()) {
            String current = toExplore.remove();
            for (String neighbor : graph.getNodeNeighbors(current)) {
                if (!marked.contains(neighbor)) {
                    level.put(neighbor,level.get(current)+1); //add 1 to the level of the parent and store current
                    if (neighbor.equals(dest))
                        return level.get(neighbor);
                    marked.add(neighbor);
                    toExplore.add(neighbor);
                }
            }
        }
        return -1;
    }

    /**
     * Given a graph, a src node contained in graph, and a distance of at least 1,
     * this method returns the set of all nodes, excluding src, for which the
     * smallest number of edges from src to each node is less than or equal to
     * distance; null is returned if there is any invalid input. Invalid inputs are
     * defined as: any of graph or src is null; src is not in graph; distance is
     * less than 1.
     *
     * @param graph    directed or undirected graph
     * @param src      source node
     * @param distance maximum distance from source to the nodes to include in
     *                 output set
     * @return set of all nodes, excluding src, for which the smallest number of
     *         edges from src to each node is less than or equal to distance, or
     *         null on invalid input
     */
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance){
        Set<String> result = new HashSet<>();
        //handle invalid inputs
        if(graph==null) return null;
        if(src == null) return null;
        if(distance < 1) return null;
        if(!graph.containsNode(src)) return null;
        //otherwise essentially run BFS keeping track of levels of each vertex using hashmap
//        Set<String> marked = new HashSet<>();
        Map<String,Integer> level = new HashMap<>();
        Queue<String> toExplore = new LinkedList<>();
//            marked.add(src);
            toExplore.add(src);
            level.put(src,0); //set level of source to 0;
            while (!toExplore.isEmpty()) {
            String current = toExplore.remove();
            for (String neighbor : graph.getNodeNeighbors(current)) {
                if (!level.containsKey(neighbor)) {
                    level.put(neighbor,level.get(current)+1); //add 1 to the level of the parent and store current
                    if (level.get(neighbor)<= distance) {
                        result.add(neighbor);
                    }
//                    marked.add(neighbor);
                    toExplore.add(neighbor);
                }
            }
        }
            return result;
    }
    /**
     * This method returns true only if the graph g is non-null and has at least
     * three nodes and values is non-null and represents a Hamiltonian cycle through
     * g.
     *
     * @param g      directed or undirected graph
     * @param values a sequence of vertices that must end in the starting node of
     *               the cycle
     * @return true only if values represents Hamiltonian cycle through g
     */
    public static boolean isHamiltonianCycle(Graph g, List<String> values) {
        //handle invalid inputs
        if(g==null) return false;
        if(values == null) return false;
        if(g.numNodes < 3) return false;

        return false;
    }
}