package io.nzbee.entity.product.shipping.type.attribute;

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
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;

@Entity
@Table(name = "shipping_type_attr_lcl", schema = "mochi")
public class ShippingTypeAttributeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shp_lcl_id")
	private Long Id;
	
	@Column(name="shp_desc")
	private String shippingTypeDesc;

	@Column(name="lcl_cd")
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="shp_id")
	private ShippingTypeEntity shippingType;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public void setShippingTypeDesc(String shippingTypeDesc) {
		this.shippingTypeDesc = shippingTypeDesc;
	}

	public ShippingTypeEntity getShippingType() {
		return shippingType;
	}

	public void setShippingType(ShippingTypeEntity shippingType) {
		this.shippingType = shippingType;
	}

	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getLclCd());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof ShippingTypeAttributeEntity)) {
	            return false;
	    }
	    ShippingTypeAttributeEntity that = (ShippingTypeAttributeEntity) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
	
}
