package graph;

import graph.exception.InvalidConstructionException;

public class ConnectedGraph<T> extends Graph<T> {
	
	public ConnectedGraph() {
		super();
	}
	
	public Vertex<T> addVertex(T elm) throws InvalidConstructionException{
		if (elm == null)
			throw new NullPointerException();
		
		Vertex<T> v = findVertex(elm);

		if (v == null && adjacencyList.size() != 0) {
				throw new InvalidConstructionException(
						"adding vertex (" + elm +")" + " disconnects tree");
		}
		
		return super.addVertex(elm);
	}
	
	public Edge<T> addEdge(T elm1, T elm2) throws InvalidConstructionException {
		Vertex<T> v1 = findVertex(elm1);
		Vertex<T> v2 = findVertex(elm2);

		if (v1 == null && v2 == null) {
			throw new InvalidConstructionException(
					"adding edge (" + elm1 + "," + elm2 + ")" + " creates a disconnected graph");
		}
		
		return super.addEdge(elm1, elm2);
	}
	
}
