package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.department.IDepartmentMapper;

@Component
public class PostgresProductAdapter implements IProductPortService {
	
	@Autowired
	private IProductService productService;
	
	@Autowired 
	private IProductMapper productMapper;
	
	@Autowired 
	private IBrandMapper brandMapper;
	
	@Autowired 
	private IDepartmentMapper departmentMapper;
	
	@Autowired 
	private ICategoryMapper categoryMapper;
	
	@Override
	public Optional<Product> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findByCode(String locale, String currency, String code) {
		
		io.nzbee.entity.product.Product pe = productService.findByCode(locale, currency, code).get();
		io.nzbee.entity.brand.Brand be = pe.getBrand();
		io.nzbee.entity.product.department.Department de = pe.getDepartment();
		io.nzbee.entity.category.Category c = pe.getCategories().stream().findFirst().get();
		
		Brand bdo = brandMapper.entityToDo(be, locale, currency);
		Department ddo = departmentMapper.entityToDo(de, locale, currency);
		ProductCategory cdo = (ProductCategory) categoryMapper.entityToDo(c, locale, currency);
		
		return productMapper.entityToDo(pe, bdo, ddo, cdo);
	}

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(String locale, String currency, Double price, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy) {
		
		return null;
	}

	@Override
	public Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy) {
		
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
