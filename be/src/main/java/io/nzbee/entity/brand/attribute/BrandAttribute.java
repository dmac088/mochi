package io.nzbee.entity.brand.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.entity.brand.Brand;

@Entity
@Table(name = "brand_attr_lcl", schema = "mochi")
public class BrandAttribute {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_lcl_id")
	private Long Id;

	@Column(name="bnd_desc")
	@Field(analyze = Analyze.YES, store=Store.YES)
	private String brandDesc;
	
	@Column(name="lcl_cd")	
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="bnd_id")
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
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getBrand().getBrandCode());
        hcb.append(this.getLclCd());
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
	      eb.append(this.getBrand().getBrandCode(), that.getBrand().getBrandCode());
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
}
