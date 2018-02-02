package io.javabrains.springbootstarter.customer;


import java.util.List;

import io.javabrains.springbootstarter.party.PartyType;
import io.javabrains.springbootstarter.party.PartyTypeService;
import io.javabrains.springbootstarter.person.*;
import io.javabrains.springbootstarter.role.RoleType;
import io.javabrains.springbootstarter.role.RoleTypeService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PartyTypeService partyTypeService;

	@Autowired
	private RoleTypeService roleTypeService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Customer")
    public List<Customer> getAllCustomers(HttpSession session) {
		System.out.println("calling getAllCustomers");
		List<Customer> c1 = customerService.getAllCustomers();
        return c1;
    }	
	
	@ResponseBody
	@RequestMapping("/Customer/{id}")
	public Customer getCustomer(@PathVariable String id) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(id);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Customer")
	public String addCustomer(Person person, Customer customer) {	
		System.out.println("calling addCustomer");
		person.setPartyType(partyTypeService.getPartyType("Person"));
		customer.setRoleType(roleTypeService.getRoleType("Customer"));
		customer.setRoleParty(person);
		personService.addPerson(person);
		customerService.addCustomer(customer);
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Customer/{id}")
	public void updateCustomer(@RequestBody Customer Customer, @PathVariable String id) {
		System.out.println("calling updateCustomer");
		customerService.updateCustomer(id, Customer);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Customer/{id}")
	public void deleteCustomer(@PathVariable String id) {
		System.out.println("calling deleteCustomer");
		customerService.deleteCustomer(id);
	}
	
}
