package io.nzbee.entity.product.shipping.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="prostage_type")
public class ShippingTypeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_typ_id")
	private Long postageTypeId;
	
	@Column(name="pst_typ_cd")
	private String postageTypeCode;

	public Long getPostageTypeId() {
		return postageTypeId;
	}

	public String getPostageTypeCode() {
		return postageTypeCode;
	}
	
}
