package io.nzbee.entity.adapters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.brand.IBrandMapper;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.IDepartmentMapper;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.search.dto.facet.IFacet;

@Component
public class PostgresProductAdapter implements IProductPortService {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired 
	private IProductMapper productMapper;
	
	@Autowired 
	private IBrandMapper brandMapper;
	
	@Autowired 
	private IDepartmentMapper departmentMapper;
	
	@Autowired 
	private ICategoryMapper categoryMapper;
	
	@Override
	public Product findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product domainObject) {
		if(domainObject instanceof Food) {
			io.nzbee.entity.product.food.Food product = new io.nzbee.entity.product.food.Food();
			Food food = (Food) domainObject;

			//find the department
			io.nzbee.entity.product.department.Department d =
					departmentService.findByCode(domainObject.getLclCd(), 
												 domainObject.getCurrency(),
												 domainObject.getDepartment().getDepartmentCode()).get();	
			
			//get all the categories	
			Set<io.nzbee.entity.category.product.CategoryProduct> lcp =  
					categoryService.findAll(	domainObject.getLclCd(), 
										 		domainObject.getCurrency(), 
										 		domainObject.getCategories().stream().map(cc -> cc.getCategoryCode()).collect(Collectors.toSet())
										 	 ).stream().map(cd -> (CategoryProduct) cd).collect(Collectors.toSet());
			
			io.nzbee.entity.category.product.CategoryProduct primaryCategory =
						(CategoryProduct) categoryService.findByCode(	domainObject.getLclCd(), 
																		domainObject.getCurrency(), 
																		food.getPrimaryCategory().getCategoryCode()).get();
			
			//find the brand 
			io.nzbee.entity.brand.Brand b = 
					brandService.findByCode( domainObject.getLclCd(), 
										 	 domainObject.getCurrency(),
										 	 domainObject.getBrand().getBrandCode()).get();
			
			
			io.nzbee.entity.product.attribute.ProductAttribute pa = new io.nzbee.entity.product.attribute.ProductAttribute();
			pa.setProductDesc(domainObject.getProductDesc());
			pa.setProductImage(domainObject.getProductImage());
			pa.setLclCd(domainObject.getLclCd());
			
			Currency curr = currencyService.findByCode(food.getCurrency()).get();
					
			io.nzbee.entity.product.price.ProductPriceType ptr = productPriceTypeService.findByCode("RET01").get();
			io.nzbee.entity.product.price.ProductPriceType ptm = productPriceTypeService.findByCode("MKD01").get();
			
			io.nzbee.entity.product.status.ProductStatus ps = productStatusService.findByProductStatusCode("ACT01").get();
			
			io.nzbee.entity.product.price.ProductPrice prcr = new io.nzbee.entity.product.price.ProductPrice();
			prcr.setType(ptr);
			prcr.setCurrency(curr);
			prcr.setPriceValue(food.getProductRetail());
			
			io.nzbee.entity.product.price.ProductPrice prcm = new io.nzbee.entity.product.price.ProductPrice();
			prcm.setType(ptm);
			prcm.setCurrency(curr);
			prcm.setPriceValue(food.getProductMarkdown());
					
			product.setProductUPC(food.getProductUPC());
			product.setProductCreateDt(food.getProductCreateDt());
			product.setCountryOfOrigin(food.getCountryOfOrigin());
			product.setExpiryDate(food.getExpiryDate());
			product.setLocale(food.getLclCd());
			product.setCurrency(food.getCurrency());
			product.setDepartment(d);
			lcp.stream().forEach(c -> {
				product.addCategory(c);
			});
			product.setPrimaryCategory(primaryCategory);
			product.setBrand(b);
			product.addProductPrice(prcr);
			product.addProductPrice(prcm);
			product.setProductStatus(ps);
			product.addProductAttribute(pa);
			
			productService.save(product);
			
		}
		
	}

	@Override
	public Product findByCode(String locale, String currency, String code) {
		io.nzbee.entity.product.Product pe = productService.findByCode(locale, currency, code).get();
		return mapHelper(pe);
	}

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		io.nzbee.entity.product.Product pe = productService.findByDesc(locale, currency, desc).get();
		return mapHelper(pe);
	}

	@Override
	public Set<Product> findAll(String locale, String currency) {
		List<io.nzbee.entity.product.Product> lp = productService.findAll(locale, currency);
		return lp.stream().map(pe -> mapHelper(pe)).collect(Collectors.toSet());
		
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

	//returns a user interface object, rule broken, need to change to return a domain object 
		@Override
		public Page<Product> findAll(	 String locale, 
								 String currency, 
								 Double price,
								 int page, 
								 int size, 
								 String categoryDesc,
								 Set<IFacet> selectedFacets,
								 String sortBy) {
			
			@SuppressWarnings("unused")
			Set<IFacet> returnFacets = new HashSet<IFacet>();
			
			Page<io.nzbee.entity.product.Product> pp = productService.findAll(
					   locale, 
					   currency,
					   new Double(0),
					   price,
					   "RET01",
					   PageRequest.of(page, size), 
					   categoryDesc, 
					   selectedFacets.stream().filter(c -> c.getType().equals("category")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()),
					   selectedFacets.stream().filter(c -> c.getType().equals("brand")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()),
					   selectedFacets.stream().filter(c -> c.getType().equals("tag")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()));

			return new PageImpl<Product>(
			//receive a list of entities and map to domain objects
			pp.stream().map(pe -> mapHelper(pe))
			.collect(Collectors.toList()),
			PageRequest.of(page, size),
			pp.getTotalElements());

		}
		
		@Override
		public Page<Product> findAll(String locale, 
									 String currency, 
									 String categoryDesc,
									 int page, 
									 int size,
									 Set<IFacet> selectedFacets,
									 String sortBy) {
			
			Page<io.nzbee.entity.product.Product> pp = productService.findAll(
											   locale, 
											   currency,
											   "MKD01",
											   PageRequest.of(page, size), 
											   categoryDesc, 
											   selectedFacets.stream().filter(c -> c.getType().equals("category")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()),
											   selectedFacets.stream().filter(c -> c.getType().equals("brand")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()),
											   selectedFacets.stream().filter(c -> c.getType().equals("tag")).map(c -> c.getPayload().getCode()).collect(Collectors.toList()));
						
			 return new PageImpl<Product>(
				    //receive a list of entities and map to domain objects
					pp.stream().map(pe -> mapHelper(pe))
					.collect(Collectors.toList()),
					PageRequest.of(page, size),
					pp.getTotalElements());
		}

		
		private Product mapHelper(io.nzbee.entity.product.Product pe) {
			io.nzbee.entity.brand.Brand be = pe.getBrand();
			io.nzbee.entity.product.department.Department de = pe.getDepartment();
			io.nzbee.entity.category.Category c = pe.getPrimaryCategory();
		
			Brand bdo = brandMapper.entityToDo(be, pe.getLocale(), pe.getCurrency());
			Department ddo = departmentMapper.entityToDo(de, pe.getLocale(), pe.getCurrency());
			ProductCategory cdo = (ProductCategory) categoryMapper.entityToDo(c, pe.getLocale(), pe.getCurrency());
			return productMapper.entityToDo(pe, bdo, ddo, cdo);	
		}

		@Override
		public void update(Product domainObject) {
			// TODO Auto-generated method stub
			
		}
	
}
