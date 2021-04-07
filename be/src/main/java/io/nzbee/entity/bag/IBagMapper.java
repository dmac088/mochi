package io.nzbee.entity.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.IDomainObjectMapper;
import io.nzbee.entity.party.person.PersonDTO;

public interface IBagMapper extends IDomainObjectMapper<Bag, BagEntity, BagDTO> {

	Bag DTOToDo(String locale, String currency, PersonDTO pDto, BagDTO bDto);

}
