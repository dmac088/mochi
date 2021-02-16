package io.nzbee.entity.product.shipping.destination.attribute;

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
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;

@Entity
@Table(name = "shipping_destination_attr_lcl", schema = "mochi")
public class ShippingDestinationAttributeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shp_dst_lcl_id")
	private Long Id;
	
	@Column(name="shp_dst_desc")
	private String shippingDestinationDesc;

	@Column(name="lcl_cd")
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="shp_dst_id")
	private ShippingDestinationEntity shippingDestination;
	
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

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public void setShippingDestinationDesc(String shippingDestinationDesc) {
		this.shippingDestinationDesc = shippingDestinationDesc;
	}

	public ShippingDestinationEntity getShippingDestination() {
		return shippingDestination;
	}

	public void setShippingDestination(ShippingDestinationEntity shippingDestination) {
		this.shippingDestination = shippingDestination;
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
	    if (!(obj instanceof ShippingDestinationAttributeEntity)) {
	            return false;
	    }
	    ShippingDestinationAttributeEntity that = (ShippingDestinationAttributeEntity) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getLclCd(), that.getLclCd());
	      return eb.isEquals();
	}
	
}
