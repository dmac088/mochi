package io.nzbee.entity.ports;

import java.util.Set;

import io.nzbee.domain.brand.Brand;

public interface IBrandPortService  extends IPortService<Brand> {

	Set<Brand> findAll(String locale, String currency, String category);

}
