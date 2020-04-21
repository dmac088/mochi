package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.brand.IBrandService;

@Component
public class PostgresBrandAdapter implements IBrandPortService {

	
	@Autowired 
	private IBrandService brandService;
	
	@Override
	public Optional<Brand> findByCode(String code) {
		return null;
	}

	@Override
	public Set<Brand> findAll(String locale, String currency) {
		return brandService.findAll(locale, currency)
				.stream().map(b -> this.entityToDo(Optional.ofNullable(b)).get()).collect(Collectors.toSet());
	}

	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		return entityToDo(Optional.ofNullable(brandService.findByCode(locale, currency, code)).get());
				
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		return entityToDo(brandService.findByDesc(locale, currency, desc));
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes) {
		return brandService.findAll(locale, currency, categoryCodes, tagCodes)
				.stream().map(b -> this.entityToDo(Optional.ofNullable(b)).get()).collect(Collectors.toSet());
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, String category) {
		return brandService.findAll(locale, currency, category)
				.stream().map(b -> this.entityToDo(Optional.ofNullable(b)).get()).collect(Collectors.toSet());
	}
	
	@Override
	public Set<Brand> findAll(String locale, String currency, Set<String> codes) {
		return brandService.findAll(locale, currency, codes)
				.stream().map(b -> (Brand) this.entityToDo(Optional.ofNullable(b)).get()).collect(Collectors.toSet());
	}
	
	@Override
	public void save(Brand domainObject) {
		io.nzbee.entity.brand.Brand brand = new io.nzbee.entity.brand.Brand();
		io.nzbee.entity.brand.attribute.BrandAttribute brandAttribute = new io.nzbee.entity.brand.attribute.BrandAttribute();
	
		brand.setBrandCode(domainObject.getBrandCode());
		brand.setLocale(domainObject.getLocale());
		brand.setCurrency(domainObject.getCurrency());
		
		brandAttribute.setBrandDesc(domainObject.getBrandDesc());
		brandAttribute.setLclCd(domainObject.getLocale());
		brand.addAttribute(brandAttribute);
		brandAttribute.setBrand(brand);
		
		brandService.save(brand);		
	}
	
	private Optional<Brand> entityToDo(Optional<io.nzbee.entity.brand.Brand> e) {
	
		return	Optional.ofNullable(
				e.isPresent()
				?	new Brand(
					 e.get().getBrandCode(),
					 e.get().getBrandAttribute().getBrandDesc(),
					 0,
					 e.get().getLocale(), 
					 e.get().getCurrency()
					)
				: null);
	}

	

}
