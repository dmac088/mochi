package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.domain.product.Product;

public interface IProductPortService extends IProductDimensionService<Product> {

	Product findByCode(String locale, String currency, String code);

	Product findByDesc(String locale, String currency, String desc);

	Set<Product> findAll(String locale, String currency);

	Set<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, Double price, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy);

	Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc, List<Product> collect,
			String sortBy);

}
