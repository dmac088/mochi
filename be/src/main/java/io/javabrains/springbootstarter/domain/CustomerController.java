package io.javabrains.springbootstarter.domain;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/Customer")
    public List<Customer> getAllCustomers() {
		System.out.println("calling getAllCustomers");
		return customerService.getAllCustomers();
	}	
	
	@GetMapping("/Customer/{userName}")
	public Party getCustomer(@PathVariable String userName) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(userName).get().getRoleParty();
	}
	
	@PostMapping("/Customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/Customer/{id}")
	public void updateCustomer(@RequestBody Customer Customer, @PathVariable Long id) {
		System.out.println("calling updateCustomer");
		customerService.updateCustomer(id, Customer);
	}

	@DeleteMapping("/Customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		System.out.println("calling deleteCustomer");
		//customerService.deleteCustomer(id);
	}

}
