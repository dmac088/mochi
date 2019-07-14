package io.nzbee.entity.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PartyPersonController {
	
	@Autowired
	private PartyPersonService personService;	
	
	@GetMapping("/Person")
    public List<PartyPerson> getAllPersons() {
        return personService.getAllPersons();
    }	
	
	@GetMapping("/Person/{id}")
	public PartyPerson getPerson(@PathVariable Long id) {
		return personService.getPerson(id);
	}
	
	@PutMapping("/Person/{id}")
	public void updatePerson(@RequestBody PartyPerson person, @PathVariable Long id) {
		personService.updatePerson(id, person);
	}

	@DeleteMapping("/Person/{id}")
	public void deletePerson(@PathVariable Long id) { 
		personService.deletePerson(id);
	}
	
}
