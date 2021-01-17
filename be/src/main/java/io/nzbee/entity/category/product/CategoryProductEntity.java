package io.nzbee.entity.category.product;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.category.CategoryEntity;

@Entity
@Table(name = "category_product", schema = "mochi")
@DiscriminatorValue("1")
public class CategoryProductEntity extends CategoryEntity {

	private static final long serialVersionUID = 8166164110478278072L;
	
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
	public String getCode() {
		return this.getCategoryCode();
	}

	@Override
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
