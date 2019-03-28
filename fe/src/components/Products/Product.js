import React, { Component } from 'react';

class Product extends Component {

  routeSingleProduct = (e) => {
    e.preventDefault();
    console.log(this.props);
    const { locale, currency, term } = this.props.match.params;
    const type = this.props.match.params[0];
    this.props.history.push('/' + locale + '/' + currency + '/' + type + '/' + term + '/product/' + e.currentTarget.id);
  }


  renderLV = (product, setCurrentProductId, isGrid) => {
    return (
      <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
        <div className="gf-product shop-list-view-product">
          <div className="image">
            <a onClick={this.routeSingleProduct} id={product.productId} href="#">
              <span className="onsale">Sale!</span>
              <img src={product.productImage} className="img-fluid" alt />
            </a>
            <div className="product-hover-icons">
              <a id={product.productId}
                 onClick={setCurrentProductId}
                 href="#"
                 data-tooltip="Quick view"
                 data-toggle="modal"
                 data-target="#quick-view-modal-container">
                 <span className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="single-product.html">{product.productDesc}</a></h3>
            <div className="price-box mb-20">
              <span className="main-price">{product.productRrp}</span>
              <span className="discounted-price">$80.00</span>
            </div>
            <p className="product-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere esse tempora magnam dolorem tenetur eos eligendi non temporibus qui enim. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam, magni.</p>
            <div className="list-product-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            </div>
          </div>
        </div>
      </div>
    );
  }


  renderGV = (product, setCurrentProductId, isGrid) => {
    return (
      <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12">
        <div className="gf-product shop-grid-view-product">
          <div className="image">
            <a onClick={this.routeSingleProduct} id={product.productId} href="#">
              <span className="onsale">Sale!</span>
              <img src={product.productImage} className="img-fluid" alt />
            </a>
            <div className="product-hover-icons">
              <a href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
              <a href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
              <a href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
              <a  id={product.productId}
                  onClick={setCurrentProductId}
                  href="#"
                  data-tooltip="Quick view"
                  data-toggle="modal"
                  data-target="#quick-view-modal-container"> <span className="icon_search" />
              </a>
            </div>
          </div>
          <div className="product-content">
            <div className="product-categories">
              <a href="shop-left-sidebar.html">Fast Foods</a>,
              <a href="shop-left-sidebar.html">Vegetables</a>
            </div>
            <h3 className="product-title"><a href="#">{product.productDesc}</a></h3>
            <div className="price-box">
              <span className="main-price">{product.productRrp}</span>
              <span className="discounted-price">$80.00</span>
            </div>
          </div>
        </div>
      </div>
    )
  }

  render() {
    const { product, setCurrentProductId, isGrid } = this.props;
    return (
      <React.Fragment>
        {
          ((isGrid)
          ? this.renderGV(product, setCurrentProductId, isGrid)
          : this.renderLV(product, setCurrentProductId, isGrid))
        }
      </React.Fragment>
    );
  }
}

export default Product;
