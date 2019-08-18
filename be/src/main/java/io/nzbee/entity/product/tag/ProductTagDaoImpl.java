package io.nzbee.entity.product.tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.Currency_;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.price.ProductPriceType_;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute_;
import io.nzbee.variables.ProductVars;

@Component 
public class ProductTagDaoImpl  implements IProductTagDao {
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<ProductTag> findById(long id) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ProductTag> cq = cb.createQuery(ProductTag.class);
		
		Root<ProductTag> root = cq.from(ProductTag.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(ProductTag_.productTagId), id));
		
		TypedQuery<ProductTag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public Optional<ProductTag> findByCode(String code) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ProductTag> cq = cb.createQuery(ProductTag.class);
		
		Root<ProductTag> root = cq.from(ProductTag.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(ProductTag_.productTagCode), code));
		
		TypedQuery<ProductTag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	
	@Override
	public Optional<ProductTag> findByDesc(String desc, String locale) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ProductTag> cq = cb.createQuery(ProductTag.class);
		Root<ProductTag> root = cq.from(ProductTag.class);
		Join<ProductTag, ProductTagAttribute> attribute = root.join(ProductTag_.attributes);
		

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(attribute.get(ProductTagAttribute_.lclCd), locale));
		conditions.add(cb.equal(attribute.get(ProductTagAttribute_.tagDesc), desc));
		
		TypedQuery<ProductTag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}


	@Override
	public List<ProductTag> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductTag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductTag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductTag t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductTag t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<ProductTag> cq = cb.createQuery(ProductTag.class);
		
		Root<ProductTag> root = cq.from(ProductTag.class);
		//Join<ProductTagAttribute, ProductTag> tag = root.join(ProductTagAttribute_.tag);
		Join<ProductTag, Product> product = root.join(ProductTag_.products);
		Join<Product, ProductAttribute> productAttribute = product.join(Product_.attributes);
		Join<Product, CategoryProduct> category = product.join(Product_.categories);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<Product, ProductStatus> status = product.join(Product_.productStatus);
		
		
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(Category_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!(priceStart == null && priceEnd == null)) {
			Join<Product, ProductPrice> price = product.join(Product_.prices);
			Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
			Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
			conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
			conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
			conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
			conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
			conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
			conditions.add(cb.equal(curr.get(Currency_.code), currency));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));

		TypedQuery<ProductTag> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
    }

	
}
