package io.nzbee.entity.brand;

import io.nzbee.entity.IMapper;

public interface IBrandMapper extends IMapper<io.nzbee.domain.brand.Brand, Brand> {

	io.nzbee.domain.brand.Brand entityToDo(io.nzbee.entity.brand.Brand e, String locale, String currency);

}
