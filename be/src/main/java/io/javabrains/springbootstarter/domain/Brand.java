package io.javabrains.springbootstarter.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.search.annotations.IndexedEmbedded;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "brand", schema = "mochi")
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;
	
	@Column(name="bnd_cd")
	private String brandCode;

	@OneToMany(mappedBy="brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Product> products;
	
	@OneToMany(mappedBy="brand",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	private List<BrandAttribute> brandAttributes;
	
	public Long getBrandId() {
		return this.brandId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	
	public List<BrandAttribute> getBrandAttributes() {
		return brandAttributes;
	}
	
	public void setBrandAttributes(List<BrandAttribute> brandAttributes) {
		this.brandAttributes = brandAttributes;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

}
