package io.nzbee.entity.party;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PartyTypeRepository extends CrudRepository<PartyTypeEntity, Long> {

	List<PartyTypeEntity> findAll();

}
