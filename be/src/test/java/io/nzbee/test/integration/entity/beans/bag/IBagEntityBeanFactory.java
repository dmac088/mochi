package io.nzbee.test.integration.entity.beans.bag;

import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.party.Party;
import io.nzbee.test.integration.entity.beans.IEntityBeanFactory;

public interface IBagEntityBeanFactory extends IEntityBeanFactory<BagEntity> {

	BagEntity getBean(Party customer);

}