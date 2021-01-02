package io.nzbee.entity.party.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPartyAddressEntityRepository extends CrudRepository<PartyAddressEntity, Long> {

	Optional<PartyAddressEntity> findByPartyPartyUserUsername(String userName);

	@Query(	  " SELECT new io.nzbee.entity.party.address.PartyAddressDTO(a.addressId, "
			+ "															 a.addressLine1, "
			+ "															 a.addressLine2, "
			+ "															 a.addressLine3, "
			+ "															 a.country, "
			+ "															 a.postcode,"
			+ "															 at.addressTypeId,"
			+ "															 at.addressTypeCode,"
			+ "															 at.addressTypeDesc, "
			+ "															 treat(p AS PersonEntity).partyId, "
			+ "															 treat(p AS PersonEntity).givenName, "
			+ "															 treat(p AS PersonEntity).familyName, "
			+ "															 u.username, "
			+ "															 treat(r AS CustomerEntity).customerNumber, "	
			+ "															 u.enabled "			
			+ ") "
			+ " FROM PartyAddressEntity a "
			+ " JOIN a.type at"
			+ " JOIN a.party p "
			+ " JOIN p.partyRoles r "
			+ " JOIN r.roleType rt "
			+ " JOIN p.partyUser u "
			+ " WHERE u.username = :userName "
			+ " AND rt.roleTypeDesc = :roleName" )
	Optional<PartyAddressDTO> findByUsernameAndRole(String userName, String roleName);
	
}
 