package io.nzbee.entity.party;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PartyTypeRepository extends CrudRepository<PartyType, Long> {

	List<PartyType> findAll();

}
