package io.nzbee.entity.category.brand;

import java.util.Set;
import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_brand", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("2")
@JsonTypeName("categorybrand")
public class CategoryBrand extends Category {

	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    @JsonIgnore
    private Set<Brand> brands;
	
	@Transient
	private int brandCount;
	
	public CategoryBrand() {
		super();
	}

	@Override
	public int getObjectCount() {
		// TODO Auto-generated method stub
		return brandCount;
	}

	@Override
	public void setObjectCount(int count) {
		// TODO Auto-generated method stub
		this.brandCount = count;
	}
	
	public void addBrand(Brand brand) {
		this.brands.add(brand);
		brand.addBrandCategory(this);
	}
	
	public void removeBrand(Brand brand) {
		this.brands.remove(brand);
		brand.removeBrandCategory(this);
	}

	@Override
	@JsonIgnore
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCode() {
		return this.getCategoryCode();
	}

	@Override
	public String getDesc() {
		return this.getCategoryAttribute().getCategoryDesc();
	}

	@Override
	public int getCount() {
		return this.getObjectCount();
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}
	
}
