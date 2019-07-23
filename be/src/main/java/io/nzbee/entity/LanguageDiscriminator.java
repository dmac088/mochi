package io.nzbee.entity;

import org.hibernate.search.analyzer.Discriminator;

import io.nzbee.entity.brand.BrandAttribute;
import io.nzbee.entity.category.CategoryAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute;

public class LanguageDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {

    	if (value == null || !(entity instanceof ProductAttribute ||  entity instanceof CategoryAttribute || entity instanceof BrandAttribute)) {
            return null;
        }
        System.out.println(entity.getClass().toGenericString() + " - " + field + " - " + value);
        return (String) value;
    }
}