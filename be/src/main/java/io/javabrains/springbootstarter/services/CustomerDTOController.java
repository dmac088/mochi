package io.javabrains.springbootstarter.services;

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


@RestController
@RequestMapping("/api")
public class CustomerDTOController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerDTOService customerService;

    public CustomerDTOController() {
        super();
    }
    
    @GetMapping("/Customers")
    public List<CustomerDTO> getCustomers() {
    	return customerService.getCustomers();
    }
    
    
    @GetMapping("/Customer/UserName/{userName}")
    public CustomerDTO getCustomer(@PathVariable String userName) {
		return customerService.getCustomer(userName);
    }

    //Registration
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewPersonCustomer(@RequestBody final CustomerDTO customer) {
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
