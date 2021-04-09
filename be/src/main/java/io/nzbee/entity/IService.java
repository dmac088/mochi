package io.nzbee.entity;

import java.util.Optional;

import io.nzbee.entity.role.supplier.Supplier;

public interface IService<T> {

	 void save(T t);
	
	 void update(T t);
	
	 void delete(T t);


}
