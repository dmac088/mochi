package io.nzbee.entity.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.product.Product;
import io.nzbee.search.ISearchDimension;

@Entity
@Table(name = "brand", schema = "mochi")
@SqlResultSetMapping(
	    name = "CategoryMapping",
	    columns = {
	    		@ColumnResult(name = "object_count")
	    },
	    entities = {
	            @EntityResult(
	                    entityClass = Brand.class,
	                    fields = {
	                        @FieldResult(name = "brandId", 			column = "bnd_id"),
	                        @FieldResult(name = "brandCode", 		column = "bnd_cd"),
	                        @FieldResult(name = "brandAttribute", 	column = "bnd_lcl_id")
	                    }),
	            @EntityResult(
	                    entityClass = BrandAttribute.class,
	                    fields = {
	                        @FieldResult(name = "brandAttributeId", column = "bnd_lcl_id"),
	                        @FieldResult(name = "brandDesc", 		column = "bnd_desc"),
	                        @FieldResult(name = "lclCd", 			column = "lcl_cd"),
	                        @FieldResult(name = "brand", 			column = "bnd_id")
	                    })
		    })
public class Brand implements ISearchDimension {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;

	@NaturalId
	@Column(name="bnd_cd", unique = true, updatable = false)
	@Field(analyze = Analyze.NO, store=Store.YES)
	private String brandCode;
	
	@ManyToMany(mappedBy = "brands")
	@JsonIgnore
	private List<CategoryBrand> categories;

	@OneToMany(	mappedBy="brand",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<Product> products;

	@OneToMany(	mappedBy="brand",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<BrandAttribute> attributes = new ArrayList<BrandAttribute>();
	
	@Transient
	private BrandAttribute brandAttribute;
	
	@Transient
	private String locale;
	
	@Transient
	private String currency;
	
	public String getLocale() {
		return locale;
	}

	public Long getId() {
		return this.brandId;
	}
	
	public void setId(Long id) {
		this.brandId = id;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getBrandDescENGB() {
		Optional<BrandAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("en-GB")).findFirst();
		if(pa.isPresent()) {
			return pa.get().getBrandDesc();
		}
		return "Not Applicable"; 
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getBrandDescZHHK() {
		Optional<BrandAttribute> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals("zh-HK")).findFirst();
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public BrandAttribute getBrandAttribute() {
		return brandAttribute;
	}

	public void setBrandAttribute(BrandAttribute brandAttribute) {
		this.brandAttribute = brandAttribute;
	}
	
	public List<BrandAttribute> getAttributes() {
		return attributes;
	}
	
	public List<CategoryBrand> getCategories() {
		return categories;
	}
	
	public void addAttribute(BrandAttribute brandAttribute) {
		this.getAttributes().add(brandAttribute);
		brandAttribute.setBrand(this);		
	}
	
	public void removeAttribute(BrandAttribute brandAttribute) {
		this.getAttributes().remove(brandAttribute);
		brandAttribute.setBrand(null);
	}
	
	public void addBrandCategory(CategoryBrand categoryBrand) {
		this.getCategories().add(categoryBrand);
		categoryBrand.addBrand(this);
	}

	public void removeBrandCategory(CategoryBrand categoryBrand) {
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

	@Override
	public String getCode() {
		return this.getBrandCode();
	}

	@Override
	public String getDesc() {
		return this.getBrandAttribute().getBrandDesc();
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}

}
