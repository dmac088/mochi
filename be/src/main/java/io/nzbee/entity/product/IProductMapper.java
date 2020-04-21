package io.nzbee.entity.product;

import io.nzbee.entity.IMapper;

import java.util.Optional;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Product;

public interface IProductMapper extends IMapper<Product, io.nzbee.entity.product.Product> {

	Optional<Product> entityToDo(Optional<io.nzbee.entity.product.Product> e, 
								 Optional<Brand> brand, 
								 Optional<Department> deprartment, 
								 Optional<ProductCategory> category);
	


}
