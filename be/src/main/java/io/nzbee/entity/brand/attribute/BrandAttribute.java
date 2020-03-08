package io.nzbee.entity.brand.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDiscriminator;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.nzbee.entity.LanguageDiscriminator;
import io.nzbee.entity.brand.Brand;

@Entity
@Table(name = "brand_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "bnd_lcl_id")
public class BrandAttribute {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_lcl_id")
	private Long Id;

	@Column(name="bnd_desc")
	@Field(analyze = Analyze.YES, store=Store.YES)
	private String brandDesc;
	
	@Field
	@Column(name="lcl_cd")	
	@AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bnd_id", insertable=false, updatable=false)
	@JsonBackReference
	private Brand brand;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getBrandDesc() {
		return brandDesc;
	}
	
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	@Field(analyze = Analyze.NO, store=Store.YES)
	@Facet
	public String getBrandToken() {
		return this.getBrand().getBrandCode();
	}

	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(brand.getBrandCode());
        hcb.append(lclCd);
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof BrandAttribute)) {
	            return false;
	    }
	    BrandAttribute that = (BrandAttribute) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(brand.getBrandCode(), that.brand.getBrandCode());
	      eb.append(lclCd, that.lclCd);
	      return eb.isEquals();
	}
}
