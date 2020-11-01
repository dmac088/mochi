package io.nzbee.entity.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.IMapper;
import io.nzbee.entity.party.person.PersonEntity;

public interface IBagMapper extends IMapper<Bag, BagDTO, BagEntity> {

	io.nzbee.domain.bag.Bag entityToDo(PersonEntity p, BagEntity e);

	io.nzbee.domain.bag.Bag entityToDo(String locale, String currency, PersonEntity p, BagEntity e);

}
