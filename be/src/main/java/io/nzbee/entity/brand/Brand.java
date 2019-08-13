package io.nzbee.entity.brand;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.Product;


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
	
	@ManyToMany(mappedBy = "brands")
	@JsonIgnore
	private List<Category> categories;

	@OneToMany(mappedBy="brand",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	private List<BrandAttribute> brandAttributes;
	
	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = "en-GB"))
	public String getBrandDescENGB() {
		List<BrandAttribute> lba = this.getAttributes().stream().filter(ca -> {
 			return ca.getLclCd().equals("en-GB");
 		}).collect(Collectors.toList());

		if (lba.isEmpty()) { return null; }
		return lba.get(0).getBrandDesc();
	}
	
	
	@Field(analyze = Analyze.YES, analyzer = @Analyzer(definition = "zh-HK"))
	public String getBrandDescZHHK() {
		List<BrandAttribute> lba = this.getAttributes().stream().filter(ca -> {
		 			return ca.getLclCd().equals("zh-HK");
		 		}).collect(Collectors.toList());
		
		if (lba.isEmpty()) { return null; }
		return lba.get(0).getBrandDesc();
	}
	
	public Long getId() {
		return this.brandId;
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

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

}
