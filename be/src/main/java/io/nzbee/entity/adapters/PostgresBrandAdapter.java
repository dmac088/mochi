package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.brand.IBrandService;

public class PostgresBrandAdapter implements IBrandPortService {

	
	@Autowired 
	private IBrandService brandService;
	
	@Override
	public Optional<Brand> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Brand domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Brand> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return brandService.findAll(locale, currency)
				.stream().map(b -> this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	public Brand findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return entityToDo(brandService.findByCode(locale, currency, code).get());
				
	}

	@Override
	public Brand findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return entityToDo(brandService.findByDesc(locale, currency, desc).get());
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes) {
		// TODO Auto-generated method stub
		return brandService.findAll(locale, currency, categoryCodes, tagCodes)
				.stream().map(b -> this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, String category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Brand entityToDo(io.nzbee.entity.brand.Brand e) {
		return new Brand(
				 e.getBrandCode(),
				 e.getAttributes().stream().filter(a -> a.getLclCd().equals(e.getLocale())).findFirst().get().getBrandDesc(),
				 0,
				 e.getLocale(), 
				 e.getCurrency()
				);
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
