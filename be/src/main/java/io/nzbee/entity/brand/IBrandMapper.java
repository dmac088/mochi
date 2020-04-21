package io.nzbee.entity.brand;

import java.util.Optional;
import io.nzbee.entity.IMapper;

public interface IBrandMapper extends IMapper<Brand, io.nzbee.entity.brand.Brand> {

	Optional<io.nzbee.domain.brand.Brand> entityToDo(Optional<io.nzbee.entity.brand.Brand> e, String locale, String currency);

}
