package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.dto.IDto;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {
	
	public Page<Product> findAll(	String locale, 
									String currency, 
									String priceType, 
									Pageable pageable, 
									String categoryDesc,
									List<IDto> ldto);

	public Page<Product> findAll(	String locale, 
									String currency, 
									Double priceStart, 
									Double priceEnd, 
									String priceType,
									Pageable pageable, 
									String categoryDesc, 
									List<IDto> ldto);

}
