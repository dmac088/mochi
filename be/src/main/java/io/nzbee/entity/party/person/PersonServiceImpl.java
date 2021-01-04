package io.nzbee.entity.party.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonRepository personRepository; 
	
	@Autowired
	private IPersonDao personDao;
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READER')")
	@Transactional(readOnly = true)
	public List<PersonEntity> findAll() {
		List<PersonEntity> Persons = new ArrayList<>();
		Iterator<PersonEntity> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	@Transactional(readOnly = true)
	public Optional<PersonEntity> findById(Long id) {
		return personRepository.findByPartyId(id);
	}
	
	@Override
	public Optional<PersonEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(PersonEntity person) {
		personDao.save(person);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_UPDATE')")
	@Transactional
	public void update(PersonEntity p) {
		personRepository.save(p);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_DELETE')")
	@Transactional
	public void delete(PersonEntity p) {
		personRepository.delete(p);
	}

	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	public Optional<PersonEntity> findByUsernameAndRole(String userName, String roleType) {
		return personDao.findByUsernameAndRole(userName, roleType);
	}
	
	@Override
	public boolean userExists(String userName, String roleType) {
		return personDao.findByUsernameAndRole(userName, roleType).isPresent();
	}


}
