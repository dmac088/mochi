package io.nzbee.entity.role.supplier;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.role.IRoleBaseRepository;

public interface ISupplierRepository extends IRoleBaseRepository<Supplier> {

	Set<Supplier> findAll();

	Optional<Supplier> findBySupplierNumber(String supplierNumber);

}
