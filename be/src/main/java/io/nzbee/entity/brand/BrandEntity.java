package io.nzbee.entity.brand;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.Constants;
import io.nzbee.entity.brand.attribute.BrandAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "brand", schema = "mochi")
@SqlResultSetMapping(
	    name = "BrandMapping",
	    columns = {
	    		@ColumnResult(name = "object_count")
	    },
	    entities = {
	            @EntityResult(
	                    entityClass = BrandEntity.class,
	                    fields = {
	                        @FieldResult(name = "brandId", 			column = "bnd_id"),
	                        @FieldResult(name = "brandCode", 		column = "bnd_cd")
	                    }),
	            @EntityResult(
	                    entityClass = BrandAttributeEntity.class,
	                    fields = {
	                        @FieldResult(name = "brandAttributeId", column = "bnd_lcl_id"),
	                        @FieldResult(name = "brandDesc", 		column = "bnd_desc"),
	                        @FieldResult(name = "lclCd", 			column = "lcl_cd"),
	                        @FieldResult(name = "brand", 			column = "bnd_id")
	                    })
		    })
public class BrandEntity implements ISearchDimension {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;

	@NaturalId
	@Column(name="bnd_cd", unique = true, updatable = false)
	private String brandCode;
	
	@ManyToMany(mappedBy = "brands")
	@JsonIgnore
	private Set<CategoryBrandEntity> categories = new HashSet<CategoryBrandEntity>();

	@OneToMany(	mappedBy="brand",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<ProductEntity> products = new HashSet<ProductEntity>();

	@OneToMany(	mappedBy="brand",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<BrandAttributeEntity> attributes = new HashSet<BrandAttributeEntity>();
	
	@Transient
	private BrandAttributeEntity brandAttribute;
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;
	
	@Transient
	private Long objectCount;

	public String getLocale() {
		return locale;
	}

	public Long getBrandId() {
		return this.brandId;
	}
	
	public void setBrandId(Long id) {
		this.brandId = id;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getBrandDescENGB() {
		Optional<BrandAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeENGB)).findFirst();
		if(pa.isPresent()) {
			return pa.get().getBrandDesc();
		}
		return "Not Applicable"; 
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getBrandDescZHHK() {
		Optional<BrandAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeZHHK)).findFirst();
		if(pa.isPresent()) {
			return pa.get().getBrandDesc();
		}
		return "Not Applicable"; 
	}
	
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getBrandToken() {
		return this.getBrandCode();
	}
	
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}
	
	public BrandAttributeEntity getBrandAttribute() {
		return brandAttribute;
	}

	public void setBrandAttribute(BrandAttributeEntity brandAttribute) {
		this.brandAttribute = brandAttribute;
	}
	
	public Set<BrandAttributeEntity> getAttributes() {
		return attributes;
	}
	
	public Set<CategoryBrandEntity> getCategories() {
		return categories;
	}
	
	public void addAttribute(BrandAttributeEntity brandAttribute) {
		this.getAttributes().add(brandAttribute);
		brandAttribute.setBrand(this);		
	}
	
	public void removeAttribute(BrandAttributeEntity brandAttribute) {
		this.getAttributes().remove(brandAttribute);
		brandAttribute.setBrand(null);
	}
	
	public void addBrandCategory(CategoryBrandEntity categoryBrand) {
		this.getCategories().add(categoryBrand);
		categoryBrand.addBrand(this);
	}

	public void removeBrandCategory(CategoryBrandEntity categoryBrand) {
		this.getCategories().remove(categoryBrand);
		categoryBrand.removeBrand(this);
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}

	@Override
	public String getCode() {
		return this.getBrandCode();
	}

	@Override
	public String getDesc() {
		return this.getBrandAttribute().getBrandDesc();
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
        if (!(o instanceof BrandEntity)) return false;
        return brandCode != null && brandCode.equals(((BrandEntity) o).getBrandCode());
    }
 
    @Override
    public int hashCode() {
    	HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.brandCode);
        return hcb.toHashCode();
    }

}