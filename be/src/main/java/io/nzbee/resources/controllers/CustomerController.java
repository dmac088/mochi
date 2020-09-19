package io.nzbee.resources.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.dto.customer.CustomerDTOIn;
import io.nzbee.dto.customer.ICustomerDTOMapper;
import io.nzbee.resources.customer.CustomerResource;
import io.nzbee.resources.customer.CustomerResourceAssembler;
import io.nzbee.security.events.OnRegistrationCompleteEvent;


@RestController
@RequestMapping("/api")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICustomerService customerService;
 
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private CustomerResourceAssembler customerResourceAssembler;
    
    @Autowired ICustomerDTOMapper customerDTOMapper;

    public CustomerController() {
        super();
    }

    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
    	LOGGER.debug("call CustomerController.currentUserName");
        return principal.getName();
    }
    
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewCustomer(@RequestBody final CustomerDTOIn customer, final HttpServletRequest request) {
        LOGGER.debug("Signing up a new customer with information: {}", customer);
        
        Customer c = customerService.registerNewCustomer(customer);
        customerService.addCustomerLocation(c, getClientIP(request));
        
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(c, request.getLocale(), getAppUrl(request)));
        
        return new GenericResponse("success");
    }
    
    @GetMapping("/registrationConfirmation")
    public GenericResponse confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        final String result = customerService.validateVerificationToken(token);
        if (result.equals("valid")) {
            customerService.authWithoutPassword(token);
            return new GenericResponse("success");
        }
        return new GenericResponse("failure");
    }
    
    @GetMapping("/Customer")
	public ResponseEntity<CustomerResource> getCustomer(Principal customer) {   	
    	Customer c = customerService.findByUsername(customer.getName());
    	return ResponseEntity.ok(customerResourceAssembler.toModel(customerDTOMapper.doToDto(c)));
	}
       
    @PostMapping("/Customer/Update")
    public GenericResponse updateCustomer(@RequestBody final CustomerDTOIn customer) {
    	customerService.update(customer);
    	return new GenericResponse("success");
	}
    
    @PostMapping("/Customer/Delete/{username}")
    public GenericResponse deleteCustomer(@PathVariable String username) {
    	customerService.delete(username);
    	return new GenericResponse("success");
	}
    
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
    
    private String getAppUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    
}