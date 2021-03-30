package io.nzbee.entity.promotion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;

@Entity
@Table(name = "promotion", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="prm_typ_id")
public abstract class PromotionEntity implements Serializable {

	private static final long serialVersionUID = -8035431077837286610L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_id")
	private Long promotionId;
	
	@NaturalId
	@Column(name="prm_cd", unique = true, updatable = false)
	private String promotionCode;
	
	@Column(name="prm_st_dt")
	private LocalDateTime promotionStartDate;
	
	@Column(name="prm_en_dt")
	private LocalDateTime promotionEndDate;
	
	@Column(name="prm_act")
	private Boolean promotionActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_mec_id")
	private PromotionMechanicEntity promotionMechanic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_lvl_id")
	private PromotionLevelEntity promotionLevel;
	
	@OneToMany(	mappedBy="promotion",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<PromotionAttributeEntity> attributes = new HashSet<PromotionAttributeEntity>();
	

	@Transient
	private String locale;
	
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public LocalDateTime getPromotionStartDate() {
		return promotionStartDate;
	}

	public void setPromotionStartDate(LocalDateTime promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}

	public LocalDateTime getPromotionEndDate() {
		return promotionEndDate;
	}

	public void setPromotionEndDate(LocalDateTime promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}

	public Boolean getPromotionActive() {
		return promotionActive;
	}

	public void setPromotionActive(Boolean promotionActive) {
		this.promotionActive = promotionActive;
	}

	public PromotionMechanicEntity getPromotionMechanic() {
		return promotionMechanic;
	}

	public void setPromotionMechanic(PromotionMechanicEntity promotionMechanic) {
		this.promotionMechanic = promotionMechanic;
	}

	public Set<PromotionAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PromotionAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(PromotionAttributeEntity promotionAttribute) {
		this.getAttributes().add(promotionAttribute);
		promotionAttribute.setPromotion(this);		
	}
	
	public void removeAttribute(PromotionAttributeEntity promotionAttribute) {
		this.getAttributes().remove(promotionAttribute);
		promotionAttribute.setPromotion(null);
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public PromotionLevelEntity getPromotionLevel() {
		return promotionLevel;
	}

	public void setPromotionLevel(PromotionLevelEntity promotionLevel) {
		this.promotionLevel = promotionLevel;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromotionEntity)) return false;
        return promotionCode != null && promotionCode.equals(((PromotionEntity) o).getPromotionCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getPromotionCode());
    }
}
