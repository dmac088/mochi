import React, { Component } from 'react';
import CategoryMenuContainer from './CategoryMenuContainer';
import Slider from "react-slick";
import { HsSlickArrowLeft, HsSlickArrowRight } from '../../services/helpers/Helper';
import { connect } from 'react-redux';
const $ = window.$;

class HeroSlider extends Component {

  componentDidMount() {
    // built-in feature, for more info check the [doc](http://kenwheeler.github.io/slick/)

    // let element = document.querySelector('#zzz');
    // console.log(element);
    //
    // element.addEventListener('beforeChange', function(event, slick, direction) {
    //   console.log("slide");
    //     //reinitSlick();
    // });
    //
    // $('.slick-prev, .slick-next').on('click', function(){
    //     reinitSlick();
    // });
    //
    // var reinitSlick = function() {
    //     $slick.slick('slickSetOption', {
    //       'autoplay': false
    //     }, false);
    // }


    // var element = document.querySelector('.slick-slide');
    // element.on('afterChange', function(event, slick, currentSlide){
    //   console.log(currentSlide);
    // });
    //element.classList.add("hero-slider-item");
    //element.classList.add("slider-bg-5");
  }


  render() {
    const hs1_settings = {
  		arrows: true,
  		autoplay: true,
  		autoplaySpeed: 8000,
  		dots: false,
  		pauseOnFocus: false,
  		pauseOnHover: false,
  		fade: true,
  		infinite: true,
  		slidesToShow: 1,
      prevArrow: <HsSlickArrowLeft />,
      nextArrow: <HsSlickArrowRight />,
  	};

    const hs2_settings = {
      arrows: false,
  		autoplay: true,
  		autoplaySpeed: 10000,
  		dots: true,
  		pauseOnFocus: false,
  		pauseOnHover: false,
  		fade: true,
  		infinite: true,
  		slidesToShow: 1
    }

    const hs3_settings = {
      arrows: false,
      autoplay: true,
      autoplaySpeed: 10000,
      dots: true,
      pauseOnFocus: false,
      pauseOnHover: false,
      fade: true,
      infinite: true,
      slidesToShow: 1,
      class: "hero-slider-item",
      beforeChange: (current, next) => {
        // current is always undefined
        console.log(current, next);
        let element = document.querySelector('.slick-slide');
        // element.on('afterChange', function(event, slick, currentSlide){
        //   console.log(currentSlide);
        // });
        //element.classList.add("hero-slider-item");
        //element.classList.add("slider-bg-5");
      }
    }


    return(
      <div className="hero-slider-with-category-container mt-35 mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-3 col-md-12">
              <CategoryMenuContainer
                match={this.props.match}
                categoryList={this.props.categoryList}
              />
            </div>
            <div className="col-lg-6 col-md-12">
              <div className="slider-container">
                <div className="hero-slider-three">
                  <Slider {...hs3_settings}>
                    <div  className="slider-bg-5" >
                      <div className="slider-content">
                        <h1>Organic<span>vegetables</span></h1>
                        <h1 className="change-text">Up to <span>50% off</span></h1>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>save up to 10%</span></p>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>free shipping</span></p>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>return in 24 hours</span></p>
                        <a href="shop-left-sidebar.html" className="slider-two-btn mt-20">start at $9</a>
                      </div>
                    </div>
                    <div className="slider-bg-6">
                      <div className="slider-content">
                        <h1>Organic<span>vegetables</span></h1>
                        <h1 className="change-text">Up to <span>50% off</span></h1>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>save up to 10%</span></p>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>free shipping</span></p>
                        <p><img src="assets/images/icon-slider.png" alt="" /> <span>return in 24 hours</span></p>
                        <a href="shop-left-sidebar.html" className="slider-two-btn mt-20">start at $9</a>
                      </div>
                    </div>
                  </Slider>
                </div>
              </div>
            </div>
            <div className="col-lg-3 col-md-12">
              <div className="slider-side-banner-container">
                <div className="row">
                  <div className="col-lg-12 col-sm-6">
                    <div className="slider-side-banner mb-20 mb-sm-0 mb-xs-0">
                      <a href="shop-left-sidebar.html">
                        <img src="assets/images/banners/home3-banner1-1.jpg" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                  <div className="col-lg-12 col-sm-6">
                    <div className="slider-side-banner mb-0 mb-sm-0 mb-xs-0">
                      <a href="shop-left-sidebar.html">
                        <img src="assets/images/banners/home3-banner1-2.jpg" className="img-fluid" alt="" />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default HeroSlider;
