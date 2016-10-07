package graph;

public class Vertex<T>{

	private T data;
	private boolean visited;
	private Vertex<T> parent;
	public boolean setVisited;
	
	public Vertex(T data) {
		this.data = data;
		visited = false;
		parent = null;
	}
	
	public T getData() {
		return data;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setParent(Vertex<T> parent) {
		this.parent = parent;
	}
	
	public Vertex<T> getparent() {
		return parent;
	}
	
	public boolean equals(Vertex<T> v) {
		return data.equals(v.getData());
	}
	
	public String toString() {
		return data.toString();
	}

}
