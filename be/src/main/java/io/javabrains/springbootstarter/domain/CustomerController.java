package io.javabrains.springbootstarter.domain;


import java.util.List;
import javax.servlet.http.HttpSession;
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
    public List<Customer> getAllCustomers(HttpSession session) {
		System.out.println("calling getAllCustomers");
		return customerService.getAllCustomers();
	}	
	
	@GetMapping("/Customer/{id}")
	public Party getCustomer(@PathVariable Long id) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(id).get().getRoleParty();
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
