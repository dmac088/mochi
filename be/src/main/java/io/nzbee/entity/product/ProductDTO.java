package io.nzbee.entity.product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;
import io.nzbee.entity.promotion.PromotionDTO;

public class ProductDTO {
	
	public static final String ID_ALIAS = "prd_id";
	
	public static final String UPC_ALIAS = "upc_cd";
    
    public static final String SHORT_DESC_ALIAS = "prd_desc";
    
    public static final String LONG_DESC_ALIAS = "prd_lng_desc";
    
    public static final String IN_STOCK_ALIAS = "prd_in_stock";
    
    public static final String IMAGE_ALIAS = "prd_img_pth";
    
    public static final String CODE_ALIAS = "prd_sts_cd";

    public static final String RETAIL_PRICE_ALIAS = "retail_price";
    
    public static final String MARKDOWN_PRICE_ALIAS = "markdown_price";
    
    public static final String CURRENCY_ALIAS = "ccy_cd";
    
    public static final String HEIGHT_ALIAS = "height";
    
    public static final String WIDTH_ALIAS = "width";
    
    public static final String LENGTH_ALIAS = "length";
    
    public static final String WEIGHT_ALIAS = "weight";
    
    protected Long productId;

	protected String productUPC;

	protected String productDesc;
	
	protected String productLongDesc;
	
	protected LocalDateTime productCreateDt;
	
	
	//brand object
	protected BrandDTO brand;

	//department
	protected DepartmentDTO department;
	
	//these fields will contain all the categories related to the product 1->N relationship
	protected Set<CategoryProductDTO> categories = new HashSet<CategoryProductDTO>();
	
	protected Set<PromotionDTO> promotions = new HashSet<PromotionDTO>();
	
	//pricing objects
	protected Double retailPrice;
	
	protected Double markdownPrice;
	
	//physical dimensions
	protected Integer height;
	
	protected Integer width;
	
	protected Integer length;
	
	protected Integer weight;
	
	
	//localization
	protected String locale;
	
	protected String currency;
	
	
	//stock and status
	protected String productStatus;
	
	protected boolean inStock;

	protected String productImage;

	protected String productStatusCode;
	

	public ProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productId 				= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
        this.productUPC 			= tuple[aliasToIndexMap.get(UPC_ALIAS)].toString();
        this.productDesc 			= tuple[aliasToIndexMap.get(SHORT_DESC_ALIAS)].toString();
        this.productLongDesc 		= !aliasToIndexMap.containsKey(LONG_DESC_ALIAS)
        								|| ((tuple[aliasToIndexMap.get(LONG_DESC_ALIAS)] == null))
        							  ? ""
        							  : tuple[aliasToIndexMap.get(LONG_DESC_ALIAS)].toString();
        this.inStock 				= ((Boolean) tuple[aliasToIndexMap.get(IN_STOCK_ALIAS)]);
        this.productImage 			= !aliasToIndexMap.containsKey(LONG_DESC_ALIAS)
				|| ((tuple[aliasToIndexMap.get(IMAGE_ALIAS)] == null))
			  ? ""
			  : tuple[aliasToIndexMap.get(IMAGE_ALIAS)].toString();
		this.productStatusCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.currency				= tuple[aliasToIndexMap.get(CURRENCY_ALIAS)].toString();
		this.retailPrice			= ((BigDecimal) tuple[aliasToIndexMap.get(RETAIL_PRICE_ALIAS)]).doubleValue();
		this.markdownPrice			= ((BigDecimal) tuple[aliasToIndexMap.get(MARKDOWN_PRICE_ALIAS)]).doubleValue();
		
		this.height					= ((BigInteger) tuple[aliasToIndexMap.get(HEIGHT_ALIAS)]).intValue();
		this.width					= ((BigInteger) tuple[aliasToIndexMap.get(WIDTH_ALIAS)]).intValue();
		this.length					= ((BigInteger) tuple[aliasToIndexMap.get(LENGTH_ALIAS)]).intValue();
		this.weight					= ((BigInteger) tuple[aliasToIndexMap.get(WEIGHT_ALIAS)]).intValue();
		

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

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public String getProductImage() {
		return productImage;
	}

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public Set<CategoryProductDTO> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryProductDTO> categories) {
		this.categories = categories;
	}
	
	public Set<PromotionDTO> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionDTO> promotions) {
		this.promotions = promotions;
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

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}