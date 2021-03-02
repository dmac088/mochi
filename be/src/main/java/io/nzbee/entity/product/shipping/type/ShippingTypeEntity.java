package io.nzbee.entity.product.shipping.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import io.nzbee.entity.product.shipping.type.attribute.ShippingTypeAttributeEntity;

@Entity
@Table(name="shipping_type")
public class ShippingTypeEntity implements Serializable {

	private static final long serialVersionUID = 5192811246276819475L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shp_typ_id")
	private Long shippingTypeId;
	
	@OneToMany(	mappedBy="shippingType",  
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ShippingTypeAttributeEntity> attributes = new ArrayList<ShippingTypeAttributeEntity>();
	
	@Column(name="shp_typ_cd")
	private String shippingTypeCode;

	public Long getShippingTypeId() {
		return shippingTypeId;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public void setShippingTypeId(Long shippingTypeId) {
		this.shippingTypeId = shippingTypeId;
	}

	public void setAttributes(List<ShippingTypeAttributeEntity> attributes) {
		this.attributes = attributes;
	}

	public void setShippingTypeCode(String shippingTypeCode) {
		this.shippingTypeCode = shippingTypeCode;
	}

	public List<ShippingTypeAttributeEntity> getAttributes() {
		return attributes;
	}
	
}
