package io.nzbee.entity.category.product;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.Product;

@Entity
@Table(name = "category_product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("1")
public class CategoryProduct extends Category  {
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private List<Product> products;
	
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
