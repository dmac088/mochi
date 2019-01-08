package io.javabrains.springbootstarter.services;


import org.springframework.data.domain.Page;


public interface IProductDTOService {
	
	 Page<ProductDTO> getProducts(String lcl, int page, int size);
	 
	 Page<ProductDTO> getProducts(String lcl, Long categoryId, int page, int size);
	 
}
