package io.javabrains.springbootstarter.services;


import java.util.List;
import io.javabrains.springbootstarter.domain.Brand;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<Brand> getBrands(String lcl, String curr);
	
	List<Brand> getBrandsForCategory(String lcl, String curr, String categoryDesc);

}
