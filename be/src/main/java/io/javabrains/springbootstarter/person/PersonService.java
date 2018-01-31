package io.javabrains.springbootstarter.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository; 
	
	public List<Person> getAllPersons() {
		List<Person> Persons = new ArrayList<>();
		Iterator<Person> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	public Person getPerson(String id) {
		Person p = personRepository.findOne(id);
		return p;
	}
	
	public void addPerson(Person person) {
		personRepository.save(person);
	}
	
	public void updatePerson(String id, Person person) {
		personRepository.save(person);
	}
	
	public void deletePerson(String id) {
		personRepository.delete(id);
	}
	
}
