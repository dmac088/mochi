package io.javabrains.springbootstarter.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.domain.Customer;
import io.javabrains.springbootstarter.services.CustomerAlreadyExistException;
import io.javabrains.springbootstarter.services.GenericResponse;
import io.javabrains.springbootstarter.services.ICustomerService;


@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerService customerService;

    public CustomerController() {
        super();
    }
    
    @GetMapping("/Customers")
    public List<Customer> getCustomers() {
    	return customerService.getCustomers();
    }
    
    
    @GetMapping("/Customer/UserName/{userName}")
    public Customer getCustomer(@PathVariable String userName) {
		return customerService.getCustomer(userName);
    }

    //Registration
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewPersonCustomer(@RequestBody final Customer customer) {
        LOGGER.debug("Registering user account with information: {}", customer);
        
        try {
			customerService.registerNewCustomer(customer);
		} catch (CustomerAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
    
    //Registration
    @GetMapping("/user/registration")
    public GenericResponse test() {
    	System.out.println("called test");
        return new GenericResponse("success");
    }
}
