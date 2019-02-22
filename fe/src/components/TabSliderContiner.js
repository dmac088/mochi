import React, { Component } from 'react';
import { connect } from 'react-redux';
const $ = window.$;

class TabSliderContiner extends Component {

  componentDidMount(){
      this.initTabSliderContiner();
  }

  initTabSliderContiner = () => {
    let tabSlider = $('.tab-slider-container');
    tabSlider.slick({
      arrows: true,
      autoplay: false,
      dots: false,
      infinite: true,
      slidesToShow: 4,
      prevArrow: '<button type="button" className="slick-prev"><i className="fa fa-caret-left"></i></button>',
      nextArrow: '<button type="button" className="slick-next"><i className="fa fa-caret-right"></i></button>',
      responsive: [{
        breakpoint: 1499,
        settings: {
          slidesToShow: 4,
        }
      },
      {
        breakpoint: 1199,
        settings: {
          slidesToShow: 4,
        }
      },
      {
        breakpoint: 991,
        settings: {
          slidesToShow: 3,
        }
      },
      {
        breakpoint: 767,
        settings: {
          slidesToShow: 2,
        }
      },
      {
        breakpoint: 575,
        settings: {
          slidesToShow: 2,
        }
      },
      {
        breakpoint: 479,
        settings: {
          slidesToShow: 1,
        }
      }
      ]
    });
  }


render() {
  return(
    <div className="tab-slider-container">
      {/*=======  single tab slider item  =======*/}
      <div className="single-tab-slider-item">
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
      </div>
      {/*=======  End of single tab slider product  =======*/}
      {/*=======  single tab slider item  =======*/}
      <div className="single-tab-slider-item">
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
      </div>
      {/*=======  End of single tab slider product  =======*/}
      {/*=======  single tab slider item  =======*/}
      <div className="single-tab-slider-item">
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <img src="assets/images/products/product06.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
      </div>
      {/*=======  End of single tab slider product  =======*/}
      {/*=======  single tab slider item  =======*/}
      <div className="single-tab-slider-item">
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product07.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product08.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
      </div>
      {/*=======  End of single tab slider product  =======*/}
      {/*=======  single tab slider item  =======*/}
      <div className="single-tab-slider-item">
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product09.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
        {/*=======  tab slider sub product  =======*/}
        <div className="gf-product tab-slider-sub-product">
          <div className="image">
            <a href="single-product.html">
              <span className="onsale">Sale!</span>
              <img src="assets/images/products/product10.jpg" className="img-fluid" alt="" />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">Sed tempor ehicula non commodo</a></h3>
            <div className="price-box">
              <span className="main-price">$89.00</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
        {/*=======  End of tab slider sub product  =======*/}
      </div>
      {/*=======  End of single tab slider product  =======*/}
    </div>
  )
}

}

export default TabSliderContiner;
