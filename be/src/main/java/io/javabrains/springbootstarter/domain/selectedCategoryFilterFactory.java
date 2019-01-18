package io.javabrains.springbootstarter.domain;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;

public class selectedCategoryFilterFactory {
    private String categoryDesc;

    /**
     * injected parameter
     */
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @Factory
    public Query getFilter() {
        return new TermQuery( new Term( "categoryDesc", categoryDesc.toString() ) );
    }
}