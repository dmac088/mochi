package io.nzbee.entity.category.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.Product;

@Entity
@Table(name = "category_product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("1")
@JsonTypeName("categoryproduct")
public class CategoryProduct extends Category  {
	
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {
			            CascadeType.PERSIST,
			            CascadeType.MERGE
			        })
    @JoinTable(name = "product_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
	
	@Transient
	private int productCount;
	
	@Transient
	private boolean hasParent;
	
	@Field(store=Store.YES,analyze=Analyze.NO)
	public String getCategoryCode() {
		return super.getCategoryCode();
	}
	
	public boolean hasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public Set<Product> getProducts() {
		return products;
	}

	@Override
	public int getObjectCount() {
		return productCount;
	}

	@Override
	public void setObjectCount(int count) {
		productCount = count;
	}
	
	public void addProduct(Product product) {
		this.getProducts().add(product);
		product.getCategories().add(this);
	}
	
	public void removeProduct(Product product) {
		this.getProducts().remove(product);
		product.removeProductCategory(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProduct)) return false;
        return categoryId != null && categoryId.equals(((Category) o).getCategoryId());
    }
 
    @Override
    public int hashCode() {
        return 32;
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
		return true;
	}
}
