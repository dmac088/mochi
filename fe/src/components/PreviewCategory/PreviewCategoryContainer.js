import React, { Component } from 'react';
import PreviewCategory from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

class PreviewCategoryContainer extends Component {


getCategories = (lang = "en-GB", preview = 1) =>
  categoryApi.findAllPreview(lang, preview)
  .then((response) => {
      return response.text();
  })
  .then((responseText) => {
      return JSON.parse(responseText);
  })
  .then((responseJSON) => {
      return responseJSON
  })
  .catch(()=>{
      console.log('getCategories failed!');
  });


  render() {
    return (
        <PreviewCategory />
    )
  }
}

export default PreviewCategoryContainer;
