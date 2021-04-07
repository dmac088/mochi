package io.nzbee.domain.product;

import io.nzbee.domain.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {

	Product findByCode(String locale, String currency, String code);

	Product findByDesc(String locale, String currency, String desc);

}
