package io.nzbee.entity.role.supplier;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface ISupplierService extends IService<Supplier> {

	Optional<Supplier> findByCode(String code);

	Optional<Supplier> findById(Long id);

}
