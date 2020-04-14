package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
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
	private ICategoryService categoryService;
	
	@Autowired 
	private ICategoryMapper categoryMapper;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired 
	private IBrandMapper brandMapper;
	
	@Autowired
	private IBrandService departmentService;
	
	@Autowired 
	private IDepartmentMapper departmentMapper;
	
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
		Set<io.nzbee.entity.category.product.CategoryProduct> lcp = pe.getCategories();
		
		Brand bdo = brandMapper.entityToDo(be, locale, currency);
		Department ddo = departmentMapper.entityToDo(de, locale, currency);
		List<ProductCategory> lpc = lcp.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toList());
		
		return productMapper.entityToDo(pe, bdo, ddo, lpc);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
