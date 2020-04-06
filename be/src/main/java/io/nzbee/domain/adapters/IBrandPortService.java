package io.nzbee.domain.adapters;

import java.util.List;
import java.util.Set;

import io.nzbee.domain.brand.Brand;

public interface IBrandPortService  extends IPortService<Brand> {

	Set<Brand> findAll(String locale, String currency, String category);

	Set<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes);

}
