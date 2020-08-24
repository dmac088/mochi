package io.nzbee.entity.bag;

import io.nzbee.entity.IMapper;
import io.nzbee.entity.party.person.Person;

public interface IBagMapper extends IMapper<io.nzbee.domain.bag.Bag, Bag> {

	io.nzbee.domain.bag.Bag entityToDo(Person p, Bag e);

}
