package io.nzbee.entity.brand;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.product.Product;


@Entity
@Table(name = "brand", schema = "mochi")
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;

	@Column(name="bnd_cd")
	@Field(store=Store.YES)
	private String brandCode;

	@OneToMany(	mappedBy="brand",  
				cascade = CascadeType.ALL,
				orphanRemoval = true
				)
	@JsonManagedReference
	private List<Product> products;
	
	@ManyToMany(mappedBy = "brands")
	@JsonIgnore
	private List<CategoryBrand> categories;
	
	@OneToMany(	mappedBy="brand",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<BrandAttribute> brandAttributes;
	
	@Transient
	private BrandAttribute brandAttribute;
	
	public Long getId() {
		return this.brandId;
	}
	
	public void setId(Long id) {
		this.brandId = id;
	}

	public String getCode() {
		return brandCode;
	}

	public void setCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public List<BrandAttribute> getAttributes() {
		return brandAttributes;
	}
	
	public void setAttributes(List<BrandAttribute> brandAttributes) {
		this.brandAttributes = brandAttributes;
	}
	
	public BrandAttribute getBrandAttribute() {
		return brandAttribute;
	}

	public void setBrandAttribute(BrandAttribute brandAttribute) {
		this.brandAttribute = brandAttribute;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

}
