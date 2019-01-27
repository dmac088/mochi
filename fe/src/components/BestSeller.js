import React, { Component } from 'react';
import { connect } from 'react-redux';
const $ = window.$;


class BestSeller extends Component {

componentDidMount(){
    this.initBestSeller();
}

initBestSeller = () => {
  /*--
  Best seller slider active
  -----------------------------------*/
  let bestSellerSlider = $('.best-seller-slider-container');
  bestSellerSlider.slick({
    arrows: true,
    autoplay: false,
    dots: false,
    infinite: false,
    slidesToShow: 3,
    prevArrow: '<button type="button" class="slick-prev"><i class="fa fa-caret-left"></i></button>',
    nextArrow: '<button type="button" class="slick-next"><i class="fa fa-caret-right"></i></button>',
    responsive: [{
      breakpoint: 1499,
      settings: {
        slidesToShow: 3,
      }
    },
    {
      breakpoint: 1199,
      settings: {
        slidesToShow: 3,
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
  return (
    <div className="slider best-seller-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            {/*=======  category slider section title  =======*/}
            <div className="section-title">
              <h3>best seller</h3>
            </div>
            {/*=======  End of category slider section title  =======*/}
          </div>
        </div>
        <div className="row">
          <div className="col-lg-12">
            {/*=======  best seller slider container  =======*/}
            <div className="best-seller-slider-container pt-15 pb-15">
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product01.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
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
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product02.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Officiis debitis varius risus</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product03.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Phasellus vel hendrerit eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product04.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product05.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
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
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product06.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product07.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
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
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product08.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product09.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
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
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product10.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
              {/*=======  single best seller product  =======*/}
              <div className="col">
                <div className="single-best-seller-item">
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product11.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
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
                    </div>
                  </div>
                  <div className="best-seller-sub-product">
                    <div className="row">
                      <div className="col-lg-4 pl-0 pr-0">
                        <div className="image">
                          <a href="single-product.html">
                            <img src="assets/images/products/product12.jpg" className="img-fluid" alt />
                          </a>
                        </div>
                      </div>
                      <div className="col-lg-8 pl-0 pr-0">
                        <div className="product-content">
                          <div className="product-categories">
                            <a href="shop-left-sidebar.html">Fast Foods</a>,
                            <a href="shop-left-sidebar.html">Vegetables</a>
                          </div>
                          <h3 className="product-title"><a href="single-product.html">Ornare sed consequat nisl eget</a></h3>
                          <div className="price-box">
                            <span className="main-price">$89.00</span>
                            <span className="discounted-price">$80.00</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/*=======  End of single best seller product  =======*/}
            </div>
            {/*=======  End of best seller slider container  =======*/}
          </div>
        </div>
      </div>
    </div>
  )
}

}

export default BestSeller;
