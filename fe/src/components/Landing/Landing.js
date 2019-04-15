import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import { Banner } from './Banner';
import { BestSeller } from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import { PreviewCategoryContainer } from '../PreviewCategory/PreviewCategoryContainer';
import Highlights from '../Highlights/Highlights';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import ReactTransitionGroup from 'react-addons-transition-group';

class Landing extends Component {

  render() {
    const { locale } = this.props.match.params;
    const { setCurrentProductId, currentProductId, showQVModal, toggleQuickView, categoryList, previewCategories } = this.props;
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
          locale={locale}
          previewCategories={previewCategories}
          setCurrentProductId={setCurrentProductId}
          {...this.props}
        />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <QuickViewProduct
          productId={currentProductId}
          isShowing={showQVModal}
          toggleQuickView={toggleQuickView}
          {...this.props}
        />
      </React.Fragment>
    );
  }
}

export default withRouter(Landing);
