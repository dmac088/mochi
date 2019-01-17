package io.javabrains.springbootstarter.domain;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;

public class selectedCategoryFilterFactory {
    private Long categoryId;

    /**
     * injected parameter
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Factory
    public Query getFilter() {
        return new TermQuery( new Term( "categoryId", categoryId.toString() ) );
    }
}