package io.nzbee.entity.product.physical;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

import io.nzbee.Constants;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTO;
import io.nzbee.entity.stock.StockOnHandEntity;

@Indexed
@Entity
@Table(name = "product_basic", schema = "mochi")
@DiscriminatorValue("2")
@NamedNativeQuery(
	    name = "QueryForListOfProducts",
	    query =
	        "WITH recursive descendants AS " + 
	        "( " + 
	        "       SELECT t.cat_id, " + 
	        "              t.cat_cd, " + 
	        "              t.cat_lvl, " + 
	        "              t.cat_prnt_id, " + 
	        "              t.cat_prnt_cd, " + 
	        "              t.cat_typ_id, " + 
	        "              cast('/' " + 
	        "                     || cast(t.cat_id AS text) " + 
	        "                     || '/' AS text) node " + 
	        "       FROM   mochi.category         AS t " + 
	        "       WHERE  0=0 " + 
	        "       AND    t.cat_cd = :categoryCode " + 
	        "       UNION ALL " + 
	        "       SELECT t.cat_id, " + 
	        "              t.cat_cd, " + 
	        "              t.cat_lvl, " + 
	        "              t.cat_prnt_id, " + 
	        "              t.cat_prnt_cd, " + 
	        "              t.cat_typ_id, " + 
	        "              cast(d.node " + 
	        "                     || cast(t.cat_id AS text) " + 
	        "                     || '/' AS text) node " + 
	        "       FROM   mochi.category         AS t " + 
	        "       JOIN   descendants            AS d " + 
	        "       ON     t.cat_prnt_id = d.cat_id ) " + 
	        " " + 
	        "SELECT physicalpr0_1_.upc_cd                 , " + 
	        "       attributes1_.prd_desc                 , " + 
	        "       attributes3_.bnd_desc                 , " + 
	        "       Max(CASE " + 
	        "             WHEN productpri7_.prc_typ_cd = 'RET01' THEN prices6_.prc_val " + 
	        "             ELSE 0 " + 
	        "           END)                              				AS retail_price, " + 
	        "       Max(CASE " + 
	        "             WHEN productpri7_.prc_typ_cd = 'MKD01' THEN prices6_.prc_val " + 
	        "             ELSE 0 " + 
	        "           END)                              				AS markdown_price, " + 
	        "       COALESCE(stockonhan5_.soh_qty, 0) > 0 				AS hasStock, " + 
	        "       attributes1_.prd_img_pth " + 
	        "FROM   mochi.product_basic physicalpr0_ " + 
	        "	   INNER JOIN mochi.product_category pc  " + 
	        "	   		   ON physicalpr0_.prd_id = pc.prd_id " + 
	        "	   INNER JOIN descendants d " + 
	        "	   			ON pc.cat_id = d.cat_id " + 
	        "       INNER JOIN mochi.product physicalpr0_1_ " + 
	        "               ON physicalpr0_.prd_id = physicalpr0_1_.prd_id " + 
	        "       INNER JOIN mochi.product_attr_lcl attributes1_ " + 
	        "               ON physicalpr0_.prd_id = attributes1_.prd_id " + 
	        "       INNER JOIN mochi.brand brandentit2_ " + 
	        "               ON physicalpr0_1_.bnd_id = brandentit2_.bnd_id " + 
	        "       INNER JOIN mochi.brand_attr_lcl attributes3_ " + 
	        "               ON brandentit2_.bnd_id = attributes3_.bnd_id " + 
	        "       INNER JOIN mochi.product_status productsta4_ " + 
	        "               ON physicalpr0_1_.prd_sts_id = productsta4_.prd_sts_id " + 
	        "       LEFT OUTER JOIN mochi.stock_on_hand stockonhan5_ " + 
	        "                    ON physicalpr0_.prd_id = stockonhan5_.soh_prd_id " + 
	        "       INNER JOIN mochi.price prices6_ " + 
	        "               ON physicalpr0_.prd_id = prices6_.prd_id " + 
	        "       INNER JOIN mochi.price_type productpri7_ " + 
	        "               ON prices6_.prc_typ_id = productpri7_.prc_typ_id " + 
	        "       INNER JOIN mochi.currency currency8_ " + 
	        "               ON prices6_.ccy_id = currency8_.ccy_id " + 
	        "WHERE  attributes1_.lcl_cd = :locale " + 
	        "       AND attributes3_.lcl_cd = :locale " + 
	        "       AND currency8_.ccy_cd = :currency " + 
	        "       AND ( physicalpr0_1_.upc_cd IN ( :productCodes ) ) " + 
	        "       AND productsta4_.prd_sts_cd = '" + Constants.activeSKUCode + "'" +
	        "GROUP  BY physicalpr0_1_.upc_cd, " + 
	        "          attributes1_.prd_desc, " + 
	        "          attributes3_.bnd_desc, " + 
	        "          attributes1_.prd_img_pth, " + 
	        "          stockonhan5_.soh_qty ",
	    resultSetMapping = "MappingForPhysicalProductLightDTO"
)
	@SqlResultSetMapping(
	    name = "MappingForPhysicalProductLightDTO",
	    classes = @ConstructorResult(
	        targetClass = PhysicalProductLightDTO.class,
	        columns = {
	            @ColumnResult(name = "upc_cd", 			type = String.class),
	            @ColumnResult(name = "prd_desc", 		type = String.class),
	            @ColumnResult(name = "bnd_desc", 		type = String.class),
	            @ColumnResult(name = "retail_price", 	type = Double.class),
	            @ColumnResult(name = "markdown_price", 	type = Double.class),
	            @ColumnResult(name = "hasStock", 		type = Boolean.class),
	            @ColumnResult(name = "prd_img_pth", 	type = String.class),
	        }
	    )
	)
public class PhysicalProductEntity extends ProductEntity {

	private static final long serialVersionUID = -2844690299028235684L;

	@Column(name="width")
	private Integer widthDimension;
	
	@Column(name="length")
	private Integer lengthDimension;
	
	@Column(name="height")
	private Integer heightDimension;
	
	@Column(name="weight")
	private Double weightDimension;
	
	@OneToOne(	mappedBy="product",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private StockOnHandEntity stockOnHand;


	public Integer getWidthDimension() {
		return widthDimension;
	}

	public void setWidthDimension(Integer widthDimension) {
		this.widthDimension = widthDimension;
	}

	public Integer getLengthDimension() {
		return lengthDimension;
	}

	public void setLengthDimension(Integer lengthDimension) {
		this.lengthDimension = lengthDimension;
	}

	public Integer getHeightDimension() {
		return heightDimension;
	}

	public void setHeightDimension(Integer heightDimension) {
		this.heightDimension = heightDimension;
	}

	public Double getWeightDimension() {
		return weightDimension;
	}

	public void setWeightDimension(Double weightDimension) {
		this.weightDimension = weightDimension;
	}
	
}
