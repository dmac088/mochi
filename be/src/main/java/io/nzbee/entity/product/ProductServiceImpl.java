package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductDaoImpl productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productDAO.getAll();
	}
	
	@Override
	public List<Product> findAll(String locale, String currency, List<Long> productIds) {
		// TODO Auto-generated method stub
		return productDAO.getAll(locale, currency, productIds);
	}
	
	@Override
	public Page<Product> findAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds) {
		return productDAO.findAllActiveSKUByPrimaryHierarchy(	categoryIds,
									locale,
									priceStart,
									priceEnd,
									priceType,
									currency,
									priceDateStart,
									priceDateEnd,
									pageable,
									brandIds,
									tagIds
								 );
	}

	@Override
	public Optional<Product> findOne(Long Id) {
		return productDAO.findById(Id);
	}
	
	@Override
	public Optional<Product> findOne(String upc) {
		return productDAO.findByUPC(upc);
	}

	@Override
	public Double getMaxMarkDownPrice(String categoryTypeCode, String categoryDesc, String locale, String currencyCode,
			String priceTypeDesc, String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories) {
		// TODO Auto-generated method stub
		return productRepository.maxMarkDownPrice(categoryTypeCode, categoryDesc, locale, currencyCode, priceTypeDesc, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories);
	}

	@Override
	public Double getMaxMarkDownPriceForTags(String categoryTypeCode, String categoryDesc, String locale,
			String currencyCode, String priceTypeDesc, String productStatusCode, List<Long> brandIds,
			int inHandlingBrands, List<Long> categoryIds, int inHandlingCategories, List<Long> tagIds,
			int inHandlingTags) {
		// TODO Auto-generated method stub
		return productRepository.maxMarkDownPriceForTags(categoryTypeCode, categoryDesc, locale, currencyCode, priceTypeDesc, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories, tagIds, inHandlingTags);
	}

	@Override
	public Long getCount(String categoryDesc, String locale,
			String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories) {
		// TODO Auto-generated method stub
		return productRepository.count(categoryDesc, locale, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories);
	}

	@Override
	public Long getCountForTags(String categoryDesc, String locale,
			String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories, List<Long> tagIds, int inHandlingTags) {
		// TODO Auto-generated method stub
		return productRepository.countForTags(categoryDesc, locale, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories, tagIds, inHandlingTags);
	}

	@Override
	public Double getMaxPrice(String categoryDesc, String locale, String priceType, String currency,
			List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds) {
		// TODO Auto-generated method stub
		return productDAO.getMaxPrice(categoryDesc, locale, priceType, currency, categoryIds, brandIds, tagIds);
	}

	@Override
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}

}