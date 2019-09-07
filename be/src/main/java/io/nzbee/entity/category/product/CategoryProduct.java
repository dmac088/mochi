package io.nzbee.entity.category.product;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Immutable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.Product;

@Entity
@Immutable
@Table(name = "vw_category_product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
public class CategoryProduct extends Category  {

	@Transient
	private Long categoryTypeId = (long) 1;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @OrderBy
    @JsonIgnore
    private List<Product> products;
	
	@Column(name="product_count")
	private Long productCount;

	public List<Product> getProducts() {
		return products;
	}
	
	public CategoryType getCategoryType() {
		return super.getCategoryType();
	}
	
	public Long getProductCount() {
		return productCount;
	}

}
