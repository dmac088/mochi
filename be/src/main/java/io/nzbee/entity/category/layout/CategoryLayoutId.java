package io.nzbee.entity.category.layout;

import java.io.Serializable;
import java.util.Objects;

public class CategoryLayoutId implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private Long categoryId;
    private Long layoutId;
 
    public CategoryLayoutId() {
 
    }
 
    public CategoryLayoutId(Long categoryId, Long layoutId) {
        super();
        this.categoryId = categoryId;
        this.layoutId = layoutId;
    }
 
    public Long getCategoryId() {
        return categoryId;
    }
 
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
 
    public Long getLayoutId() {
        return layoutId;
    }
 
    public void setLayoutsId(Long layoutId) {
        this.layoutId = layoutId;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((categoryId == null) ? 0 : categoryId.hashCode());
        result = prime * result
                + ((layoutId == null) ? 0 : layoutId.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoryLayoutId other = (CategoryLayoutId) obj;
        return Objects.equals(getCategoryId(), other.getCategoryId()) && Objects.equals(getLayoutId(), other.getLayoutId());
    }
}
