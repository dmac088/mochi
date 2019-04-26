package io.javabrains.springbootstarter.entity;

import org.hibernate.search.analyzer.Discriminator;

public class LanguageDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {
    	//System.out.println(value);
    	if (!field.equals("lclCd") || value == null || !( entity instanceof ProductAttribute || entity instanceof CategoryAttribute || entity instanceof BrandAttribute)) {
            return null;
        }
//    	System.out.println("made it!");
//        System.out.println(field + " - " + value);
        return (String) value;
    }
}