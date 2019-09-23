package io.nzbee.entity.category.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("1")
public class CategoryProduct extends Category  {
	
	private Long productCount;
	
	public CategoryProduct() {
		super();
	}

	@Override
	public Long getObjectCount() {
		// TODO Auto-generated method stub
		return productCount;
	}
}
