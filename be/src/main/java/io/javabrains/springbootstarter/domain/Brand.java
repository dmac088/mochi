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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "brand", schema = "mochi")
public class Brand {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_id")
	private Long brandId;
	
	@OneToMany(mappedBy="brand",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JsonIgnore
	private List<BrandAttribute> brandAttribute;

	public List<BrandAttribute> getBrandAttribute() {
		return brandAttribute;
	}

	public void setBrandAttribute(List<BrandAttribute> brandAttribute) {
		this.brandAttribute = brandAttribute;
	}
	


}
