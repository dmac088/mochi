package io.nzbee.entity.promotion.type;

import java.io.Serializable;
import java.util.Map;


public class PromotionTypeDTO implements Serializable {

	private static final long serialVersionUID = 6521414706235561823L;

	public static final String CODE_ALIAS = "prm_typ_cd";
	
	public static final String DESC_ALIAS = "prm_typ_desc";
	
	private String promotionTypeCode;
	
	private String promotionTypeDesc;
	
	
	public PromotionTypeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionTypeCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionTypeDesc 		= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}


	public PromotionTypeDTO(String promotionTypeCode,
							String promotionTypeDesc) {
		this.promotionTypeCode = promotionTypeCode;
		this.promotionTypeDesc = promotionTypeDesc;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}
	
}
