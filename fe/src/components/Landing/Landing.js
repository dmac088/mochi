import React, { Component } from 'react';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import Banner from './Banner';
import BestSeller from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import PreviewCategoryContainer from '../PreviewCategory/PreviewCategoryContainer';
import Footer from '../Footer/Footer';
import Highlights from '../Highlights/Highlights';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import ReactTransitionGroup from 'react-addons-transition-group';

class Landing extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showScroller: false,
        showQVModal: false,
        currentProductId: null,
    };
  }

  setCurrentProductId = (e) => {
    e.preventDefault();
    this.setState({
      currentProductId: e.target.id,
      showQVModal: true,
    });
  }

  toggleQuickView = () => {
    this.setState({
      "showQVModal": !this.state.showQVModal,
    });
  }

  componentDidMount() {
    window.addEventListener('scroll', this.listenToScroll, { passive: true });
    this.animateScroll();
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.listenToScroll, { passive: true });
  }

  listenToScroll = () => {
    let scroll = document.documentElement.scrollTop;
    this.setState({
      theposition: scroll,
    })
  }

  animateScroll = (e) => {
     if(!(e === undefined)) {e.preventDefault()}
     const body = document.querySelector('html,body');
     document.querySelector('.scroll-top').onclick = function() {
       Velocity(body, 'scroll', { duration: 1000 });
     };
  }


  render() {
    const { locale } = this.props.match.params;
    return(
      <div>
        <HeroSlider
          {...this.props}
          categoryList={this.props.categoryList}
        />
        <Policy />
        <Highlights
          {...this.props}
          setCurrentProductId={this.setCurrentProductId}
        />
        <Banner />
        <PreviewCategoryContainer
          locale={locale}
          setCurrentProductId={this.setCurrentProductId}
        />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <Footer />
        <QuickViewProduct
          {...this.props}
          productId={this.state.currentProductId}
          isShowing={this.state.showQVModal}
          toggleQuickView={this.toggleQuickView}
        />
        <a
          onClick={this.animateScroll}
          href="#"
          className={"scroll-top " + ((this.state.theposition >= 400) ? "fadeIn" : "fadeOut")}
        />
      </div>
      );
  }
}

export default Landing;
