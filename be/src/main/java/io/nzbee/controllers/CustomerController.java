package io.nzbee.controllers;

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

import io.nzbee.domain.Customer;
import io.nzbee.services.GenericResponse;
import io.nzbee.services.customer.CustomerAlreadyExistException;
import io.nzbee.services.customer.ICustomerService;


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
    	LOGGER.debug("Finding an existing customer with user name: {}", userName);
    	//System.out.println("Get Customer");
		return customerService.getCustomer(userName);
    }
    

    //Registration
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewCustomer(@RequestBody final Customer customer) {
        LOGGER.debug("Creating a new customer with information: {}", customer);
        
        try {
        	//System.out.println("Create Customer");
			customerService.registerNewCustomer(customer);
		} catch (CustomerAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
    
    @PostMapping("/Customer/Delete")
    public GenericResponse deleteCustomer(@RequestBody final Customer customer) {
        LOGGER.debug("Deleting user account with information: {}", customer);
        
        try {
        	//System.out.println("Delete Customer");
			customerService.deleteCustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
    
    @PostMapping("/Customer/Update")
    public GenericResponse updateCustomer(@RequestBody final Customer customer) {
        LOGGER.debug("Updating user account with information: {}", customer);
        
        try {
        	//System.out.println("Update Customer");
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
}
