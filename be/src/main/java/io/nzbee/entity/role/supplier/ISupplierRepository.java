package io.nzbee.entity.role.supplier;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ISupplierRepository extends CrudRepository<Supplier, Long> {

	List<Supplier> findAll();

	Optional<Supplier> findBySupplierNumber(String supplierNumber);

}
