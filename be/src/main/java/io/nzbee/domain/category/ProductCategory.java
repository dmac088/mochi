package io.nzbee.domain.category;

public class ProductCategory extends Category {

	public ProductCategory(Long id, String categoryCode, String categoryDesc, Long categoryLevel, String categoryType,
			String lclCd, String parentCode, Long productCount) {
		super(id, categoryCode, categoryDesc, categoryLevel, categoryType, lclCd, parentCode, productCount);
		// TODO Auto-generated constructor stub
	}

	public ProductCategory() {
		super();
	}

//	private List<Product> products;
//	
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//	
}
