package io.nzbee.entity.product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;

public class ProductDTO {
	
	public static final String ID_ALIAS = "prd_id";
	
	public static final String UPC_ALIAS = "upc_cd";
    
    public static final String DESC_ALIAS = "prd_desc";
    
    protected Long productId;

	protected String productUPC;

	protected String productDesc;
	
	protected LocalDateTime productCreateDt;
	
	//primary category constructor
	protected CategoryProductDTO primaryCategory;
	
	//brand object
	protected BrandDTO brand;

	//department
	protected DepartmentDTO department;
	
	//these fields will contain all the categories related to the product 1->N relationship
	protected Set<CategoryProductDTO> categories;
	
	//pricing objects
	protected Double retailPrice;
	
	protected Double markdownPrice;
	
	
	//localization
	protected String locale;
	
	protected String currency;
	
	
	//stock and status
	protected String productStatus;
	
	protected boolean inStock;
	

	public ProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
        this.productUPC = tuple[aliasToIndexMap.get(UPC_ALIAS)].toString();
        this.productDesc = tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
    //    this.productCreateDt = tuple[aliasToIndexMap.get(CREATED_DATE_ALIAS)].;
        
	}
	

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public LocalDateTime getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(LocalDateTime productCreateDt) {
		this.productCreateDt = productCreateDt;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public CategoryProductDTO getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategoryCode(CategoryProductDTO primaryCategory) {
		this.primaryCategory = primaryCategory;
	}

	public Set<CategoryProductDTO> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryProductDTO> categories) {
		this.categories = categories;
	}

	public BrandDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(Double markdownPrice) {
		this.markdownPrice = markdownPrice;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

}