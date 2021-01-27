package io.nzbee.entity.postage.size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="prostage_size_limit")
public class PostageSizeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pst_siz_id")
	private Long postageSizeId;
	
	@Column(name="pst_siz_cd")
	private String postageSizeCode;

	public Long getPostageSizeId() {
		return postageSizeId;
	}

	public String getPostageSizeCode() {
		return postageSizeCode;
	}
	
}
