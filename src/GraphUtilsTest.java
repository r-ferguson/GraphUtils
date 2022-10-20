import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphUtilsTest {
    Graph defaultUD;
    Graph defaultD;
    Graph emptyD;
    Graph emptyUD;
    Graph onelineD;
    Graph onelineUD;
    Graph onenodeD;
    Graph onenodeUD;

    Graph hamdefUD;
    Graph hamdefD;

    Graph unconUD;
    Graph unconD;

    @Before
    public void setUp() throws Exception{
        defaultUD = GraphBuilder.buildUndirectedGraph("student_graph_test.txt");
        defaultD = GraphBuilder.buildDirectedGraph("student_graph_test.txt");
        emptyUD = GraphBuilder.buildUndirectedGraph("emptygraph.txt");
        emptyD = GraphBuilder.buildDirectedGraph("emptygraph.txt");
        onelineUD = GraphBuilder.buildUndirectedGraph("onelinegraph.txt");
        onelineD = GraphBuilder.buildDirectedGraph("onelinegraph.txt");
        hamdefUD = GraphBuilder.buildUndirectedGraph("hamiltonian_default.txt");
        hamdefD = GraphBuilder.buildDirectedGraph("hamiltonian_default.txt");
        unconUD = GraphBuilder.buildUndirectedGraph("unconnected_graph.txt");
        unconD = GraphBuilder.buildUndirectedGraph("unconnected_graph.txt");
//        onenodeUD = GraphBuilder.buildUndirectedGraph("onenodegraph.txt");
//        onenodeD = GraphBuilder.buildDirectedGraph("onenodegraph.txt");
//        System.out.println(defaultD);
//        System.out.println("Undirected: " + defaultUD.adjacencySets);
//        System.out.println("Directed: " + defaultD.adjacencySets);
    }

    @org.junit.Test
    public void minDistance() {
        assertEquals("minDistance on complete undirected graph returned value other than 1",1,GraphUtils.minDistance(defaultUD,"0", "3"));
        assertEquals("minDistance on default directed graph returned value other than 2",2,GraphUtils.minDistance(defaultD,"0", "3"));

        assertEquals("minDistance on oneline undirected graph returned value other than 3",3,GraphUtils.minDistance(onelineUD,"0", "3"));
        assertEquals("minDistance on oneline directed graph returned value other than 3",3,GraphUtils.minDistance(onelineD,"0", "3"));
        assertEquals("minDistance on oneline directed graph reverse returned value other than -1",-1,GraphUtils.minDistance(onelineD,"3", "0"));

        assertEquals("minDistance on oneline undirected graph returned value other than 0 when src = dest",0,GraphUtils.minDistance(onelineUD,"0", "0"));
        assertEquals("minDistance on oneline directed graph returned value other than 0 when src = dest",0,GraphUtils.minDistance(onelineD,"2", "2"));

        assertEquals("minDistance on oneline directed graph returned value other than 0 when src = dest",-1,GraphUtils.minDistance(onelineD,"2", "0"));

        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(unconUD,"A", "E"));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(unconD,"A", "E"));

        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(onelineUD,"A", "Z"));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(onelineD,"A", "Z"));
        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(onelineUD,"Z", "A"));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when unreachable",-1,GraphUtils.minDistance(onelineD,"Z", "A"));
    }

    @org.junit.Test
    public void minDistanceNull() {
        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when null source and dest",-1,GraphUtils.minDistance(unconUD,null, null));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when null source and dest",-1,GraphUtils.minDistance(unconD,null, null));
        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when null source",-1,GraphUtils.minDistance(unconUD,null, "E"));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when null source",-1,GraphUtils.minDistance(unconD,null, "E"));
        assertEquals("minDistance on undirected unconnected graph returned value other than -1 when null dest",-1,GraphUtils.minDistance(unconUD,"A", null));
        assertEquals("minDistance on directed unconnected graph returned value other than -1 when null dest",-1,GraphUtils.minDistance(unconD,"A", null));
        assertEquals("minDistance on null graph returned value other than -1 when null graph",-1,GraphUtils.minDistance(null,"A", "E"));
        assertEquals("minDistance on null graph returned value other than -1 when all null",-1,GraphUtils.minDistance(null,null, null));
    }

    @org.junit.Test
    public void nodesWithinDistance() {
        Set<String> result = new HashSet<>();
        result.add("1");
        result.add("2");
        result.add("3");
        assertEquals("nodesWithinDistance on complete undirected graph should return all nodes except source",GraphUtils.nodesWithinDistance(defaultUD,"0", 1),result);

        result = new HashSet<>();
        result.add("1");
        result.add("2");
        assertEquals("nodesWithinDistance on default directed graph should return all nodes except source and 3",GraphUtils.nodesWithinDistance(defaultD,"0", 1),result);

        result = new HashSet<>();
        result.add("F");
        result.add("G");
        assertEquals("nodesWithinDistance on unconnected directed graph should return only nodes in connected portion",GraphUtils.nodesWithinDistance(unconD,"E", 5),result);
        assertEquals("nodesWithinDistance on unconnected graph should return only nodes in connected portion",GraphUtils.nodesWithinDistance(unconUD,"E", 5),result);

        Graph selfloop = GraphBuilder.buildDirectedGraph("self_loop.txt");
        result.clear();
//        System.out.println(GraphUtils.nodesWithinDistance(selfloop,"A", 5));
        assertEquals("nodesWithinDistance on directed graph with self loop should return empty set",GraphUtils.nodesWithinDistance(selfloop,"A", 5),result);

    }

    @org.junit.Test
    public void nodesWithinDistanceNull() {

        assertNull("nodesWithinDistance on complete undirected graph should return null with invalid source",GraphUtils.nodesWithinDistance(defaultUD,null, 1));
        assertNull("nodesWithinDistance on complete undirected graph should return null with invalid source",GraphUtils.nodesWithinDistance(defaultUD,"25", 1));
        assertNull("nodesWithinDistance on complete undirected graph should return null with invalid number",GraphUtils.nodesWithinDistance(defaultUD,"0", 0));
        assertNull("nodesWithinDistance on null graph should return null with invalid source",GraphUtils.nodesWithinDistance(null,"0", 1));
    }

    @org.junit.Test
    public void isHamiltonianCycle() {
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        inputList.add("B");
        inputList.add("C");
        inputList.add("D");
        inputList.add("E");
        inputList.add("A");

        boolean result = GraphUtils.isHamiltonianCycle(hamdefD, inputList);
        assertTrue("Expected True for Hamiltonian Cycle", result);
        result = GraphUtils.isHamiltonianCycle(hamdefUD, inputList);
        assertTrue("Expected True for Hamiltonian Cycle", result);

        inputList.add("G");
        result = GraphUtils.isHamiltonianCycle(hamdefD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle due to extra node that is not in graph", result);
        result = GraphUtils.isHamiltonianCycle(hamdefUD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle due to extra node that is not in graph", result);
        inputList.remove(6);

        inputList.remove(5);
        result = GraphUtils.isHamiltonianCycle(hamdefD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with Last node not equal to first node", result);
        result = GraphUtils.isHamiltonianCycle(hamdefUD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with Last node not equal to first node", result);

        inputList.clear();
        inputList.add("D");
        inputList.add("E");
        inputList.add("A");
        inputList.add("B");
        inputList.add("D");
        System.out.println(inputList);
        result = GraphUtils.isHamiltonianCycle(hamdefD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with list containing less nodes than Graph", result);
        result = GraphUtils.isHamiltonianCycle(hamdefUD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with list containing less nodes than Graph", result);


        inputList.clear();
        inputList.add("A");
        inputList.add("B");
        inputList.add("D");
        inputList.add("E");
        inputList.add("C");
        inputList.add("A");
        System.out.println(inputList);
        result = GraphUtils.isHamiltonianCycle(hamdefD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with non-existing edge", result);
        result = GraphUtils.isHamiltonianCycle(hamdefUD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with non-existing edge", result);

        Graph twoUD = GraphBuilder.buildUndirectedGraph("2nodegraph.txt");
        Graph twoD = GraphBuilder.buildDirectedGraph("2nodegraph.txt");
        inputList.clear();
        inputList.add("A");
        inputList.add("B");
        inputList.add("A");
        System.out.println(inputList);
        result = GraphUtils.isHamiltonianCycle(twoD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with less than 3 nodes", result);
        result = GraphUtils.isHamiltonianCycle(twoUD, inputList);
        assertFalse("Expected False for Hamiltonian Cycle with less than 3 nodes", result);

        Graph threeUD = GraphBuilder.buildUndirectedGraph("3nodegraph.txt");
        Graph threeD = GraphBuilder.buildDirectedGraph("3nodegraph.txt");
        inputList.clear();
        inputList.add("A");
        inputList.add("B");
        inputList.add("C");
        inputList.add("A");
        System.out.println(inputList);
        result = GraphUtils.isHamiltonianCycle(threeD, inputList);
        System.out.println("Directed: " + threeD.adjacencySets);
        assertTrue("Expected True for Hamiltonian Cycle with 3 nodes", result);
        result = GraphUtils.isHamiltonianCycle(threeUD, inputList);
        assertTrue("Expected True for Hamiltonian Cycle with 3 nodes", result);

        inputList.clear();
        inputList.add("A");
        inputList.add("C");
        inputList.add("B");
        inputList.add("A");
        System.out.println(inputList);
        result = GraphUtils.isHamiltonianCycle(threeD, inputList);
        System.out.println("Directed: " + threeD.adjacencySets);
        assertFalse("Expected False for Hamiltonian Cycle with 3 nodes with wrong direction edge", result);
        result = GraphUtils.isHamiltonianCycle(threeUD, inputList);
        assertTrue("Expected True for Hamiltonian Cycle with 3 nodes", result);


    }

    @org.junit.Test
    public void isHamiltonianNull(){
        List<String> inputList = new ArrayList<>();
        inputList.add("A");
        inputList.add("B");
        inputList.add("C");
        inputList.add("D");
        inputList.add("E");
        inputList.add("A");
        assertFalse("Expected null graph input to return false",GraphUtils.isHamiltonianCycle(null,inputList));
        assertFalse("Expected null inputList to return false",GraphUtils.isHamiltonianCycle(hamdefD,null));

        inputList.clear();
        inputList.add("A");
        inputList.add(null);
        inputList.add("C");
        inputList.add("D");
        inputList.add("E");
        inputList.add("A");
        assertFalse("Expected null graph input to return false",GraphUtils.isHamiltonianCycle(hamdefD,inputList));
    }
}