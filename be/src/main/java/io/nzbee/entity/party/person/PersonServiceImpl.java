package io.nzbee.entity.party.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonRepository personRepository; 
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READER')")
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		List<Person> Persons = new ArrayList<>();
		Iterator<Person> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	@Transactional(readOnly = true)
	public Optional<Person> findById(long id) {
		return personRepository.findByPartyId(id);
	}
	
	@Override
	public Optional<Person> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	@PreAuthorize("hasAuthority('PERSON_CREATE')")
	public void save(Person person) {
		personRepository.save(person);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_UPDATE')")
	@Transactional
	public void update(Person p) {
		personRepository.save(p);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_DELETE')")
	@Transactional
	public void delete(Person p) {
		personRepository.delete(p);
	}


	@Override
	public List<Person> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	public Optional<Person> findByUsernameAndRole(String userName, Class<?> roleType) {
		// TODO Auto-generated method stub
//		return personRepository.find;
		return null;
	}
	
}
