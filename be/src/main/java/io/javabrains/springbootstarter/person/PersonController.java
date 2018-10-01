package io.javabrains.springbootstarter.person;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.javabrains.springbootstarter.customer.Customer;


@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Person")
    public List<Person> getAllPersons(HttpSession session) {
		System.out.println("calling getAllPersons");
        return personService.getAllPersons();
    }	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Person/{id}")
	public Person getPerson(@PathVariable Long id) {
		System.out.println("calling getPerson");
		return personService.getPerson(id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Person")
	public void addPerson(@RequestBody Person person) {
		System.out.println("calling addPerson");
		personService.addPerson(person);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Person/{id}")
	public void updatePerson(@RequestBody Person person, @PathVariable Long id) {
		System.out.println("calling updatePerson");
		personService.updatePerson(id, person);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Person/{id}")
	public void deletePerson(@PathVariable Long id) {
		System.out.println("calling deletePerson");
		personService.deletePerson(id);
	}
	
}
