package app.netlify.gledyson.jpa.customer;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository repository;

	@GetMapping("/customer")
	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}

	@PostMapping("/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@RequestBody Customer newCustomer) {

		log.info("newCustomer: " + newCustomer);

		return repository.save(newCustomer);
	}

	@GetMapping("/customer/{id}")
	public Customer findOne(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@PutMapping("/customer/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer updateOne(@RequestBody Customer newCustomer, @PathVariable Long id) {

		return repository.findById(id).map(customer -> {

			customer.setFirstName(newCustomer.getFirstName());
			customer.setLastName(newCustomer.getLastName());

			return repository.save(customer);

		}).orElseThrow(() -> new CustomerNotFoundException(id));

	}

	@DeleteMapping("/customer/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOne(@PathVariable Long id) {
		
		Customer customer = repository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id));
		
		repository.delete(customer);
	}
}
