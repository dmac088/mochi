package io.nzbee.entity;

import org.hibernate.search.analyzer.Discriminator;

public class LanguageDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {

    	if (value == null || !(entity instanceof ProductAttribute ||  entity instanceof CategoryAttribute || entity instanceof BrandAttribute)) {
            return null;
        }
        System.out.println(entity.getClass().toGenericString() + " - " + field + " - " + value);
        return (String) value;
    }
}