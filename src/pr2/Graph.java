package pr2;

import java.util.*;


public class Graph<V> {

    // Lista de adyacencia.
    public Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice ‘v‘ al grafo.
     *
     * @param v vértice a añadir.
     * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso contrario.
     */
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        } else {
            adjacencyList.put(v, new HashSet<V>());
            return true;
        }
    }

    /**
     * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo.
     * En caso de que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return ‘true‘ si no existía el arco y ‘false‘ en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        if (!adjacencyList.containsKey(v1)) {
            adjacencyList.put(v1, new HashSet<V>());
        }
        if (!adjacencyList.containsKey(v2)) {
            adjacencyList.put(v2, new HashSet<V>());
        }
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
        return true;
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a ‘v‘.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     * @throws Exception si el vértice no existe en el grafo.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (adjacencyList.containsKey(v)) {
            return adjacencyList.get(v);
        } else {
            throw new Exception("El vértice no existe");
        }
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return ‘true‘ si ‘v‘ es un vértice del grafo.
     */
    public boolean containsVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return true;
        }
        return adjacencyList.containsKey(v);
    }

    /**
     * Método ‘toString()‘ reescrito para la clase ‘Graph.java‘.
     *
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : adjacencyList.keySet()) {
            sb.append(v.toString() + ": ");
            for (V w : adjacencyList.get(v)) {
                sb.append(w.toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2‘. En caso contrario, devuelve ‘null‘.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices desde ‘v1‘ hasta ‘v2‘ pasando por arcos del grafo.
     */
    public List<V> onePath(V v1, V v2) {
        // Comprobar si ambos vértices existen en el grafo
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null;
        }

        // Crear tabla traza y pila abierta
        Map<V, V> trace = new HashMap<>();
        Stack<V> open = new Stack<>();

        // Añadir v1 a la pila y la tabla traza con valor null
        open.push(v1);
        trace.put(v1, null);

        boolean found = false;
        while (!open.isEmpty() && !found) {
            // Sacar el vértice superior de la pila
            V current = open.pop();

            // Ver si se ha encontrado el destino
            if (current.equals(v2)) {
                found = true;
            } else {
                // Añadir los adyacentes a la pila y actualizar la tabla traza
                for (V adj : adjacencyList.get(current)) {
                    if (!trace.containsKey(adj)) {
                        open.push(adj);
                        trace.put(adj, current);
                    }
                }
            }
        }

        // Reconstruir el camino y devolverlo
        if (found) {
            List<V> path = new ArrayList<>();
            V current = v2;
            while (current != null) {
                path.add(current);
                current = trace.get(current);
            }
            Collections.reverse(path);
            return path;
        } else {
            // No se ha encontrado el destino
            return null;
        }
    }
}