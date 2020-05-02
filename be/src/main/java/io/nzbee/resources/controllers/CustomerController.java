package io.nzbee.resources.controllers;

import java.util.Optional;

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
import io.nzbee.exceptions.CustomerAlreadyExistException;


@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerPortService customerService;

    public CustomerController() {
        super();
    }

    //Registration
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewCustomer(@RequestBody final CustomerDTO customer) {
        LOGGER.debug("Creating a new customer with information: {}", customer);
        
        try {
			customerService.registerNewCustomer(customer);
		} catch (CustomerAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
    
    @GetMapping("/User/{username}")
	public Optional<Customer> getCustomer(@PathVariable String username) {
    	return customerService.findByUsername(username);
	}
	
}
