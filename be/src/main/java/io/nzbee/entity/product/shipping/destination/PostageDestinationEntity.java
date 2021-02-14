package io.nzbee.entity.product.shipping.destination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prostage_destination")
public class PostageDestinationEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_dst_id")
	private Long postageDestinationId;
	
	@Column(name="pst_dst_cd")
	private String postageDestinationCode; 
	
	@Column(name="pst_dst_desc")
	private String postageDestinationDesc;
	
	@Column(name="pst_zne_cd")
	private String postageZoneCode;

	public Long getPostageDestinationId() {
		return postageDestinationId;
	}

	public String getPostageDestinationCode() {
		return postageDestinationCode;
	}
	
	public void setPostageDestinationCode(String postageDestinationCode) {
		this.postageDestinationCode = postageDestinationCode;
	}

	public String getPostageDestinationDesc() {
		return postageDestinationDesc;
	}

	public void setPostageDestinationDesc(String postageDestinationDesc) {
		this.postageDestinationDesc = postageDestinationDesc;
	}

	public String getPostageZoneCode() {
		return postageZoneCode;
	}

	public void setPostageZoneCode(String postageZoneCode) {
		this.postageZoneCode = postageZoneCode;
	}

}
