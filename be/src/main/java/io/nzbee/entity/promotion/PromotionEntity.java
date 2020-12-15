package io.nzbee.entity.promotion;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;

@Entity
@Table(name = "promotion", schema = "mochi")
public class PromotionEntity {

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
	
	@OneToMany(	mappedBy="promotion",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<PromotionAttributeEntity> attributes = new HashSet<PromotionAttributeEntity>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {
						CascadeType.PERSIST,
			            CascadeType.MERGE
			    })
	@JoinTable(		name 				= "category_promotion", 
					schema				= "mochi", 
		   			joinColumns 		= @JoinColumn(name = "prm_id"), 
		   			inverseJoinColumns 	= @JoinColumn(name = "cat_id"))
	private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.PERSIST,
		            CascadeType.MERGE
		    })
	@JoinTable(		name 				= "product_promotion", 
					schema				= "mochi", 
		   			joinColumns 		= @JoinColumn(name = "prm_id"), 
		   			inverseJoinColumns 	= @JoinColumn(name = "prd_id"))
	private Set<ProductEntity> products = new HashSet<ProductEntity>();

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

	public Set<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryEntity> categories) {
		this.categories = categories;
	}
	
	public void addCategory(CategoryEntity category) {
		this.getCategories().add(category);
		category.getPromotions().add(this);
	}
	
	public void removeCategory(CategoryEntity category) {
		this.getCategories().remove(category);
		category.removePromotion(this);
	}
	
	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}
	
	public void addProduct(ProductEntity product) {
		this.getProducts().add(product);
		product.getPromotions().add(this);
	}
	
	public void removeProduct(ProductEntity product) {
		this.getProducts().remove(product);
		product.removePromotion(this);
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
