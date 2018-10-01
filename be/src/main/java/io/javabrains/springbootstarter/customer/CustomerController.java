package io.javabrains.springbootstarter.customer;


import java.util.List;

import io.javabrains.springbootstarter.party.Party;
import io.javabrains.springbootstarter.person.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PersonService personService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Customer")
    public List<Customer> getAllCustomers(HttpSession session) {
		System.out.println("calling getAllCustomers");
		return customerService.getAllCustomers();
	}	
	
	@ResponseBody
	@RequestMapping("/Customer/{id}")
	public Party getCustomer(@PathVariable Long id) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(id).getRoleParty();
	}
	

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Customer")
	public ResponseEntity<Person> addCustomer(@RequestBody Person person) {
		personService.addPerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Customer/{id}")
	public void updateCustomer(@RequestBody Customer Customer, @PathVariable Long id) {
		System.out.println("calling updateCustomer");
		customerService.updateCustomer(id, Customer);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		System.out.println("calling deleteCustomer");
		//customerService.deleteCustomer(id);
	}

}
