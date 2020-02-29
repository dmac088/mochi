import React, { Component } from 'react';
import { PreviewCategory } from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

export const PreviewCategoryContainer = (props) => {
    return props.previewCategories.map(category => {
      return (
          <PreviewCategory
             key={category.categoryId}
             category={category}
             {...props}
          />
      )
    });
  }
