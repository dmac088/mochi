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
	@RequestMapping("/Person/{id}")
	public Person getPerson(@PathVariable String id) {
		System.out.println("calling getPerson");
		return personService.getPerson(id);
	}
	
	//map the post request to this particular method
	/*@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Persons")
	public void addPerson(@RequestBody Person person) {
		Personservice.addPerson(person);
	}*/
	
	
	@RequestMapping(method=RequestMethod.POST, value="/Person")
	public String addPerson( Person person) {
		System.out.println("calling addPerson");
		personService.addPerson(person);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Person/{id}")
	public void updatePerson(@RequestBody Person person, @PathVariable String id) {
		System.out.println("calling updatePerson");
		personService.updatePerson(id, person);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Person/{id}")
	public void deletePerson(@PathVariable String id) {
		System.out.println("calling deletePerson");
		personService.deletePerson(id);
	}
	
}
