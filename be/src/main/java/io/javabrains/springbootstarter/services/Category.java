package io.javabrains.springbootstarter.services;
import java.util.Set;
import io.javabrains.springbootstarter.domain.Layout;
import java.util.List;
import java.util.Objects;


public class Category {
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private String categoryDescFacet;

	private Long categoryLevel;
	
	private Long categoryPreview;
	
	private Long categoryMenu;
	
	private Long landingDisplay;
	
	private String categoryType;

	private String lclCd;
	
	private List<Layout> layouts;
	
	//@JsonIgnore
	private Long parentId;	

	private Long childCategoryCount;
	
	private Long productCount;
	
	private Long maxMarkDownPrice;

	private Set<Brand> categoryBrands;

	private List<Category> children;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	public String getCategoryDescFacet() {
		return categoryDescFacet;
	}

	public void setCategoryDescFacet(String categoryDescFacet) {
		this.categoryDescFacet = categoryDescFacet;
	}
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	public Long getCategoryPreview() {
		return categoryPreview;
	}

	public void setCategoryPreview(Long categoryPreview) {
		this.categoryPreview = categoryPreview;
	}
	
	public Long getCategoryMenu() {
		return categoryMenu;
	}

	public void setCategoryMenu(Long categoryMenu) {
		this.categoryMenu = categoryMenu;
	}
	
	public Long getLandingDisplay() {
		return landingDisplay;
	}

	public void setLandingDisplay(Long landingDisplay) {
		this.landingDisplay = landingDisplay;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}
	
	public Long getChildCategoryCount() {
		return childCategoryCount;
	}

	public void setChildCategoryCount(Long childCategoryCount) {
		this.childCategoryCount = childCategoryCount;
	}

	public Set<Brand> getCategoryBrands() {
		return categoryBrands;
	}

	public void setCategoryBrands(Set<Brand> set) {
		this.categoryBrands = set;
	}
	
	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}
	
	public Long getMaxMarkDownPrice() {
		return maxMarkDownPrice;
	}

	public void setMaxMarkDownPrice(Long maxMarkDownPrice) {
		this.maxMarkDownPrice = maxMarkDownPrice;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Category pcDto = (Category) o;
	     return this.categoryId == pcDto.categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CategoryDto [categoryId=")
        .append(categoryId)
        .append(", categoryCode=").append(categoryCode)
        .append(", categoryDesc=").append(categoryDesc)
        .append(", categoryPreview=").append(categoryPreview)
        .append(", lclCd=").append(lclCd);
        return builder.toString();
    }
}