package io.nzbee.entity.category.product;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "category_product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "cat_id")
@DiscriminatorValue("1")
@JsonTypeName("categoryproduct")
public class CategoryProduct extends Category  {
	
	
	@ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	@Transient
	private int productCount;
	
	@Transient
	private boolean hasParent;
	
	public String getCategoryCode() {
		return super.getCategoryCode();
	}
	
	public boolean hasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public Set<ProductEntity> getProducts() {
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
	
	public void addProduct(ProductEntity product) {
		this.getProducts().add(product);
		product.getCategories().add(this);
	}
	
	public void removeProduct(ProductEntity product) {
		this.getProducts().remove(product);
		product.removeProductCategory(this);
	}

	@Override
	@JsonIgnore
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public void setType(String type) {
		
	}

	@Override
	public String getCode() {
		return this.getCategoryCode();
	}

	@Override
	@JsonIgnore
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProduct)) return false;
        return categoryCode != null && categoryCode.equals(((Category) o).getCategoryCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getCategoryCode());
    }
}
