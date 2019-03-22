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
import ReactTransitionGroup from 'react-addons-transition-group';

class Landing extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showScroller: false,
    };
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
    const { setCurrentProductId, currentProductId, showQVModal, toggleQuickView } = this.props;

    return(
      <div>
        <Header
          {...this.props}
        />
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
        <Footer />
        <QuickViewProduct
          {...this.props}
          productId={currentProductId}
          isShowing={showQVModal}
          toggleQuickView={toggleQuickView}
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
