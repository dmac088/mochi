package io.javabrains.springbootstarter.customer;


import java.util.ArrayList;
import java.util.List;

import io.javabrains.springbootstarter.party.Party;
import io.javabrains.springbootstarter.party.PartyService;
import io.javabrains.springbootstarter.person.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	private PartyService partyService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Customer")
    public List<Party> getAllCustomers(HttpSession session) {
		System.out.println("calling getAllCustomers");
		List<Party> pl = new ArrayList<Party>(); 
		for(Customer c : customerService.getAllCustomers()) {
			pl.add(c.getRoleParty());
		};
		return pl;
	}	
	
	@ResponseBody
	@RequestMapping("/Customer/{id}")
	public Party getCustomer(@PathVariable Long id) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(id).getRoleParty();
	}
	
	/*@ResponseBody
	@RequestMapping("/Customer/{id}")
	public Party getCustomer(@PathVariable String id) {
		System.out.println("calling getCustomer");
		return customerService.getCustomer(id).getRoleParty();
	}*/

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Customer")
	public void addCustomer(@RequestBody Customer customer) {	
		System.out.println("calling addCustomer");
		customerService.addCustomer(customer);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Customer/{id}")
	public void updateCustomer(@RequestBody Customer Customer, @PathVariable Long id) {
		System.out.println("calling updateCustomer");
		customerService.updateCustomer(id, Customer);
	}

/*	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		System.out.println("calling deleteCustomer");
		customerService.deleteCustomer(id);
	}
*/	
}
