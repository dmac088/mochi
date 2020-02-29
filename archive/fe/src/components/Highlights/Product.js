import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import * as cartService from '../../services/cart';
import * as cartSelector from '../../services/cart/selectors';
import {
	spinner,
} from '../../services/helpers/uiHelper';
import { routeSingleProduct, createRouteProps } from '../../services/helpers/routeHelper';
import { productImagePath } from '../../services/helpers/imageHelper';

class Product extends Component {

  constructor(props){
		super(props);
    this.state = {
			"product": {},
			"quantity": 1,
      "currentImage": "",
      "isLoading": false,
    }
	}

  componentDidMount() {
    const { product } = this.props;
    this.setState({
    	"product": product,
      "currentImage": productImagePath + product.productImage,
      "isLoading": false,
    });
  }

  addToCart = (e) => {
    e.preventDefault();
    const { product, quantity } = this.state;
    product.quantity = quantity;
    cartService.addToCart(cartSelector.get(),
                          product,
                          ()=>{console.log("addToCart complete!")});
  }

  renderProduct = (category, product, currentImage, setCurrentProductId) => {

		const routeProps = createRouteProps(this.props.history, this.props.match, this.props.location);
    return (
      <React.Fragment>
        <div className="image">
          <a id={product.productUPC} onClick={(e) => routeSingleProduct(e, category.categoryDesc, routeProps)} href="#">
            <span className="onsale">Sale!</span>
            <img src={productImagePath + currentImage} className="img-fluid" alt="" />
          </a>
          <div className="product-hover-icons">
            <a onClick={this.addToCart} className="active" href="#" data-tooltip="Add to cart"> <span className="icon_cart_alt" /></a>
            <a id={product.productUPC} onClick={setCurrentProductId} href="#" data-tooltip="Add to wishlist"> <span className="icon_heart_alt" /> </a>
            <a id={product.productUPC} onClick={setCurrentProductId} href="#" data-tooltip="Compare"> <span className="arrow_left-right_alt" /> </a>
            <a id={product.productUPC} onClick={setCurrentProductId} href="#" data-tooltip="Quick view" data-toggle="modal" data-target={"#modal-" + product.productUPC} >
              <span className="icon_search" />
            </a>
          </div>
        </div>
        <div className="product-content">
          <div className="product-categories">
            <a id={product.productUPC} href={(e) => routeSingleProduct(e, category.categoryDesc, routePropse, category.categoryDesc, routeProps)} href="#">{category.categoryDesc}</a>,<span> </span>
            <a id={product.productUPC} href={(e) => routeSingleProduct(e, category.categoryDesc, routeProps)} href="#">{product.brandDesc}</a>
          </div>
          <h3 className="product-title">
            <a id={product.productUPC} onClick={(e) => routeSingleProduct(e, category.categoryDesc, routeProps)} href="#">
              {product.productDesc}
            </a>
          </h3>
          <div className="price-box">
            <span className="main-price">${product.productRetail}</span>
            <span className="discounted-price">${product.productMarkdown}</span>
          </div>
        </div>
      </React.Fragment>
    );
  }

  render() {
    const { category, product, setCurrentProductId } = this.props;
    const { isLoading } = this.state;
    return (
        <div className="gf-product tab-slider-sub-product">
          {(isLoading) ? spinner() : this.renderProduct(category, product, product.productImage, setCurrentProductId)}
        </div>
    );
  }
}

export default withRouter(Product);
