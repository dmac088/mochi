package io.nzbee.entity.postage.zone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="prostage_zone")
public class PostageZone {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_zne_id")
	private Long postageZoneId;
	
	@Column(name="pst_zne_cd")
	private String postageZoneCode;

	public Long getPostageZoneId() {
		return postageZoneId;
	}

	public String getPostageZoneCode() {
		return postageZoneCode;
	}
	
}
