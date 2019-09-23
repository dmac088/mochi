package io.nzbee.entity.category.brand;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_brand", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("2")
public class CategoryBrand extends Category {

	private Long brandCount;
	
	public CategoryBrand() {
		super();
	}

	@Override
	public Long getObjectCount() {
		// TODO Auto-generated method stub
		return brandCount;
	}
	
}
