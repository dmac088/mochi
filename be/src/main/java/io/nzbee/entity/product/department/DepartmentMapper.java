package io.nzbee.entity.product.department;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements IDepartmentMapper {

	@Override
	public Optional<io.nzbee.domain.department.Department> entityToDo(Optional<Department> e, String locale,
			String currency) {
		if(!e.isPresent()) { return Optional.ofNullable(null); }
		Department de = e.get();
		io.nzbee.domain.department.Department dO = 
				new io.nzbee.domain.department.Department(
						de.getDepartmentCode(),
						de.getAttribute(),
						locale, 
						currency
				);
	
		return Optional.ofNullable(dO);
	}

}
