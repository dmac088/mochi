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

  componentDidMount() {
    this.getCategories();
  }

  render() {
    const { categories } = this.state;
    const { locale, setCurrentProductId } = this.props;
    return categories.map(category => {
      return (
          <PreviewCategory
             locale={locale}
             key={category.categoryId}
             category={category}
             setCurrentProductId={setCurrentProductId}
          />
      )
    });
  }
}

export default PreviewCategoryContainer;
