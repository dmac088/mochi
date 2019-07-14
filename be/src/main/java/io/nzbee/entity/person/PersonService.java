package io.nzbee.entity.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository; 
	
	
	@PreAuthorize("hasAuthority('PERSON_READER')")
	@Transactional(readOnly = true)
	public List<Person> getAllPersons() {
		List<Person> Persons = new ArrayList<>();
		Iterator<Person> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	@PreAuthorize("hasAuthority('PERSON_READ')")
	@Transactional(readOnly = true)
	public Person getPerson(Long id) {
		Person p = personRepository.findByPartyId(id);
		return p;
	}

	@Transactional
	public void addPerson(Person person) {
		personRepository.save(person);
	}
	
	@PreAuthorize("hasAuthority('PERSON_UPDATE')")
	@Transactional
	public void updatePerson(Long id, Person person) {
		personRepository.save(person);
	}
	
	
	@PreAuthorize("hasAuthority('PERSON_DELETE')")
	@Transactional
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
}
