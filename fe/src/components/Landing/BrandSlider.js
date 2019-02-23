import React, { Component } from 'react';
import { connect } from 'react-redux';
const $ = window.$;


class BrandSlider extends Component {

componentDidMount(){
    this.initBrandSlider();
}

initBrandSlider = () => {

  /*--
  Brand slider active
  -----------------------------------*/
  let brandLogoSlider = $('.brand-logo-wrapper');
  brandLogoSlider.slick({
    arrows: true,
    autoplay: true,
    dots: false,
    infinite: true,
    slidesToShow: 5,
    prevArrow: '<button type="button" class="slick-prev"><i class="fa fa-caret-left"></i></button>',
    nextArrow: '<button type="button" class="slick-next"><i class="fa fa-caret-right"></i></button>',
    responsive: [{
      breakpoint: 1499,
      settings: {
        slidesToShow: 5,
      }
    },
    {
      breakpoint: 1199,
      settings: {
        slidesToShow: 5,
      }
    },
    {
      breakpoint: 991,
      settings: {
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 767,
      settings: {
        slidesToShow: 3,
      }
    },
    {
      breakpoint: 575,
      settings: {
        slidesToShow: 2,
      }
    }
    ]
  });
}


render() {
  return (
    <div className="slider brand-logo-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            {/*=======  blog slider section title  =======*/}
            <div className="section-title">
              <h3>brand logos</h3>
            </div>
            {/*=======  End of blog slider section title  =======*/}
          </div>
        </div>
        <div className="row">
          <div className="col-lg-12">
            {/*=======  brand logo wrapper  =======*/}
            <div className="brand-logo-wrapper pt-20 pb-20">
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand1.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand2.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand3.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand4.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand5.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
              {/*=======  single-brand-logo  =======*/}
              <div className="col">
                <div className="single-brand-logo">
                  <a href="#">
                    <img src="assets/images/brands/brand6.png" className="img-fluid" alt="" />
                  </a>
                </div>
              </div>
              {/*=======  End of single-brand-logo  =======*/}
            </div>
            {/*=======  End of brand logo wrapper  =======*/}
          </div>
        </div>
      </div>
    </div>
  )

}

}

export default BrandSlider;
