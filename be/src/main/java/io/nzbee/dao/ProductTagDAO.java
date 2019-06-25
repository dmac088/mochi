package io.nzbee.dao;

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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import io.nzbee.entity.Brand;
import io.nzbee.entity.BrandAttribute;
import io.nzbee.entity.BrandAttribute_;
import io.nzbee.entity.Brand_;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttribute_;
import io.nzbee.entity.Category_;
import io.nzbee.entity.Currency;
import io.nzbee.entity.Currency_;
import io.nzbee.entity.Product;
import io.nzbee.entity.ProductAttribute;
import io.nzbee.entity.ProductAttribute_;
import io.nzbee.entity.ProductPrice;
import io.nzbee.entity.ProductPriceType;
import io.nzbee.entity.ProductPriceType_;
import io.nzbee.entity.ProductPrice_;
import io.nzbee.entity.ProductStatus;
import io.nzbee.entity.ProductStatus_;
import io.nzbee.entity.ProductTag;
import io.nzbee.entity.ProductTagAttribute;
import io.nzbee.entity.ProductTagAttribute_;
import io.nzbee.entity.ProductTag_;
import io.nzbee.entity.Product_;
import io.nzbee.variables.ProductVars;

@Component 
public class ProductTagDAO  implements Dao<Product> {
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<Product> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ProductTagAttribute> getAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<Long> brandIds) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<ProductTagAttribute> cq = cb.createQuery(ProductTagAttribute.class);
		
		Root<ProductTagAttribute> root = cq.from(ProductTagAttribute.class);
		Join<ProductTagAttribute, ProductTag> tag = root.join(ProductTagAttribute_.tag);
		Join<ProductTag, Product> product = tag.join(ProductTag_.products);
		Join<Product, ProductAttribute> productAttribute = product.join(Product_.attributes);
		Join<Product, Category> category = product.join(Product_.categories);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<Product, ProductStatus> status = product.join(Product_.productStatus);
		Join<Product, ProductPrice> price = product.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<Category, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(Category_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		
		conditions.add(cb.equal(root.get(ProductTagAttribute_.lclCd), locale));
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));

		TypedQuery<ProductTagAttribute> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
    }

	
}
