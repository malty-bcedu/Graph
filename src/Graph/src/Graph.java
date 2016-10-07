package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import graph.exception.InvalidConstructionException;

public class Graph<T> {

	protected Hashtable<Vertex<T>, ArrayList<Edge<T>>> adjacencyList = null;
	protected Hashtable<Vertex<T>, Vertex<T>> connectedComponents = null;

	public Graph() {
		adjacencyList = new Hashtable<Vertex<T>, ArrayList<Edge<T>>>();
		connectedComponents = new Hashtable<Vertex<T>, Vertex<T>>();
	}

	public Vertex<T> addVertex(T elm) throws InvalidConstructionException{
		return _addVertex(elm);
	}
	
	private Vertex<T> _addVertex(T elm) throws InvalidConstructionException{
		if (elm == null)
			throw new NullPointerException();

		Vertex<T> v = findVertex(elm);

		if (v == null) {
			v = new Vertex<T>(elm);
			adjacencyList.put(v, new ArrayList<>());
			connectedComponents.put(v, v);
			//System.out.println("Added (" + elm + ")");
		}

		return v;
	}

	public Edge<T> addEdge(T elm1, T elm2) throws InvalidConstructionException{
		if (elm1 == null || elm2 == null)
			throw new NullPointerException();

		Vertex<T> u = findVertex(elm1);
		if (u == null)
			u = _addVertex(elm1);

		Vertex<T> v = findVertex(elm2);
		if (v == null)
			v = _addVertex(elm2);

		Edge<T> edge = new Edge<T>(u, v);

		ArrayList<Edge<T>> list = adjacencyList.get(u);
		list.add(edge);
		adjacencyList.put(u, list);

		list = adjacencyList.get(v);
		list.add(edge);
		adjacencyList.put(v, list);

		if (!v.equals(u))
			unionComponents(u, v);

		//System.out.println("Added: (" + elm1 + "," + elm2 + ")");		
		return edge;
	}

	private void unionComponents(Vertex<T> u, Vertex<T> v) {
		connectedComponents.put(findComponent(u), findComponent(v));
	}

	private Vertex<T> findComponent(Vertex<T> v) {
		Vertex<T> curVertex = v;
		while(!curVertex.equals(connectedComponents.get(curVertex)))
			curVertex = connectedComponents.get(curVertex);
		return curVertex;
	}

	private HashSet<Vertex<T>> getAllComponents(){
		HashSet<Vertex<T>> set = new HashSet<>();
		
		for(Vertex<T> v: connectedComponents.keySet())
			set.add(findComponent(v));
		
		return set;
	}
	
	public Vertex<T> findVertex(T elm) {
		if (elm == null)
			throw new NullPointerException();

		HashSet<Vertex<T>> set = getAllComponents();

		for (Vertex<T> v: set) {
			Vertex<T> foundVertex = BFSearch(v, elm);

			if (foundVertex != null)
				return foundVertex;
		}

		return null;
	}
	
	public Vertex<T> BFSearch(Vertex<T> start, T elm) {
		if (elm == null || start == null)
			throw new NullPointerException();

		if (!adjacencyList.containsKey(start)) {
			System.out.println("Search failed: invalid start vertex");
			return null;
		}

		setAllVisitedFalse();

		ArrayDeque<Vertex<T>> queue = new ArrayDeque<>();
		queue.add(start);
		start.setVisited(true);

		//System.out.print("Search: ");
		while (!queue.isEmpty()) {
			Vertex<T> u = queue.remove();
			//System.out.print(u.getData() + " ");

			if (u.getData().equals(elm)) {
				//System.out.println();
				return u;
			}

			ArrayList<Edge<T>> list = adjacencyList.get(u);
			if (list == null)
				continue;

			for (Edge<T> e: list) {
				Vertex<T> v = e.getAdjacentVertex(u);
				if (v.getVisited() == false) {
					v.setVisited(true);
					queue.add(v);
				}
			}
		}
		//System.out.println();
		return null;
	}

	private void setAllVisitedFalse() {
		for (Vertex<T> u : adjacencyList.keySet())
			u.setVisited(false);
	}
	
	public Tree<T> getMinSpanTree(Vertex<T> start) {
		
		return null;
	}

	public void print() {
		System.out.println("Graph");
		for (Vertex<T> v: adjacencyList.keySet()) {
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
