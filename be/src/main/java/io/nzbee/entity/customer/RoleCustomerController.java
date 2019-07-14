package io.nzbee.entity.customer;


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
		return customerService.getAllCustomers();
	}	
	
	@GetMapping("/Customer/{userName}")
	public RoleCustomer getCustomer(@PathVariable String userName) {
		return (RoleCustomer) customerService.getCustomer(userName).get();
	}
	
	@PostMapping("/Customer")
	public ResponseEntity<RoleCustomer> addCustomer(@RequestBody RoleCustomer customer) {
		return new ResponseEntity<RoleCustomer>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/Customer/{id}")
	public void updateCustomer(@RequestBody RoleCustomer Customer, @PathVariable Long id) {
		customerService.updateCustomer(id, Customer);
	}

	@DeleteMapping("/Customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		//customerService.deleteCustomer(id);
	}

}
