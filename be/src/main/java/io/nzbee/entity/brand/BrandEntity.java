package io.nzbee.entity.brand;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.Constants;
import io.nzbee.entity.brand.attribute.BrandAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "brand", schema = "mochi")
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = -3493461048853968278L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;

	@NaturalId
	@Column(name="bnd_cd", unique = true, updatable = false)
	private String brandCode;
	
	@ManyToMany(mappedBy = "brands")
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
	private String locale;
	
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
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeENGB)).findFirst().get().getBrandDesc();
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getBrandDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLclCd().equals(Constants.localeZHHK)).findFirst().get().getBrandDesc();
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
	
	@Override
    public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     BrandEntity pcDto = (BrandEntity) o;
	     return this.getBrandCode() == pcDto.getBrandCode();
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getBrandCode());
    }

}
