package io.javabrains.springbootstarter.services;
import java.util.List;

public interface IProductService {

	 //List<ProductDTO> getProducts();
	 
	 List<ProductDTO> getProducts(String lcl);
	 
	// ProductDTO getProduct(Long productId);
}
