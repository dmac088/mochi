package io.nzbee.entity.product;

import io.nzbee.entity.IMapper;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Product;

public interface IProductMapper extends IMapper<Product, io.nzbee.entity.product.Product> {

	Product entityToDo(io.nzbee.entity.product.Product e, Brand brand, Department deprartment, ProductCategory category);


}
