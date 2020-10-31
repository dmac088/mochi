package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.exceptions.brand.BrandNotFoundException;

@Component
public class PostgresBrandAdapter implements IBrandPortService {

	@Autowired 
	private IBrandService brandService;
	
	@Override
	@Transactional(readOnly = true)
	public Set<Brand> findAll(String locale) {
		return brandService.findAll(locale)
				.stream().map(b -> this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Brand findByCode(String locale, String code) {
		io.nzbee.entity.brand.BrandEntity b = brandService.findByCode(locale, code)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for code " + code));
		return entityToDo(b);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Brand findByProductCode(String locale, String productCode) {
		io.nzbee.entity.brand.BrandEntity b = brandService.findByProductCode(locale, productCode)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for product code " + productCode));
		return entityToDo(b);
	}

	@Override
	@Transactional(readOnly = true)
	public Brand findByDesc(String locale, String desc) {
		io.nzbee.entity.brand.BrandEntity b = brandService.findByDesc(locale, desc)
				.orElseThrow(() -> new BrandNotFoundException("Brand not found for product desc " + desc));
		return entityToDo(b);
	}

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public Set<Brand> findAll(String locale, String category) {
		return brandService.findAll(locale, category)
				.stream().map(b -> (Brand) this.entityToDo(b)).collect(Collectors.toSet());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Set<Brand> findAll(String locale, Set<String> codes) {
		return brandService.findAll(locale, codes)
				.stream().map(b -> (Brand) this.entityToDo(b)).collect(Collectors.toSet());
	}
	
	@Override
	@Transactional
	public void save(Brand domainObject) {
		
		Optional<io.nzbee.entity.brand.BrandEntity> ob = 
				brandService.findByCode(domainObject.getBrandCode());
						
		io.nzbee.entity.brand.BrandEntity b = 
		(ob.isPresent())
		? ob.get() 
		: new io.nzbee.entity.brand.BrandEntity();
		
		io.nzbee.entity.brand.attribute.BrandAttributeEntity ba = new io.nzbee.entity.brand.attribute.BrandAttributeEntity();
		if(ob.isPresent()) {
			Optional<io.nzbee.entity.brand.attribute.BrandAttributeEntity> oba =
					ob.get().getAttributes().stream().filter(a -> a.getLclCd().equals(domainObject.getLocale())).findFirst();
			
			ba = (oba.isPresent()) 
			? oba.get()
			: new io.nzbee.entity.brand.attribute.BrandAttributeEntity();
		}
							
		b.setBrandCode(domainObject.getBrandCode());
		b.setLocale(domainObject.getLocale());
		
		ba.setBrandDesc(domainObject.getBrandDesc());
		ba.setLclCd(domainObject.getLocale());
		b.addAttribute(ba);
		ba.setBrand(b);
		
		brandService.save(b);		
	}
	
	private Brand entityToDo(io.nzbee.entity.brand.BrandEntity e) {
		return	new Brand(
					 e.getBrandCode(),
					 e.getBrandAttribute().getBrandDesc(),
					 e.getCount(),
					 e.getLocale()
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
