package io.nzbee.entity.promotion.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_product", schema = "mochi")
@DiscriminatorValue("1")
public class PromotionProductEntity extends PromotionEntity {

	private static final long serialVersionUID = -3935115006992696518L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "category_promotion", schema = "mochi", joinColumns = @JoinColumn(name = "prm_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "product_promotion", schema = "mochi", joinColumns = @JoinColumn(name = "prm_id"), inverseJoinColumns = @JoinColumn(name = "prd_id"))
	private Set<ProductEntity> products = new HashSet<ProductEntity>();

	public Set<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryEntity> categories) {
		this.categories = categories;
	}

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}

	public void addProduct(ProductEntity product) {
		this.products.add(product);
		product.getPromotions().add(this);
	}

	public void removeProduct(ProductEntity product) {
		this.products.remove(product);
		product.removePromotion(this);
	}

	public void addCategory(CategoryEntity category) {
		this.getCategories().add(category);
		category.getPromotions().add(this);
	}

	public void removeCategory(CategoryEntity category) {
		this.getCategories().remove(category);
		category.removePromotion(this);
	}
}
