package io.nzbee.entity.adapters;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.exceptions.brand.BrandNotFoundException;

@Component
public class PostgresBrandAdapter implements IBrandPortService {

	@Autowired 
	private IBrandService brandService;

	@Override
	public Set<Brand> findAll(String locale, String currency) {
		return brandService.findAll(locale, currency)
				.stream().map(b -> this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	public Brand findByCode(String locale, String currency, String code) {
		io.nzbee.entity.brand.Brand b = brandService.findByCode(locale, currency, code)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for code " + code));
		return entityToDo(b);
	}
	
	@Override
	public Brand findByProductCode(String locale, String currency, String productCode) {
		io.nzbee.entity.brand.Brand b = brandService.findByProductCode(locale, currency, productCode)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for product code " + productCode));
		return entityToDo(b);
	}

	@Override
	public Brand findByDesc(String locale, String currency, String desc) {
		io.nzbee.entity.brand.Brand b = brandService.findByDesc(locale, currency, desc)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for product desc " + desc));
		return entityToDo(b);
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice) {
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice)
				.stream().map(b -> (Brand) this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	public Set<Brand> findAll(String locale, String currency, String category) {
		return brandService.findAll(locale, currency, category)
				.stream().map(b -> (Brand) this.entityToDo(b)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<Brand> findAll(String locale, String currency, Set<String> codes) {
		return brandService.findAll(locale, currency, codes)
				.stream().map(b -> (Brand) this.entityToDo(b)).collect(Collectors.toSet());
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
	
	private Brand entityToDo(io.nzbee.entity.brand.Brand e) {
		return	new Brand(
					 e.getBrandCode(),
					 e.getBrandAttribute().getBrandDesc(),
					 0,
					 e.getLocale(), 
					 e.getCurrency()
					);
	}
	
	@Override
	public void update(Brand domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Brand domainObject) {
		// TODO Auto-generated method stub
		
	}

	




}
