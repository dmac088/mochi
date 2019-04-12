package io.javabrains.springbootstarter.domain;

import org.hibernate.search.analyzer.Discriminator;

public class LanguageDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {
        if ( value == null || !( entity instanceof ProductAttribute || entity instanceof ProductCategoryAttribute || entity instanceof BrandAttribute)) {
            return null;
        }
        //System.out.println(entity.getClass().toString());
        return (String) value;

    }
}