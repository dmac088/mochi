package io.nzbee.entity.role.supplier;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.role.IRoleBaseRepository;

public interface ISupplierRepository extends IRoleBaseRepository<Supplier> {

	List<Supplier> findAll();

	Optional<Supplier> findBySupplierNumber(String supplierNumber);

}
