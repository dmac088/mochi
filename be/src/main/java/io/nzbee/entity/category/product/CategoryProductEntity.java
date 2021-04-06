package io.nzbee.entity.category.product;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "category_product", schema = "mochi")
@DiscriminatorValue("1")
public class CategoryProductEntity extends CategoryEntity {

	private static final long serialVersionUID = 8166164110478278072L;
	
	@Transient
	private Long productCount;
	
	@Transient
	private boolean hasParent;
	
	@ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	public String getCategoryCode() {
		return super.getCategoryCode();
	}
	
	public Set<ProductEntity> getProducts() {
		return products;
	}

	public boolean hasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	@Override
	public Long getObjectCount() {
		return productCount;
	}

	@Override
	public void setObjectCount(Long count) {
		productCount = count;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public void setType(String type) {
		
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
