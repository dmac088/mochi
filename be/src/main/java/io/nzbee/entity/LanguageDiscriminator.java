package io.nzbee.entity;

import org.hibernate.search.analyzer.Discriminator;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.tag.Tag;
import io.nzbee.entity.tag.attribute.TagAttribute;

public class LanguageDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {

    	if (value == null || !(	entity instanceof Tag ||
    							entity instanceof TagAttribute ||
    							entity instanceof Product ||
    							entity instanceof ProductAttribute ||
    							entity instanceof Category ||
    							entity instanceof CategoryAttribute ||
    							entity instanceof Brand || 
    							entity instanceof BrandAttribute)) { 
            return null;
        }
        System.out.println(entity.getClass().toGenericString() + " - " + field + " - " + value);
        return (String) value;
    }
}