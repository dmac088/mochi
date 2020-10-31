package io.nzbee.entity.product.department;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface IDepartmentDao extends ILocalizedDao<DepartmentDTO, DepartmentEntity> {

	Optional<DepartmentDTO> findByProductCode(String locale, String productCode);

}
