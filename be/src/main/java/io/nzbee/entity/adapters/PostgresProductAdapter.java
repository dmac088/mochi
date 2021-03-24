package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.physical.IPhysicalProductService;
import io.nzbee.entity.product.physical.PhysicalProductDTO;
import io.nzbee.entity.product.physical.PhysicalProductEntity;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.shipping.IShippingProductService;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.exceptions.product.ProductNotFoundException;
import io.nzbee.search.ISearchService;
import io.nzbee.search.facet.IFacet;

@Component
public class PostgresProductAdapter implements IProductPortService {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IPhysicalProductService physicalProductService;
	
	@Autowired
	private IShippingProductService shippingProductService;

	@Autowired
	private IProductAttributeService productAttributeService;

	@Autowired
	private IProductPriceService productPriceService;

	@Autowired
	private ISearchService searchService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	private IProductPriceTypeService productPriceTypeService;

	@Autowired
	private IProductStatusRepository productStatusService;

	@Autowired
	private IProductMapper productMapper;

	@Override
	@Transactional(readOnly = true)
	public Product findByCode(String locale, String currency, String code) {
		ProductDTO dto = productService.findByCode(locale, currency, code)
				.orElseThrow(() -> new ProductNotFoundException("Product for code " + code + " not found!"));
		return mapHelper(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findByDesc(String locale, String currency, String desc) {
		ProductDTO dto = productService.findByDesc(locale, currency, desc)
				.orElseThrow(() -> new ProductNotFoundException("Product for description " + desc + " not found!"));;
		return mapHelper(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> List<Product> findAllByType(String locale, String currency, Class<T> cls) {
		List<ProductDTO> lp = productService.findAllByType(locale, 
														   currency, 
														   Constants.primaryProductRootCategoryCode, 
														   mapDomainClassToEntityClass(cls)
														   );
		return lp.stream().map(pe -> mapHelper(pe)).collect(Collectors.toList());
	}
	
	public static Class<?> mapDomainClassToEntityClass(Class<?> cls) {
		if(cls.equals(PhysicalProduct.class)) {
			return PhysicalProductEntity.class;
		} else if (cls.equals(ShippingProduct.class)) {
			return ShippingProductEntity.class;
		}
		return PhysicalProductEntity.class;
	}
	
	public static Class<?> mapDomainClassToDTOClass(Class<?> cls) {
		if(cls.equals(PhysicalProduct.class)) {
			return PhysicalProductDTO.class;
		} else if (cls.equals(ShippingProduct.class)) {
			return ShippingProductDTO.class;
		}
		return PhysicalProductDTO.class;
	}

	@Override
	public Page<Product> search(String locale, String currency, String categoryCode, int page, int size, String sort, String searchTerm,
			Set<IFacet> selectedFacets, Set<IFacet> returnFacets) {

		return searchService.findAll(locale, currency, categoryCode, searchTerm, page,
					size, sort, selectedFacets, returnFacets).map(p -> {
					Product pDo = productMapper.DTOToDo(p);
					return pDo;
				});
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll(String locale, String currency, Set<String> codes) {
		List<ProductDTO> lp =  productService.findAll(locale, currency, Constants.primaryProductRootCategoryCode, new StringCollectionWrapper(codes));
		return lp.stream().map(pe -> mapHelper(pe)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		
		Page<ProductDTO> pp = productService.findAll(
															locale, 
															currency,
															categoryCode, 
															new StringCollectionWrapper(categoryCodes), 
															new StringCollectionWrapper(brandCodes), 
															new StringCollectionWrapper(tagCodes),
															maxPrice,
															page, 
															size, 
															sort);
				
		return new PageImpl<Product>(
						// receive a list of entities and map to domain objects
						pp.stream().map(pe -> mapHelper(pe)).collect(Collectors.toList()), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)),
						pp.getTotalElements());		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PhysicalProduct> findAllPhysicalProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		
		
		//cannot downcast here, 
		Page<PhysicalProductDTO> pp = physicalProductService.findAll(
															locale, 
															currency,
															categoryCode, 
															new StringCollectionWrapper(categoryCodes), 
															new StringCollectionWrapper(brandCodes), 
															new StringCollectionWrapper(tagCodes),
															maxPrice,
															page, 
															size, 
															sort);
				
		return new PageImpl<PhysicalProduct>(
						// receive a list of entities and map to domain objects
						pp.stream().map(pe -> (PhysicalProduct) mapHelper(pe)).collect(Collectors.toList()), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)),
						pp.getTotalElements());		
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<ShippingProduct> findAllShippingProducts(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {

		Page<ShippingProductDTO> pp = shippingProductService.findAll(
															locale, 
															currency,
															categoryCode, 
															new StringCollectionWrapper(categoryCodes), 
															new StringCollectionWrapper(brandCodes), 
															new StringCollectionWrapper(tagCodes),
															maxPrice,
															page, 
															size, 
															sort);

		return new PageImpl<ShippingProduct>(
						// receive a list of entities and map to domain objects
						pp.stream().map(pe -> (ShippingProduct) mapHelper(pe)).collect(Collectors.toList()), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)),
						pp.getTotalElements());		
		
	}
	
	@Override
	@Transactional
	public void save(Product domainObject) {
		if (domainObject instanceof PhysicalProduct) {

			Optional<ProductEntity> op = productService.findByCode(domainObject.getProductUPC());

			PhysicalProductEntity product = (op.isPresent()) 
					? (PhysicalProductEntity) op.get()
					: new PhysicalProductEntity();

			// find the department
			DepartmentEntity d = departmentService.findByCode(domainObject.getDepartment().getDepartmentCode()).get();
			
			// get all the categories
			Set<CategoryProductEntity> lcp = 
					categoryService.findAll(domainObject.getCategories().stream().map(cc -> cc.getCategoryCode()).collect(Collectors.toSet()))
																		.stream().map(cd -> (CategoryProductEntity) Hibernate.unproxy(cd)).collect(Collectors.toSet());
			
			//get all the tags
			Set<String> tagCodes = domainObject.getTags().stream().map(t -> t.getTagCode()).collect(Collectors.toSet());
			List<TagEntity> tags = tagService.findAll(tagCodes);	
			
		
			// find the brand
			BrandEntity b = brandService.findByCode(domainObject.getBrand().getBrandCode()).get();

			Optional<ProductAttributeEntity> opa = productAttributeService.findByCode(domainObject.getLclCd(), domainObject.getProductUPC());

			ProductAttributeEntity pa = (opa.isPresent()) ? opa.get()
					: (new ProductAttributeEntity());

			pa.setProductDesc(domainObject.getProductDesc());
			pa.setProductLongDesc(domainObject.getProductLongDesc());
			pa.setProductImage(domainObject.getProductImage());
			pa.setLclCd(domainObject.getLclCd());

			Currency curr = currencyService.findByCode(domainObject.getCurrency()).get();

			ProductPriceType ptr = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
			ProductPriceType ptm = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();

			ProductStatusEntity ps = productStatusService.findByProductStatusCode(Constants.activeSKUCode).get();

			Optional<ProductPriceEntity> oprcr = 
					productPriceService.findOne(domainObject.getProductUPC(), 
												Constants.retailPriceCode, 
												domainObject.getCurrency());

			//retail price
			ProductPriceEntity prcr = (oprcr.isPresent()) 
								? oprcr.get()
								: new ProductPriceEntity();

			prcr.setType(ptr);
			prcr.setCurrency(curr);
			prcr.setPriceValue(domainObject.getProductRetail());

			Optional<ProductPriceEntity> oprcm = 
					productPriceService.findOne(domainObject.getProductUPC(), 
												Constants.markdownPriceCode, 
												domainObject.getCurrency());

			//markdown price
			ProductPriceEntity prcm = (oprcm.isPresent()) 
								? oprcm.get()
								: new ProductPriceEntity();

			prcm.setType(ptm);
			prcm.setCurrency(curr);
			prcm.setPriceValue(domainObject.getProductMarkdown());

			product.setProductUPC(domainObject.getProductUPC());
			product.setProductCreateDt(domainObject.getProductCreateDt());
			product.setDepartment(d);
			lcp.forEach(c -> {
				product.addCategory(c);
			});
			product.setBrand(b);
			tags.forEach(t -> {
				product.addTag(t);
			});
			product.addProductPrice(prcr);
			product.addProductPrice(prcm);
			product.setProductStatus(ps);
			product.addProductAttribute(pa);
			
			List<Promotion> lp = domainObject.getPromotions();
			lp.forEach(p -> {
				Optional<PromotionEntity> promoe = promotionService.findByCode(p.getPromotionCode());
				product.addPromotion(promoe.get());
			});

			productService.save(domainObject.getLclCd(), domainObject.getCurrency(), product);

		}

	}
	
	
	private Product mapHelper(ProductDTO dto) {
		return productMapper.DTOToDo(dto);
	}

	@Override
	public void update(Product domainObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency) {
		return searchService.getSuggestions(searchTerm, rootCategory, locale, currency);
	}

	@Override
	public void delete(Product domainObject) {
		// TODO Auto-generated method stub
		
	}

}
