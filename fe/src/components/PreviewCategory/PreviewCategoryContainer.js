import React, { Component } from 'react';
import { PreviewCategory } from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

export const PreviewCategoryContainer = (props) => {
    const { locale, setCurrentProductId, previewCategories } = props;
    return previewCategories.map(category => {
      return (
          <PreviewCategory
             key={category.categoryId}
             category={category}
             {...props}
          />
      )
    });
  }
