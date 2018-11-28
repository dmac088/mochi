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
public class RoleCustomerController {
	
	@Autowired
	private RoleCustomerService customerService;
	
	
	@GetMapping("/Customer")
    public List<RoleCustomer> getAllCustomers() {
		System.out.println("calling getAllCustomers");
		return customerService.getAllCustomers();
	}	
	
	@GetMapping("/Customer/{userName}")
	public RoleCustomer getCustomer(@PathVariable String userName) {
		System.out.println("calling getCustomer");
		return (RoleCustomer) customerService.getCustomer(userName).get();
	}
	
	@PostMapping("/Customer")
	public ResponseEntity<RoleCustomer> addCustomer(@RequestBody RoleCustomer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<RoleCustomer>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/Customer/{id}")
	public void updateCustomer(@RequestBody RoleCustomer Customer, @PathVariable Long id) {
		System.out.println("calling updateCustomer");
		customerService.updateCustomer(id, Customer);
	}

	@DeleteMapping("/Customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		System.out.println("calling deleteCustomer");
		//customerService.deleteCustomer(id);
	}

}
