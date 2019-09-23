package io.nzbee.entity.layout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "layout", schema = "mochi")
@PrimaryKeyJoinColumn(name = "lay_id")
public class Layout {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lay_id")
	private Long layoutId;
	
	@Column(name="lay_cd")
	private String code;
	
	@Column(name="lay_desc")
	private String desc;
	
	public String getCode() {
		return code;
	}

	public void setLayoutCode(String layoutCode) {
		this.code = layoutCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String layoutDesc) {
		this.desc = layoutDesc;
	}
	
}
