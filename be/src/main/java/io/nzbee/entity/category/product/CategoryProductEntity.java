package io.nzbee.entity.category.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "category_product", schema = "mochi")
@DiscriminatorValue("1")
@JsonTypeName("categoryproduct")
public class CategoryProductEntity extends CategoryEntity implements Serializable  {
	
	private static final long serialVersionUID = 286345140667931120L;

	@ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	@Transient
	private Long productCount;
	
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
	public Long getObjectCount() {
		return productCount;
	}

	@Override
	public void setObjectCount(Long count) {
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
	public Long getCount() {
		return this.getObjectCount();
	}

	@Override
	public boolean isHierarchical() {
		return true;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProductEntity)) return false;
        return categoryCode != null && categoryCode.equals(((CategoryEntity) o).getCategoryCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getCategoryCode());
    }
}
