package io.nzbee.resources.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.dto.customer.CustomerDTO;


@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerPortService customerService;

    public CustomerController() {
        super();
    }

    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewCustomer(@RequestBody final CustomerDTO customer) {
        LOGGER.debug("Creating a new customer with information: {}", customer);
        customerService.registerNewCustomer(customer);
        return new GenericResponse("success");
    }
    
    @GetMapping("/Customer/UserName/{username}")
	public Customer getCustomer(@PathVariable String username) {
    	return customerService.findByUsername(username);
	}
    
    @PostMapping("/Customer/Update")
    public GenericResponse updateCustomer(@RequestBody final CustomerDTO customer) {
    	customerService.updateCustomer(customer);
    	return new GenericResponse("success");
	}
    
    @PostMapping("/Customer/Delete/{username}")
    public GenericResponse deleteCustomer(@PathVariable String username) {
    	customerService.deleteCustomer(username);
    	return new GenericResponse("success");
	}
}
