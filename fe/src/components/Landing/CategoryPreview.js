import React, { Component } from 'react';




class CategoryPreview extends Component {

  render() {
    return (
      <React.Fragment>
        <div className="slider slider-with-banner mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                {/*=======  blog slider section title  =======*/}
                <div className="section-title">
                  <h3>vegetable &amp; fruit</h3>
                </div>
                {/*=======  End of blog slider section title  =======*/}
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                {/*=======  banner slider wrapper  =======*/}
                <div className="banner-slider-wrapper">
                  <div className="row no-gutters">
                    <div className="col-lg-4 col-md-4 col-sm-12">
                      {/*=======  slider side banner  =======*/}
                      <div className="slider-side-banner">
                        <a href="shop-left-sidebar.html">
                          <img src="assets/images/banners/home4-category1-sidebar.jpg" className="img-fluid" alt="" />
                        </a>
                      </div>
                      {/*=======  End of slider side banner  =======*/}
                    </div>
                    <div className="col-lg-8 col-md-8 col-sm-12">
                      {/*=======  banner slider container  =======*/}
                      <BannerSlider />
                      {/*=======  End of banner slider container  =======*/}
                      <div className="row no-gutters">
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category1-banner1.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category1-banner2.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {/*=======  End of banner slider wrapper =======*/}
              </div>
            </div>
          </div>
        </div>
        {/*=====  End of Slider with banner ======*/}
        {/*=============================================
        =            Slider with banner        =
        =============================================*/}
        <div className="slider slider-with-banner mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                {/*=======  blog slider section title  =======*/}
                <div className="section-title red-title">
                  <h3>fist &amp; meals</h3>
                </div>
                {/*=======  End of blog slider section title  =======*/}
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                {/*=======  banner slider wrapper  =======*/}
                <div className="banner-slider-wrapper">
                  <div className="row no-gutters">
                    <div className="col-lg-4 col-md-4 col-sm-12">
                      {/*=======  slider side banner  =======*/}
                      <div className="slider-side-banner">
                        <a href="shop-left-sidebar.html">
                          <img src="assets/images/banners/home4-category2-sidebar.jpg" className="img-fluid" alt="" />
                        </a>
                      </div>
                      {/*=======  End of slider side banner  =======*/}
                    </div>
                    <div className="col-lg-8 col-md-8 col-sm-12">
                      {/*=======  banner slider container  =======*/}
                      <div className="banner-slider-container">
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                      </div>
                      {/*=======  End of banner slider container  =======*/}
                      <div className="row no-gutters">
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category2-banner1.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category2-banner2.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {/*=======  End of banner slider wrapper =======*/}
              </div>
            </div>
          </div>
        </div>
        {/*=====  End of Slider with banner ======*/}
        {/*=============================================
        =            Slider with banner        =
        =============================================*/}
        <div className="slider slider-with-banner mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                {/*=======  blog slider section title  =======*/}
                <div className="section-title yellow-title">
                  <h3>bread &amp; seeds</h3>
                </div>
                {/*=======  End of blog slider section title  =======*/}
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                {/*=======  banner slider wrapper  =======*/}
                <div className="banner-slider-wrapper">
                  <div className="row no-gutters">
                    <div className="col-lg-4 col-md-4 col-sm-12">
                      {/*=======  slider side banner  =======*/}
                      <div className="slider-side-banner">
                        <a href="shop-left-sidebar.html">
                          <img src="assets/images/banners/home4-category3-sidebar.jpg" className="img-fluid" alt="" />
                        </a>
                      </div>
                      {/*=======  End of slider side banner  =======*/}
                    </div>
                    <div className="col-lg-8 col-md-8 col-sm-12">
                      {/*=======  banner slider container  =======*/}
                      <div className="banner-slider-container">
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                      </div>
                      {/*=======  End of banner slider container  =======*/}
                      <div className="row no-gutters">
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category3-banner1.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category3-banner2.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {/*=======  End of banner slider wrapper =======*/}
              </div>
            </div>
          </div>
        </div>
        {/*=====  End of Slider with banner ======*/}
        {/*=============================================
        =            Slider with banner        =
        =============================================*/}
        <div className="slider slider-with-banner mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                {/*=======  blog slider section title  =======*/}
                <div className="section-title coffee-title">
                  <h3>coffee &amp; tea</h3>
                </div>
                {/*=======  End of blog slider section title  =======*/}
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                {/*=======  banner slider wrapper  =======*/}
                <div className="banner-slider-wrapper">
                  <div className="row no-gutters">
                    <div className="col-lg-4 col-md-4 col-sm-12">
                      {/*=======  slider side banner  =======*/}
                      <div className="slider-side-banner">
                        <a href="shop-left-sidebar.html">
                          <img src="assets/images/banners/home4-category4-sidebar.jpg" className="img-fluid" alt="" />
                        </a>
                      </div>
                      {/*=======  End of slider side banner  =======*/}
                    </div>
                    <div className="col-lg-8 col-md-8 col-sm-12">
                      {/*=======  banner slider container  =======*/}
                      <div className="banner-slider-container">
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
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
                        {/*=======  End of single banner slider product =======*/}
                        {/*=======  single banner slider product  =======*/}
                        <div className="gf-product  banner-slider-product">
                          <div className="image">
                            <a href="single-product.html">
                              <span className="onsale">Sale!</span>
                              <img src="assets/images/products/product05.jpg" className="img-fluid" alt="" />
                            </a>
                            <div className="product-hover-icons">
                              <a href="#" data-tooltip="Quick view" data-toggle="modal" data-target="#quick-view-modal-container"> <span className="icon_search" /> </a>
                            </div>
                          </div>
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
                        {/*=======  End of single banner slider product =======*/}
                      </div>
                      {/*=======  End of banner slider container  =======*/}
                      <div className="row no-gutters">
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category4-banner1.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6">
                          {/*=======  slider banner  =======*/}
                          <div className="slider-banner">
                            <a href="shop-left-sidebar.html">
                              <img src="assets/images/banners/home4-category4-banner2.jpg" className="img-fluid" alt="" />
                            </a>
                          </div>
                          {/*=======  End of slider banner  =======*/}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {/*=======  End of banner slider wrapper =======*/}
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    )
  }
}


export default CategoryPreview;
