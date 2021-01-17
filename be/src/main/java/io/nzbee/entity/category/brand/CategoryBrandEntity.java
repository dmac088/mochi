package io.nzbee.entity.category.brand;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;

@Entity
@Table(name = "category_brand", schema = "mochi")
@DiscriminatorValue("2")
@JsonTypeName("categorybrand")
public class CategoryBrandEntity extends CategoryEntity {

	private static final long serialVersionUID = 7221370251309880198L;

	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    @JsonIgnore
    private Set<BrandEntity> brands;
	
	@Transient
	private Long brandCount;
	
	public CategoryBrandEntity() {
		super();
	}

	@Override
	public Long getObjectCount() {
		return brandCount;
	}

	@Override
	public void setObjectCount(Long count) {
		this.brandCount = count;
	}
	
	public void addBrand(BrandEntity brand) {
		this.brands.add(brand);
		brand.addBrandCategory(this);
	}
	
	public void removeBrand(BrandEntity brand) {
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
		return false;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProductEntity)) return false;
        return categoryCode != null && categoryCode.equals(((CategoryEntity) o).getCategoryCode());
    }
 
    @Override
    public int hashCode() {
        return 32;
    }
	
}
