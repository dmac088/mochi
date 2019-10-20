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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import io.nzbee.entity.brand.Brand;

@Entity
@Table(name = "brand_attr_lcl", schema = "mochi")
@PrimaryKeyJoinColumn(name = "bnd_lcl_id")
public class BrandAttribute {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bnd_lcl_id")
	private Long Id;

	@Column(name="bnd_id")
	private Long brandId;

	@Column(name="bnd_desc")
	@Field(analyze = Analyze.YES, store=Store.YES)
	private String brandDesc;
	
	@Column(name="lcl_cd")	
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bnd_id", insertable=false, updatable=false)
	private Brand brand;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Brand getBrand() {
		return brand;
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
        hcb.append(brand.getCode());
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
	      eb.append(brand.getCode(), that.brand.getCode());
	      eb.append(lclCd, that.lclCd);
	      return eb.isEquals();
	}
}
