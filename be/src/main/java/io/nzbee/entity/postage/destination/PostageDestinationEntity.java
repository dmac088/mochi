package io.nzbee.entity.postage.destination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.nzbee.entity.postage.zone.PostageZoneEntity;

@Entity
@Immutable
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pty_zne_id")
	private PostageZoneEntity postageZone;

	public Long getPostageDestinationId() {
		return postageDestinationId;
	}

	public String getPostageDestinationCode() {
		return postageDestinationCode;
	}

	public String getPostageDestinationDesc() {
		return postageDestinationDesc;
	}

	public PostageZoneEntity getPostageZone() {
		return postageZone;
	}
}
