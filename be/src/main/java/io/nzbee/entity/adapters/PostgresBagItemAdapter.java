package io.nzbee.entity.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.entity.bag.item.IBagItemMapper;
import io.nzbee.entity.bag.item.IBagItemService;

@Service
public class PostgresBagItemAdapter implements IBagItemPortService {

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
		bagItemService.delete(bagItemMapper.doToEntity(domainObject));
	}

}
