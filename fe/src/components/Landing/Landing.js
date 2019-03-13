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
import ProductQuickView from './ProductQuickView';
import { withRouter } from 'react-router-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
//const $ = window.$;



class Landing extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showScroller: false
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

    return(
      <div>
        <HeroSlider
          match={this.props.match}
          categoryList={this.props.categoryList}
        />
        <Policy />
        <Highlights
          {...this.props}
        />
        <Banner />
        <PreviewCategoryContainer />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <Footer />
        <ProductQuickView />
            <a  onClick={this.animateScroll}
                href="#"
                className={"scroll-top " + ((this.state.theposition >= 400) ? "fadeIn" : "fadeOut")}
            />
      </div>
      );
  }
}

export default withRouter(Landing);
