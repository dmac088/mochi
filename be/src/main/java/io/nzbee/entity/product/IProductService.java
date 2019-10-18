package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.dto.IDto;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {
	
	public Page<Product> findAll(String locale, String currency, String priceType, int page, int size, String categoryDesc,
			List<IDto> ldto);

	public Page<Product> findAll(String locale, String currency, Double priceStart, Double priceEnd, String priceType,
			int page, int size, String categoryDesc, List<IDto> ldto);

}
