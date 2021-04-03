package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.exceptions.NotFoundException;

@Component
public class PostgresBrandAdapter implements IBrandPortService {

	@Autowired 
	private IBrandService brandService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Brand> findAll(String locale) {
		return brandService.findAll(locale)
				.stream().map(b -> this.DTOToDo(b)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Brand> findAllByProductType(String locale, Class<?> cls) {
		return brandService.findAllByProductType(locale, PostgresProductAdapter.mapDomainClassToDTOClass(cls))
				.stream().map(b -> this.DTOToDo(b)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Brand findByCode(String locale, String code) {
		BrandDTO b = brandService.findByCode(locale, code)
				.orElseThrow(() -> new NotFoundException("Brand not found for code " + code));
		return DTOToDo(b);
	}

	@Override
	@Transactional(readOnly = true)
	public Brand findByDesc(String locale, String desc) {
		BrandDTO b = brandService.findByDesc(locale, desc)
				.orElseThrow(() -> new NotFoundException("Brand not found for product desc " + desc));
		return DTOToDo(b);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		
		return brandService.findAll(locale, 
									currency, 
									categoryCode, 
									new StringCollectionWrapper(categoryCodes),
									new StringCollectionWrapper(tagCodes),
				 					maxPrice)
				.stream().map(b -> (Brand) this.DTOToDo(b)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Brand> findAll(String locale, String rootCategory, String category) {
		return brandService.findAll(locale, rootCategory, category)
				.stream().map(b -> (Brand) this.DTOToDo(b)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Brand> findAll(String locale, Set<String> codes) {
		return brandService.findAll(locale, new StringCollectionWrapper(codes))
				.stream().map(b -> (Brand) this.DTOToDo(b)).collect(Collectors.toList());
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
	
	private Brand DTOToDo(BrandDTO dto) {
		return	new Brand(
					 dto.getBrandCode(),
					 dto.getBrandDesc(),
					 dto.getCount(),
					 dto.getLocale()
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
