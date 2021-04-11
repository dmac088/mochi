package io.nzbee.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.Tag;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {
			    @JsonSubTypes.Type(value = ProductCategory.class, 	name = "productcategory"),
			    @JsonSubTypes.Type(value = BrandCategory.class, 	name = "brandcategory"),
			    @JsonSubTypes.Type(value = Tag.class, 				name = "tag"),
			    @JsonSubTypes.Type(value = Product.class, 			name = "product")})
public interface ISearchDimension {

	String getCode();
	
	String getDesc();
	
	String getLocale();
	
	Long getCount();
	
	boolean isHierarchical();

}
