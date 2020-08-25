package io.nzbee.entity.adapters;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.bag.item.BagItemDTO;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductMapper;
import io.nzbee.entity.role.customer.Customer;

@Service
public class PostgresBagAdapter implements IBagPortService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBagMapper bagMapper;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Override
	public Bag findByCode(String userName) {
		Optional<io.nzbee.entity.bag.Bag> ob = bagService.findByCode(userName);
		
		Person p = (ob.isPresent())
				   ? (Person) ob.get().getParty()
				   : personService.findByUsernameAndRole(userName, Customer.class).get();
		
		//if there is no current bag, get a new one
		io.nzbee.entity.bag.Bag b = (ob.isPresent())
									? ob.get()
									: new io.nzbee.entity.bag.Bag();
	
		//map the bag to a domain object
		return bagMapper.entityToDo(p, b);
	}
	
	@Override
	public Bag addItemToBag(String userName, BagItemDTO bagItem) {
		Bag b = this.findByCode(userName);
		Product p = productMapper.entityToDo(productService.findByCode(bagItem.getProductUPC()).get());
		BagItem bi = new BagItem(b, p, bagItem.getQuantity());
		b.getBagItems().add(bi);
		this.save(b);
		return b;
	}


	@Override
	public void save(Bag domainObject) {
		bagService.save(bagMapper.doToEntity(domainObject));
	}

	@Override
	public void update(Bag domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bag domainObject) {
		// TODO Auto-generated method stub
		
	}


	
}
