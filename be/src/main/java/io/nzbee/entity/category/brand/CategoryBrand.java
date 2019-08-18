package io.nzbee.entity.category.brand;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;

@Entity
@Table(name = "category_brand", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class CategoryBrand extends Category {

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    @JsonIgnore
    private List<Brand> brands;

    public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

}
