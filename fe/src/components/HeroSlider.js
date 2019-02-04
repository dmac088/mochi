import React, { Component } from 'react';
import CategoryMenuContainer from './CategoryMenuContainer';
import { connect } from 'react-redux';
const $ = window.$;

class HeroSlider extends Component {

  componentDidMount(){
      this.initHeroSlider();
  }

  initHeroSlider = () => {
    /*--
  	Hero slider one active
  	-----------------------------------*/
  	let heroSlider = $('.hero-slider-one');
  	heroSlider.slick({
  		arrows: true,
  		autoplay: true,
  		autoplaySpeed: 8000,
  		dots: false,
  		pauseOnFocus: false,
  		pauseOnHover: false,
  		fade: true,
  		infinite: true,
  		slidesToShow: 1,
  		prevArrow: '<button type="button" class="slick-prev"><i class="fa fa-chevron-left"></i></button>',
  		nextArrow: '<button type="button" class="slick-next"><i class="fa fa-chevron-right"></i></button>',
  	});

  	/*--
  	Hero slider two active
  	-----------------------------------*/
  	let heroSliderTwo = $('.hero-slider-two');
  	heroSliderTwo.slick({
  		arrows: false,
  		autoplay: true,
  		autoplaySpeed: 10000,
  		dots: true,
  		pauseOnFocus: false,
  		pauseOnHover: false,
  		fade: true,
  		infinite: true,
  		slidesToShow: 1
  	});

  	/*--
  	Hero slider three active
  	-----------------------------------*/
  	let heroSliderThree = $('.hero-slider-three');
  	heroSliderThree.slick({
  		arrows: false,
  		autoplay: true,
  		autoplaySpeed: 10000,
  		dots: true,
  		pauseOnFocus: false,
  		pauseOnHover: false,
  		fade: true,
  		infinite: true,
  		slidesToShow: 1
  	});
  }


  render() {
    return(
      <div className="hero-slider-with-category-container mt-35 mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-3 col-md-12">
              {/*=======  slider left category  =======*/}
              <CategoryMenuContainer
                categoryList={this.props.categoryList}
              />
            </div>
            <div className="col-lg-6 col-md-12">
              {/*=======  slider container  =======*/}
              <div className="slider-container">
                {/*=======  Slider area  =======*/}
                <div className="hero-slider-three">
                  {/*=======  hero slider item  =======*/}
                  <div className="hero-slider-item slider-bg-5">
                    <div className="slider-content">
                      <h1>Organic<span>vegetables</span></h1>
                      <h1 className="change-text">Up to <span>50% off</span></h1>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>save up to 10%</span></p>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>free shipping</span></p>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>return in 24 hours</span></p>
                      <a href="shop-left-sidebar.html" className="slider-two-btn mt-20">start at $9</a>
                    </div>
                  </div>
                  {/*=======  End of hero slider item  =======*/}
                  {/*=======  Hero slider item  =======*/}
                  <div className="hero-slider-item slider-bg-6">
                    <div className="slider-content">
                      <h1>Organic<span>vegetables</span></h1>
                      <h1 className="change-text">Up to <span>50% off</span></h1>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>save up to 10%</span></p>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>free shipping</span></p>
                      <p><img src="assets/images/icon-slider.png" alt /> <span>return in 24 hours</span></p>
                      <a href="shop-left-sidebar.html" className="slider-two-btn mt-20">start at $9</a>
                    </div>
                  </div>
                  {/*=======  End of Hero slider item  =======*/}
                </div>
                {/*=======  End of Slider area  =======*/}
              </div>
              {/*=======  End of slider container  =======*/}
            </div>
            <div className="col-lg-3 col-md-12">
              {/*=======  slider side banner container  =======*/}
              <div className="slider-side-banner-container">
                <div className="row">
                  {/*=======  single banner  =======*/}
                  <div className="col-lg-12 col-sm-6">
                    <div className="slider-side-banner mb-20 mb-sm-0 mb-xs-0">
                      <a href="shop-left-sidebar.html">
                        <img src="assets/images/banners/home3-banner1-1.jpg" className="img-fluid" alt />
                      </a>
                    </div>
                  </div>
                  {/*=======  End of single banner  =======*/}
                  {/*=======  single banner  =======*/}
                  <div className="col-lg-12 col-sm-6">
                    <div className="slider-side-banner mb-0 mb-sm-0 mb-xs-0">
                      <a href="shop-left-sidebar.html">
                        <img src="assets/images/banners/home3-banner1-2.jpg" className="img-fluid" alt />
                      </a>
                    </div>
                  </div>
                  {/*=======  End of single banner  =======*/}
                </div>
              </div>
              {/*=======  End of slider side banner container  =======*/}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default HeroSlider;
