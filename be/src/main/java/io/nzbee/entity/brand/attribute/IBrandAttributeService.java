package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;

public interface IBrandAttributeService {

	Optional<BrandAttribute> getBrandAttributes(Long Id, String locale);

	List<BrandAttribute> getBrandAttributes();

	Optional<BrandAttribute> getBrandAttributesEN(Long Id);

	Optional<BrandAttribute> getBrandAttributesHK(Long Id);

}
