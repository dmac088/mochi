package io.nzbee.entity.adapters.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.IProductAttributeService;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.physical.PhysicalProductDomainObjectDTO;
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
import io.nzbee.exceptions.NotFoundException;

@Component
public class ProductAdapter implements IProductPortService {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IShippingProductService shippingProductService;

	@Autowired
	private IProductAttributeService productAttributeService;

	@Autowired
	private IProductPriceService productPriceService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICategoryService categoryService;
	
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
				.orElseThrow(() -> new NotFoundException("Product for code " + code + " not found!"));
		return mapHelper(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findByDesc(String locale, String currency, String desc) {
		ProductDTO dto = productService.findByDesc(locale, currency, desc)
				.orElseThrow(() -> new NotFoundException("Product for description " + desc + " not found!"));
		return mapHelper(dto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ShippingProduct findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode, String type, Double weightKg) {
		ProductDTO dto = shippingProductService.findByDestinationAndTypeAndBagWeight(locale, currency, destinationCode, type, weightKg)
				.orElseThrow(() -> new NotFoundException("Shipping product not found!"));
		return (ShippingProduct) mapHelper(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> List<Product> findAllByType(String locale, String currency, Class<T> cls) {
		List<ProductDTO> lp = productService.findAllByType(locale, 
														   currency, 
														   Constants.defaultProductRootCategoryCode, 
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
			return PhysicalProductDomainObjectDTO.class;
		} else if (cls.equals(ShippingProduct.class)) {
			return ShippingProductDTO.class;
		}
		return PhysicalProductDomainObjectDTO.class;
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
			//BrandEntity b = brandService.findByCode(domainObject.getBrand().getBrandCode()).get();

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
					productPriceService.findByProductCode(domainObject.getProductUPC(), 
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
					productPriceService.findByProductCode(domainObject.getProductUPC(), 
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
			//product.setBrand(b);
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
				product.addPromotion((PromotionProductEntity) promoe.get());
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
	public void delete(Product domainObject) {
		// TODO Auto-generated method stub
		
	}

	


}
