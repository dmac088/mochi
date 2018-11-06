package io.javabrains.springbootstarter.domain;

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



@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private PersonService personService;	
	

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
	
	@PostMapping("/Person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		System.out.println("calling addPerson");
		personService.addPerson(person);
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
