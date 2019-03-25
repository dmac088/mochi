import React, { Component } from 'react';
import PreviewCategory from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

class PreviewCategoryContainer extends Component {

  filterPreview = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryPreview === 1;
    });
  }

  render() {
    const { locale, setCurrentProductId, categoryList } = this.props;
    console.log(this.filterPreview(categoryList));
    return this.filterPreview(categoryList).map(category => {
      return (
          <PreviewCategory
             key={category.categoryId}
             locale={locale}
             category={category}
             setCurrentProductId={setCurrentProductId}
          />
      )
    });
  }
}

export default PreviewCategoryContainer;
