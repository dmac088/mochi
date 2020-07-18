package io.nzbee.domain.product;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {


	Page<Product> findAll(String locale, String currency, String categoryCode,
						  Set<String> categoryCodes,
						  Set<String> brandCodes,
						  Set<String> tagCodes,
						  Double maxPrice,
						  String page, String size, String sort);


}
