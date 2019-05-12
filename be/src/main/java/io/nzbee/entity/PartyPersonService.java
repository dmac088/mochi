package io.nzbee.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartyPersonService {

	@Autowired
	private PartyPersonRepository personRepository; 
	
	
	@PreAuthorize("hasAuthority('PERSON_READER')")
	@Transactional(readOnly = true)
	public List<PartyPerson> getAllPersons() {
		List<PartyPerson> Persons = new ArrayList<>();
		Iterator<PartyPerson> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	@PreAuthorize("hasAuthority('PERSON_READ')")
	@Transactional(readOnly = true)
	public PartyPerson getPerson(Long id) {
		PartyPerson p = personRepository.findByPartyId(id);
		return p;
	}

	@Transactional
	public void addPerson(PartyPerson person) {
		personRepository.save(person);
	}
	
	@PreAuthorize("hasAuthority('PERSON_UPDATE')")
	@Transactional
	public void updatePerson(Long id, PartyPerson person) {
		personRepository.save(person);
	}
	
	
	@PreAuthorize("hasAuthority('PERSON_DELETE')")
	@Transactional
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
}
