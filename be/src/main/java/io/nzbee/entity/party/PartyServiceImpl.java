package io.nzbee.entity.party;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="partyService")
@Transactional
public class PartyServiceImpl implements IPartyService {

	@Autowired
	private IPartyRepository partyRepository; 
	
	@Autowired
	@Qualifier(value = "partyDao")
	private IPartyDao partyDAO;
	
	@Override
	public List<Party> findByRoleType(Class<?> roleType) {
		return partyDAO.findAllByRoleName(roleType.getSimpleName());
	}
	
	@Override
	public List<Party> findAll() {
		return partyRepository.findAll();
	}

	@Override
	public Optional<Party> findById(long id) {
		return partyRepository.findById(id);
	}

	@Override
	public Optional<Party> findByCode(String code) {
		return null;
	}

	@Override
	public void save(Party Party) {
		partyRepository.save(Party);
	}
	
	@Override
	public void update(Party t) {
		partyRepository.save(t);
	}

	@Override
	public void delete(Party t) {
		partyRepository.delete(t);
	}

}
