package app.netlify.gledyson.jpa.customer;

public class CustomerNotFoundResponse {
	private final String status;
	private final Integer statusCode;
	private final String message;
	
	CustomerNotFoundResponse(String message) {
		this.status = "Error";
		this.statusCode = 404;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
	
}
