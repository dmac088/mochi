import React, { Component } from 'react';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import Banner from './Banner';
import BestSeller from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import PreviewCategoryContainer from '../PreviewCategory/PreviewCategoryContainer';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Highlights from '../Highlights/Highlights';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import Scroller from '../Scroller';
import ReactTransitionGroup from 'react-addons-transition-group';

class Landing extends Component {

  render() {
    const { locale } = this.props.match.params;
    const { setCurrentProductId, currentProductId, showQVModal, toggleQuickView } = this.props;

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
          setCurrentProductId={setCurrentProductId}
        />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <QuickViewProduct
          {...this.props}
          productId={currentProductId}
          isShowing={showQVModal}
          toggleQuickView={toggleQuickView}
        />
      </React.Fragment>
    );
  }
}

export default Landing;
