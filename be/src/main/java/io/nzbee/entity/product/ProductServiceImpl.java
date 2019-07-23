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
	private ProductDao productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public List<Product> getProducts() {
		return productDAO.getAll();
	}
	
	@Override
	public List<Product> getProducts(String locale, String currency, List<Long> productIds) {
		// TODO Auto-generated method stub
		return productDAO.getAll(locale, currency, productIds);
	}
	
	@Override
	public Page<Product> getProducts(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds) {
		return productDAO.findAll(	categoryIds,
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
	public Optional<Product> getProduct(Long Id) {
		return productDAO.findById(Id);
	}
	
	@Override
	public Optional<Product> getProduct(String upc) {
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
	public Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currencyCode,
			String priceTypeDesc, String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories) {
		// TODO Auto-generated method stub
		return productRepository.count(categoryTypeCode, categoryDesc, locale, currencyCode, priceTypeDesc, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories);
	}

	@Override
	public Long getCountForTags(String categoryTypeCode, String categoryDesc, String locale, String currencyCode,
			String priceTypeDesc, String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories, List<Long> tagIds, int inHandlingTags) {
		// TODO Auto-generated method stub
		return productRepository.countForTags(categoryTypeCode, categoryDesc, locale, currencyCode, priceTypeDesc, productStatusCode, brandIds, inHandlingBrands, categoryIds, inHandlingCategories, tagIds, inHandlingTags);
	}

	@Override
	public Double getMaxPrice(String categoryDesc, String locale, String priceType, String currency,
			List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds) {
		// TODO Auto-generated method stub
		return productDAO.getMaxPrice(categoryDesc, locale, priceType, currency, categoryIds, brandIds, tagIds);
	}

}