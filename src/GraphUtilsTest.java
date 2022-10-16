import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
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

    @Before
    public void setUp() throws Exception{
        defaultUD = GraphBuilder.buildUndirectedGraph("student_graph_test.txt");
        defaultD = GraphBuilder.buildDirectedGraph("student_graph_test.txt");
        emptyUD = GraphBuilder.buildUndirectedGraph("emptygraph.txt");
        emptyD = GraphBuilder.buildDirectedGraph("emptygraph.txt");
        onelineUD = GraphBuilder.buildUndirectedGraph("onelinegraph.txt");
        onelineD = GraphBuilder.buildDirectedGraph("onelinegraph.txt");
//        onenodeUD = GraphBuilder.buildUndirectedGraph("onenodegraph.txt");
//        onenodeD = GraphBuilder.buildDirectedGraph("onenodegraph.txt");
        System.out.println(defaultD);
        System.out.println("Undirected: " + defaultUD.adjacencySets);
        System.out.println("Directed: " + defaultD.adjacencySets);
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


    }

    @org.junit.Test
    public void isHamiltonianCycle() {
    }
}