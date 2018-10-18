package io.javabrains.springbootstarter.domain;

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
	
	@Transactional(readOnly = true)
	public List<Person> getAllPersons() {
		List<Person> Persons = new ArrayList<>();
		Iterator<Person> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	public Person getPerson(Long id) {
		Person p = personRepository.findOne(id);
		return p;
	}

	public void addPerson(Person person) {
		personRepository.save(person);
	}
	
	public void updatePerson(Long id, Person person) {
		personRepository.save(person);
	}
	
	public void deletePerson(Long id) {
		personRepository.delete(id);
	}
	
}
