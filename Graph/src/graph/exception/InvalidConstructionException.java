package graph.exception;

public class InvalidConstructionException extends Exception {
	
	private static final long serialVersionUID = 837647240086911870L;
	private String reason;
	
	public InvalidConstructionException(String reason) {
		this.reason = reason;
	}

	protected String getReason() {
		return reason;
	}
	
	public String toString() {
		return "Invalid construction: " + reason;
	}
	
}
