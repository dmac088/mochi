import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import  HeroSlider from './HeroSlider';
import { Policy } from './Policy';
import { Banner } from './Banner';
import { BestSeller } from './BestSeller';
import { BlogPosts } from './BlogPosts';
import { BrandSlider } from './BrandSlider';
import { PreviewCategoryContainer } from '../PreviewCategory/PreviewCategoryContainer';
import Highlights from '../Highlights/Highlights';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import ReactTransitionGroup from 'react-addons-transition-group';

  export const Landing = withRouter(({ ...props }) => {
    const { setCurrentProductId, currentProductId, showQVModal, toggleQuickView, previewCategories } = props;
    return(
      <React.Fragment>
        <HeroSlider
          {...props}
        />
        <Policy />
        <Highlights
          {...props}
        />
        <Banner />
        <PreviewCategoryContainer
          previewCategories={previewCategories}
          setCurrentProductId={setCurrentProductId}
          {...props}
        />
        <BestSeller />
        <BrandSlider />
        <QuickViewProduct
          productId={currentProductId}
          isShowing={showQVModal}
          toggleQuickView={toggleQuickView}
          {...props}
        />
      </React.Fragment>
    );
  });
