package io.nzbee.entity.product.shipping.type;

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
import org.hibernate.annotations.Immutable;
import io.nzbee.entity.product.shipping.type.attribute.ShippingTypeAttributeEntity;

@Entity
@Immutable
@Table(name="prostage_type")
public class ShippingTypeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_typ_id")
	private Long shippingTypeId;
	
	@OneToMany(	mappedBy="shippingType",  
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ShippingTypeAttributeEntity> attributes = new ArrayList<ShippingTypeAttributeEntity>();
	
	@Column(name="pst_typ_cd")
	private String shippingTypeCode;

	public Long getShippingTypeId() {
		return shippingTypeId;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}
	
}
