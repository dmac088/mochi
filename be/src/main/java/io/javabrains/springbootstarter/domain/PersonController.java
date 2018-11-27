package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.security.UserRole;
import io.javabrains.springbootstarter.security.UserRoleService;



@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonService personService;	
	
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping("/Person")
    public List<Person> getAllPersons() {
		System.out.println("calling getAllPersons");
        return personService.getAllPersons();
    }	
	
	@GetMapping("/Person/{id}")
	public Person getPerson(@PathVariable Long id) {
		System.out.println("calling getPerson");
		return personService.getPerson(id);
	}
	

	
	@PostMapping("/Customer/Signup")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		//Should this go into an existing service class or a new one?
				
		//create the role object
		person.setPartyRoles(new ArrayList<Role>());
		Customer c1 = new Customer();
		c1.setRoleStart(new Date());
		
		//add the role to person
		person.addRole(c1);
		
		//add the person to role
		c1.setRoleParty(person);
		
		//add the user object
		person.getPartyUser().setUserRoles(new ArrayList<UserRole>());
		person.getPartyUser().addUserRole(userRoleService.loadUserRoleByRoleName("CUSTOMER"));
		
		person.getPartyUser().setEnabled(true);
		
		//persist the parent
		personService.addPerson(person);
		
		//return the response entity
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@PutMapping("/Person/{id}")
	public void updatePerson(@RequestBody Person person, @PathVariable Long id) {
		System.out.println("calling updatePerson");
		personService.updatePerson(id, person);
	}

	@DeleteMapping("/Person/{id}")
	public void deletePerson(@PathVariable Long id) { 
		System.out.println("calling deletePerson");
		personService.deletePerson(id);
	}
	
}
