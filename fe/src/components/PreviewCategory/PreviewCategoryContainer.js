import React, { Component } from 'react';
import PreviewCategory from './PreviewCategory';
import * as categoryApi from '../../data/categories/api';

class PreviewCategoryContainer extends Component {

  constructor(props) {
    super(props);
    this.state = {
      categories: [],
    };
  }

  getCategories = (lang = "en-GB", preview = 1) =>
    categoryApi.findAllPreview(lang, preview)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        this.setState({
          categories: responseJSON,
        })
    })
    .catch(()=>{
        console.log('getCategories failed!');
  });

  componentWillMount() {
    this.getCategories();
  }

  render() {
    const { categories } = this.state;
    return categories.map(category => {
      return (
          <PreviewCategory
             key={category.categoryId}
             category={category}
          />
      )
    });
  }
}

export default PreviewCategoryContainer;
