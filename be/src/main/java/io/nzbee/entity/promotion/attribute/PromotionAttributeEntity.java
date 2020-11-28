package io.nzbee.entity.promotion.attribute;

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
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_attr_lcl", schema = "mochi")
public class PromotionAttributeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_lcl_id")
	private Long Id;

	@Column(name="prm_desc")
	private String promotionDesc;
	
	@Column(name="lcl_cd")
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_id")
	private PromotionEntity promotion;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public Long getPromotionId() {
		return this.getPromotion().getPromotionId();
	}
	
	public PromotionEntity getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionEntity promotion) {
		this.promotion = promotion;
	}
	
	public String getPromotionDesc() {
		return promotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	public String getLocale() {
		return lclCd;
	}

	public void setLocale(String lclCd) {
		this.lclCd = lclCd;
	}

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getLocale());
        return hcb.toHashCode();
    }
 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
        }
	    if (!(obj instanceof PromotionAttributeEntity)) {
	            return false;
	    }
	    PromotionAttributeEntity that = (PromotionAttributeEntity) obj;
	      EqualsBuilder eb = new EqualsBuilder();
	      eb.append(this.getLocale(), that.getLocale());
	      return eb.isEquals();
	}
}
