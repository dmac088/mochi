package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.attribute.IBrandAttributeService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.exceptions.brand.BrandNotFoundException;

@Component
public class PostgresBrandAdapter implements IBrandPortService {

	@Autowired 
	private IBrandService brandService;
	
	@Autowired 
	private IBrandAttributeService brandAttributeService;

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
	public Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		
		return brandService.findAll(locale, 
									currency, 
									categoryCode, 
									categoryCodes,
									tagCodes,
				 					maxPrice)
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
		
		Optional<io.nzbee.entity.brand.Brand> ob = brandService.findByCode(
																			domainObject.getLocale(),
																			domainObject.getCurrency(), 
																			domainObject.getBrandCode());
		
		io.nzbee.entity.brand.Brand b = (ob.isPresent()) 
										? ob.get()
										: new io.nzbee.entity.brand.Brand();
										
										
		Optional<io.nzbee.entity.brand.attribute.BrandAttribute> oba = brandAttributeService.findByCode(
																			domainObject.getLocale(), 
																			domainObject.getCurrency(), 
																			domainObject.getBrandCode());

		io.nzbee.entity.brand.attribute.BrandAttribute ba = (oba.isPresent()) 
										? oba.get()
										: (new io.nzbee.entity.brand.attribute.BrandAttribute());	
							
		b.setBrandCode(domainObject.getBrandCode());
		b.setLocale(domainObject.getLocale());
		b.setCurrency(domainObject.getCurrency());
		
		ba.setBrandDesc(domainObject.getBrandDesc());
		ba.setLclCd(domainObject.getLocale());
		b.addAttribute(ba);
		ba.setBrand(b);
		
		brandService.save(b);		
	}
	
	private Brand entityToDo(io.nzbee.entity.brand.Brand e) {
		return	new Brand(
					 e.getBrandCode(),
					 e.getBrandAttribute().getBrandDesc(),
					 e.getCount(),
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
