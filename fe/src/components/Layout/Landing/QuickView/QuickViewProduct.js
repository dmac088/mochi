import React from 'react';
import Slider from "react-slick";

function QuickViewProduct(props) {

  const { toggleQuickView, product } = props;

  const closeModal = (e) => {
    toggleQuickView(e);
  }

  const changeImage = () => {
    console.log('changeImage');
  }

  const currentImage = '';
  const settings = {};

  return (
    <div className={"modal fade quick-view-modal-container show"}
      id={"modal-" + product.data.productUPC}
      tabIndex="-1"
      role="dialog"
      style={{
          "display": "block",
          "paddingRight": "17px"
        }}
      aria-hidden={"false"}>
      <div className="modal-dialog modal-dialog-centered" role="document">
        <div className="modal-content">
          <div className="modal-header">
            <button onClick={closeModal} type="button" className="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div className="modal-body">
            <div className="row">
              <div className="col-lg-5 col-md-6 col-xs-12">
                <div className="product-image-slider">
                  <div className="tab-content product-large-image-list" id="myTabContent">
                    <div className="tab-pane fade show active" id="single-slide1" role="tabpanel" aria-labelledby="single-slide-tab-1">
                      <div className="single-product-img img-full">
                        <img src={currentImage}
                          className="img-fluid"
                          alt=""
                        />
                      </div>
                    </div>
                    <div className="tab-pane fade" id="single-slide2" role="tabpanel" aria-labelledby="single-slide-tab-2">
                      <div className="single-product-img img-full">
                        <img src={product.data.productImage}
                          className="img-fluid"
                          alt=""
                        />
                      </div>
                    </div>
                    <div className="tab-pane fade" id="single-slide3" role="tabpanel" aria-labelledby="single-slide-tab-3">
                      <div className="single-product-img img-full">
                        <img src="assets/images/products/grapes.jpg"
                          className="img-fluid"
                          alt=""
                        />
                      </div>
                    </div>
                    <div className="tab-pane fade" id="single-slide4" role="tabpanel" aria-labelledby="single-slide-tab-4">
                      <div className="single-product-img img-full">
                        <img src="assets/images/products/pistachio.jpg"
                          className="img-fluid"
                          alt=""
                        />
                      </div>
                    </div>
                  </div>
                  <div className="product-small-image-list">
                    <Slider role="tablist" className="nav small-image-slider"  {...settings}>
                      <div className="single-small-image img-full">
                        <a onClick={changeImage} data-toggle="tab" id="single-slide-tab-1" href="#single-slide1">
                          <img src={product.data.productImage}
                            className="img-fluid"
                            alt="" />
                        </a>
                      </div>
                      <div className="single-small-image img-full">
                        <a onClick={changeImage} data-toggle="tab" id="single-slide-tab-2" href="#single-slide2">
                          <img src="assets/images/products/grapes.jpg"
                            className="img-fluid"
                            alt="" />
                        </a>
                      </div>
                      <div className="single-small-image img-full">
                        <a onClick={changeImage} data-toggle="tab" id="single-slide-tab-3" href="#single-slide3">
                          <img src={product.data.productImage}
                            className="img-fluid"
                            alt="" />
                        </a>
                      </div>
                      <div className="single-small-image img-full">
                        <a onClick={changeImage} data-toggle="tab" id="single-slide-tab-4" href="#single-slide4">
                          <img src={product.data.productImage}
                            alt="" />
                        </a>
                      </div>
                      <div className="single-small-image img-full">
                        <a onClick={changeImage} data-toggle="tab" id="single-slide-tab-2" href="#single-slide2">
                          <img src="assets/images/products/pistachio.jpg"
                            className="img-fluid"
                            alt="" />
                        </a>
                      </div>
                    </Slider>
                  </div>
                </div>
              </div>
              <div className="col-lg-7 col-md-6 col-xs-12">
                <div className="product-feature-details">
                  <h2 className="product-title mb-15">{product.data.productDesc}</h2>
                  <h2 className="product-price mb-15">
                    <span className="main-price">${product.data.productRetail}</span>
                    <span className="discounted-price"> ${product.data.productMarkdown}</span>
                  </h2>
                  <p className="product-description mb-20">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus</p>
                  <div className="cart-buttons mb-20">
                    <div className="pro-qty mr-10">
                      <input onChange={() => console.log('updateQuantity')} type="text" value={1} />
                      <a onClick={() => console.log('incrementQuantity')} href="#" className="inc qty-btn">+</a>
                      <a onClick={() => console.log('decrementQuantity')} href="#" className="dec qty-btn">-</a>
                    </div>
                    <div className="add-to-cart-btn">
                      <a onClick={() => console.log('addToBag')} href="#"><i className="fa fa-shopping-cart"></i> Add to Bag</a>
                    </div>
                  </div>
                  <div className="social-share-buttons">
                    <h3>share this product</h3>
                    <ul>
                      <li><a className="twitter" href="#"><i className="fa fa-twitter"></i></a></li>
                      <li><a className="facebook" href="#"><i className="fa fa-facebook"></i></a></li>
                      <li><a className="google-plus" href="#"><i className="fa fa-google-plus"></i></a></li>
                      <li><a className="pinterest" href="#"><i className="fa fa-pinterest"></i></a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}


export default QuickViewProduct;
