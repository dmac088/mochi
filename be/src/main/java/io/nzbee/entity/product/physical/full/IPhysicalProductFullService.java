package io.nzbee.entity.product.physical.full;

import java.util.Optional;

public interface IPhysicalProductFullService {

	Optional<PhysicalProductFullDTO> findByCode(String locale, String currency, String code);

}
