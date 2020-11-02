package io.nzbee.entity.adapters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.bag.item.IBagItemMapper;
import io.nzbee.entity.bag.item.IBagItemService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.customer.CustomerEntity;

@Service
public class PostgresBagItemAdapter implements IBagItemPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBagItemService bagItemService;
	
	@Autowired
	private IBagItemMapper bagItemMapper;
	
	@Override
	@Transactional
	public void save(BagItem domainObject) {
		bagItemService.save(bagItemMapper.doToEntity(domainObject));
	}

	@Override
	public void update(BagItem domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagItem domainObject) {
		LOGGER.debug("call PostgresBagItemAdapter.delete with parameters {}", domainObject.getProduct().getProductUPC());
		Optional<PersonEntity> op = personService.findByUsernameAndRole(domainObject.getBag().getCustomer().getUserName(), CustomerEntity.class);
		BagEntity b = op.get().getBag();
		Optional<io.nzbee.entity.bag.item.BagItemEntity> obi = b.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals(domainObject.getProduct().getProductUPC())).findAny();
		io.nzbee.entity.bag.item.BagItemEntity bi = obi.get();
		b.removeItem(bi);
		bagService.save(b); 
	}

}
