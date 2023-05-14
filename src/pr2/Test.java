package pr2;
import pr2.Graph;

import java.util.*;
import static org.junit.Assert.*;

public class Test {

    public static void main(String[] args) {


            System.out.println("\nTest onePathFindsAPath");
            System.out.println("----------------------");
// Se construye el grafo.
            Graph<Integer> g = new Graph<>();
            g.addEdge(1, 2);
            g.addEdge(3, 4);
            g.addEdge(1, 5);
            g.addEdge(5, 6);
            g.addEdge(6, 4);
// Se construye el camino esperado.
            List<Integer> expectedPath = new ArrayList<>();
            expectedPath.add(1);
            expectedPath.add(5);
            expectedPath.add(6);
            expectedPath.add(4);
//Se comprueba si el camino devuelto es igual al esperado.

            assertEquals(expectedPath, g.onePath(1, 4));

            System.out.println(g.adjacencyList);


        }

}


