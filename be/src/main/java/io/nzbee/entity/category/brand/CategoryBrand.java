package io.nzbee.entity.category.brand;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_brand", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("2")
public class CategoryBrand extends Category {

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    @JsonIgnore
    private List<Brand> brands;
	
	@Transient
	private Long brandCount;
	
	public CategoryBrand() {
		super();
	}

	@Override
	public Long getObjectCount() {
		// TODO Auto-generated method stub
		return brandCount;
	}

	@Override
	public Long setObjectCount(Long count) {
		// TODO Auto-generated method stub
		return this.brandCount = count;
	}
	
}
