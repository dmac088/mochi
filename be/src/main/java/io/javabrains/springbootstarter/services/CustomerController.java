package io.javabrains.springbootstarter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.domain.PartyPerson;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerService customerService;

    public CustomerController() {
        super();
    }

    //Registration
    @PostMapping("/user/registration")
    public GenericResponse registerNewPersonCustomer(@RequestBody final PartyPerson person) {
    	System.out.println("called registerNewPersonCustomer");
    	System.out.println(person.getGivenNameEn());
        LOGGER.debug("Registering user account with information: {}", person);
        
        try {
			customerService.registerNewPersonCustomer(person);
		} catch (CustomerAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
}
