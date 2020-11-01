package io.nzbee.entity.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.IMapper;
import io.nzbee.entity.party.person.PersonDTO;

public interface IBagMapper extends IMapper<Bag, BagEntity, BagDTO> {

	Bag DTOToDo(String locale, String currency, PersonDTO pDto, BagDTO bDto);

}
