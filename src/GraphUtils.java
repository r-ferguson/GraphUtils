/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.List;
import java.util.Set;

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
     */
    public static int minDistance(Graph graph, String src, String dest) {
        return 0;
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
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
        return null;
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
        return false;
    }
}