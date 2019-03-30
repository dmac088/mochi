import React, { Component } from 'react';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import Banner from './Banner';
import BestSeller from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import PreviewCategoryContainer from '../PreviewCategory/PreviewCategoryContainer';
import Highlights from '../Highlights/Highlights';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import ReactTransitionGroup from 'react-addons-transition-group';

class Landing extends Component {

  render() {
    return(
      <React.Fragment>
        <HeroSlider
          {...this.props}
        />
        <Policy />
        <Highlights
          {...this.props}
        />
        <Banner />
        <PreviewCategoryContainer
          {...this.props}
        />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <QuickViewProduct
          {...this.props}
        />
      </React.Fragment>
    );
  }
}

export default Landing;
