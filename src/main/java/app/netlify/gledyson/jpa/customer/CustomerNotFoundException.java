package app.netlify.gledyson.jpa.customer;

public class CustomerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Long id) {
		super(String.format("Customer id {%s} not found.", id));
	}
}
