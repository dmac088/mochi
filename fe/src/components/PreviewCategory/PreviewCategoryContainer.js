import React, { Component } from 'react';
import PreviewCategory from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

class PreviewCategoryContainer extends Component {

  render() {
    const { locale, setCurrentProductId, previewCategories } = this.props;
    return previewCategories.map(category => {
      return (
          <PreviewCategory
             category={category}
             {...this.props}
          />
      )
    });
  }
}

export default PreviewCategoryContainer;
