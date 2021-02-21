package io.nzbee.entity.product.shipping;

import java.util.Map;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO;
import io.nzbee.entity.product.shipping.type.ShippingTypeDTO;

public class ShippingProductDTO extends ProductDTO {

	private static final long serialVersionUID = 2891040207798692399L;

    
    public static final String WEIGHT_LIMIT_ALIAS = "shp_wgt_lim";
    
    public static final String WEIGHT_FROM_ALIAS = "shp_wgt_frm";
    
    public static final String WEIGHT_TO_ALIAS = "shp_wgt_to";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
    private String weightLimit;
    
    private String weightFrom;
    
    private String weightTo;
    
    private ShippingDestinationDTO shippingDestination;
    
    private ShippingTypeDTO shippingtype;
    
    private String locale;
    
    

	public ShippingProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		
		this.weightLimit 					= tuple[aliasToIndexMap.get(WEIGHT_LIMIT_ALIAS)].toString();
		this.weightFrom 					= tuple[aliasToIndexMap.get(WEIGHT_FROM_ALIAS)].toString();
		this.weightTo 						= tuple[aliasToIndexMap.get(WEIGHT_TO_ALIAS)].toString();
		this.locale							= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		
		this.shippingDestination = new ShippingDestinationDTO(tuple, aliasToIndexMap);
		this.shippingtype = new ShippingTypeDTO(tuple, aliasToIndexMap);
		
	}


	public String getWeightLimit() {
		return weightLimit;
	}

	public String getWeightFrom() {
		return weightFrom;
	}

	public String getWeightTo() {
		return weightTo;
	}

	public ShippingDestinationDTO getShippingDestination() {
		return shippingDestination;
	}

	public ShippingTypeDTO getShippingtype() {
		return shippingtype;
	}

	public String getLocale() {
		return locale;
	}
	
}
