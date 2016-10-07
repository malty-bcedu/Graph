package graph;

import java.util.ArrayList;

import graph.exception.InvalidConstructionException;

public class Tree<T> extends ConnectedGraph<T> {
	
	protected Vertex<T> root = null;
	
	public Tree(T rootValue) throws InvalidConstructionException {
		super();
		
		if (rootValue == null)
			throw new NullPointerException();
		
		this.root = super.addVertex(rootValue);
	}
	
	public Vertex<T> getRoot() { 
		return root;
	}
	
	public Vertex<T> addVertex(T elm) throws InvalidConstructionException {
		return super.addVertex(elm);
	}
	
	public Edge<T> addEdge(T elm1, T elm2) throws InvalidConstructionException {
		Vertex<T> v1 = findVertex(elm1);
		Vertex<T> v2 = findVertex(elm2);
		
		if (v1 != null && v2 != null) {
			throw new InvalidConstructionException(
					"adding edge (" + elm1 + "," + elm2 + ")" + " creates a cycle");
		}
		
		return super.addEdge(elm1, elm2);
	}
	
	public void print() {
		System.out.println("Tree");
		for (Vertex<T> v: adjacencyList.keySet()) {
			if (v.equals(root))
				System.out.print("(" + v.toString() + ") ");
			else
				System.out.print("[" + v.toString() + "] ");
			
			ArrayList<Edge<T>> edges = adjacencyList.get(v);
			if (edges != null) {
				for (Edge<T> e: edges) {
					if (e.getFirst().equals(v))
						System.out.print(e.getSecond().toString());
					else
						System.out.print(e.getFirst().toString());
					
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
