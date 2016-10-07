package graph;

public class Edge<T> {

	private Vertex<T> v1;
	private Vertex<T> v2;
	
	public Edge(Vertex<T> v1, Vertex<T> v2) throws NullPointerException {
		if (v1 == null || v2 == null)
			throw new NullPointerException();
		
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Vertex<T> getFirst() {
		return v1;
	}
	
	public Vertex<T> getSecond() {
		return v2;
	}

	public Vertex<T> getAdjacentVertex(Vertex<T> v) {
		if (v1 == v)
			return v2;
		else if (v2 == v)
			return v1;
		else
			return null;
	}
	
	public boolean equals(Edge<T> o) {
		if (v1 == o.getFirst() && v2 == o.getSecond())
			return true;
		else if (v1 == o.getSecond() && v2 == o.getFirst())
			return true;
		
		return false;
	}
	
	public String toString() {
		return v1.toString() + "," + v2.toString();
	}
}
