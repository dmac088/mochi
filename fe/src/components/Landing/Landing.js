import React, { Component } from 'react';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import Banner from './Banner';
import BestSeller from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import CategoryPreview from './CategoryPreview';
import Footer from '../Footer/Footer';
import ProductHighlights from './ProductHighlights';
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
    let scrollTop = ((this.state.theposition >= 400) ? <a onClick={this.animateScroll}
                                                          href="#"
                                                          className="scroll-top fadeIn"
                                                      /> :
                                                      <a  href="#"
                                                          className="scroll-top fadeOut"
                                                      />);

    return(
      <div>
        <HeroSlider
          match={this.props.match}
          categoryList={this.props.categoryList}
        />
        <Policy />
        <ProductHighlights />
        <Banner />
        <CategoryPreview />
        <BestSeller />
        <BlogPosts />
        <BrandSlider />
        <Footer />
        <ProductQuickView />
        <ReactTransitionGroup
          component={React.Fragment}>
          {scrollTop}
        </ReactTransitionGroup>
      </div>
      );
  }
}

export default withRouter(Landing);
