package io.nzbee.entity.promotion;

import java.util.Map;

public class PromotionBNGNPCTDTO extends PromotionDTO {

	public static final String ID_ALIAS = "prm_id";
	
	public static final String BUY_QTY_ALIAS = "buy_qty";
	
	public static final String PCT_DISC_ALIAS = "pct_disc";
	
	private Long promotionId;
	
	private int buyQty;
	
	private Double discountPct;
	
	
	public PromotionBNGNPCTDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.promotionId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.buyQty				= ((Number) tuple[aliasToIndexMap.get(BUY_QTY_ALIAS)]).intValue();
		this.discountPct		= ((Number) tuple[aliasToIndexMap.get(PCT_DISC_ALIAS)]).doubleValue();
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public int getBuyQty() {
		return buyQty;
	}

	public Double getDiscountPct() {
		return discountPct;
	}
	
}
