package io.nzbee.domain.adapters;

import java.util.Set;

import io.nzbee.domain.brand.Brand;

public interface IBrandPortService  extends IPortService<Brand> {

	Set<Brand> findAll(String locale, String currency, String category);

}
