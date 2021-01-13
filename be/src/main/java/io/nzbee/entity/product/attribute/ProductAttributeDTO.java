package io.nzbee.entity.product.attribute;

import java.io.Serializable;
import java.util.Map;

public class ProductAttributeDTO implements Serializable {
	
	private static final long serialVersionUID = 6969987709682578464L;

	public static final String ID_ALIAS 			= "prd_lcl_id";
	
    public static final String SHORT_DESC_ALIAS 	= "prd_desc";
    
    public static final String LONG_DESC_ALIAS 		= "prd_lng_desc";
    
    public static final String IMAGE_ALIAS 			= "prd_img_pth";
    
    public static final String LOCALE_CODE_ALIAS 	= "lcl_cd";
    
	private Long productAttributeId;
	
	private String productShortDesc;
	
	private String productLongDesc;
	
	private String productImage;
	
	private String locale;

	public ProductAttributeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productAttributeId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.productShortDesc 		= tuple[aliasToIndexMap.get(SHORT_DESC_ALIAS)].toString();
		this.productLongDesc 		= tuple[aliasToIndexMap.get(LONG_DESC_ALIAS)].toString();
		this.productImage			= tuple[aliasToIndexMap.get(IMAGE_ALIAS)].toString();
	}

	public Long getProductAttributeId() {
		return productAttributeId;
	}

	public String getProductShortDesc() {
		return productShortDesc;
	}
	
	public String getProductLongDesc() {
		return productLongDesc;
	}
	
	public String getProductImage() {
		return productImage;
	}

	public String getLocale() {
		return locale;
	}
	
	
}
