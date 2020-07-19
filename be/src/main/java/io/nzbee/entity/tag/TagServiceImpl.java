package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.search.IFacetService;

@Service(value = "tagEntityService")
public class TagServiceImpl implements ITagService, IFacetService {

	@Autowired
	private ITagDao productTagDAO;
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		return productTagDAO.findAll(
									locale, 
									currency, 
									categoryCode,
									categoryCodes, 
									brandCodes,
									maxPrice);
	}

	@Override
	public List<Tag> findAll(String locale, String currency) {
		return productTagDAO.findAll(locale, currency);
	}
	
	@Override
	public List<Tag> findAll(String locale, String currency, Set<String> codes) {
		return productTagDAO.findAll(locale, currency, codes);
	}

	@Override
	public Optional<Tag> findById(String locale, String currency, long id) {
		return productTagDAO.findById(locale, currency, id);
	}

	@Override
	public Optional<Tag> findByCode(String locale, String currency, String code) {
		return productTagDAO.findByCode(locale, currency, code);
	}
	
	@Override
	public Optional<Tag> findByDesc(String locale, String currency, String desc) {
		return productTagDAO.findByDesc(locale, currency, desc);
	}
	
	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	public String getFacetField() {
		return "product.tags.tagToken";
	}

	@Override
	public String getFacetCategory() {
		return "tag";
	}

	@Override
	public void save(Tag t) {
		productTagDAO.save(t);
	}

	@Override
	public void update(Tag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag t) {
		// TODO Auto-generated method stub
		
	}
	
}
