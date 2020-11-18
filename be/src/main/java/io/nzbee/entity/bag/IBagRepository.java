package io.nzbee.entity.bag;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IBagRepository extends CrudRepository<BagEntity, Long> {
	
	@Query("SELECT be " +
		   "FROM BagEntity be " + 
		   "JOIN be.party p " +
		   "JOIN p.partyUser u " + 
		   "WHERE u.username = :userName")
	Optional<BagEntity> findByPartyPartyUserUsername(String userName);

}

