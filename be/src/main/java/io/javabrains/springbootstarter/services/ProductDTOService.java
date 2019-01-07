package io.javabrains.springbootstarter.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Party;
import io.javabrains.springbootstarter.domain.PartyPerson;
import io.javabrains.springbootstarter.domain.PartyPersonRepository;
import io.javabrains.springbootstarter.domain.PartyRepository;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributeRepository;
import io.javabrains.springbootstarter.domain.ProductRepository;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleCustomer;
import io.javabrains.springbootstarter.errors.CustomerAlreadyExistException;
import io.javabrains.springbootstarter.security.User;
import io.javabrains.springbootstarter.security.UserRole;
import io.javabrains.springbootstarter.security.UserRoleService;

@Service
@Transactional
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
	
	@Override
	@Transactional
	public List<ProductDTO> getProducts(String lcl) {
		List<ProductDTO> pl = new ArrayList<ProductDTO>();
		List<ProductAttribute> pal = productAttributeRepository.findByLclCd(lcl);
		for(ProductAttribute pa : pal) {
			ProductDTO p = new ProductDTO();
			p.setProductId(pa.getProduct().getProductId());
			p.setProductImage(pa.getProductImage());
			p.setProductRrp(pa.getProductRrp());
			p.setLclCd(pa.getLclCd());
			p.setProductUPC(pa.getProduct().getProductUPC());
			p.setProductDesc(pa.getProductDesc());
			p.setProductCreateDt(pa.getProduct().getProductCreateDt());
			pl.add(p);
		}
		return pl;
	}	
}