import React, {Component} from 'react';
import {findDOMNode} from 'react-dom';
import Slider from "react-slick";
import * as cartSelector from '../../services/cart/selectors';
import * as cartService from '../../services/cart';
import {
	SlickArrowPrev,
	SlickArrowNext
} from './Helper';
import * as productApi from '../../data/products/api';

class QuickViewProduct extends Component{

	constructor(props){
		super(props);
    const { locale } = this.props;
    this.state = {
      "locale": locale,
      "productId": null,
			"product": {"productImage": 'assets/images/spinners/default.gif'},
			"currentImage": "assets/images/spinners/default.gif",
			"quantity": 1,
			"isShowing": false,
    }
	}

  componentDidUpdate() {
    const { locale, productId, showQVModal} = this.props;
		if(!productId) {return;}
    this.updateData(locale, productId, showQVModal);
  }

	updateState = () => {
		this.setState({
			"isAdded": true,
		}), () => {
								setTimeout(() => {
									this.setState({
										"isAdded": false,
									});
								}, 1000);
							}
	}

	closeModal = () => {
		this.setState({
			"product": {"productImage": 'assets/images/spinners/default.gif'},
			"currentImage": 'assets/images/spinners/default.gif',
			"quantity": 1,
			"isShowing": false,
		});
	}

	addToCart = (e) => {
		e.preventDefault();
		const { product, quantity } = this.state;
		product.quantity = quantity;
		cartService.addToCart(cartSelector.get(),
													product,
													this.updateState);
	}

	incrementQuantity = (e) => {
		e.preventDefault();
		this.setState((prevState) => ({
			 "quantity": prevState.quantity + 1
		}));
	}

	decrementQuantity = (e) => {
		e.preventDefault();
		this.setState((prevState) => ({
			 "quantity": prevState.quantity - 1
		}));
	}

	resetQuantity = () => {
		this.setState({
			"quantity": 1
		});
	}

	updateQuantity = (e) => {
		console.log(e.target.value);
	}

  getProduct = (locale, id) =>
    productApi.findById(locale, id)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .catch(()=>{
        console.log('getProducts failed!');
    });

  updateData = (locale = "en-GB", productId, isShowing, isMounting = 0) => {
    if(locale === this.state.locale
			&& productId === this.state.productId
			&& isMounting === 0) {return;}
		this.setState({
			"productId": productId,
			"locale": locale,
			"isShowing": isShowing,
		}, () => {
	    this.getProduct(locale, productId)
	    .then((responseJSON) => {
				setTimeout(() => {
					this.setState({
						"productId": productId,
						"product": responseJSON,
						"locale": locale,
						"currentImage": responseJSON.productImage,
					});
				}, 1000);
	    });
		});
  }

	next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

	changeImage = (e) => {
		e.preventDefault();
		this.setState({
			"currentImage": e.target.src,
		});
	}

  render(){
		const { product, currentImage, isShowing } = this.state;
		const settings = {
			prevArrow: <SlickArrowPrev />,
			nextArrow: <SlickArrowNext />,
			slidesToShow: 3,
			responsive: [{
				breakpoint: 1200,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				}
			},
			{
				breakpoint: 991,
				settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				}
			},
			{
				breakpoint: 767,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				}
			},
			{
				breakpoint: 480,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				}
			}
		]};
    return(
			<div className={"modal fade quick-view-modal-container "
									+ ((isShowing) ? " show" : "")}
				id={"modal-" + product.productId}
				tabIndex="-1"
				role="dialog"
				style={ (!isShowing)
							? {"display": "none"}
							: {"display": "block",
							 	 "paddingRight": "17px"}}
				aria-hidden={!isShowing}
				>
				<div className="modal-dialog modal-dialog-centered" role="document">
					<div className="modal-content">
						<div className="modal-header">
							<button onClick={this.closeModal} type="button" className="close" data-dismiss="modal" aria-label="Close">
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
													<img src={product.productImage}
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
													<img 	src="assets/images/products/pistachio.jpg"
																className="img-fluid"
																alt=""
													/>
												</div>
											</div>
										</div>
										<div className="product-small-image-list">
											<Slider role="tablist" className="nav small-image-slider" ref={c => (this.slider = c)} {...settings}>
												<div className="single-small-image img-full">
													<a onClick={this.changeImage} data-toggle="tab" id="single-slide-tab-1" href="#single-slide1">
														<img 	src={product.productImage}
																	className="img-fluid"
																	alt="" />
													</a>
												</div>
												<div className="single-small-image img-full">
													<a onClick={this.changeImage} data-toggle="tab" id="single-slide-tab-2" href="#single-slide2">
														<img 	src="assets/images/products/grapes.jpg"
																	className="img-fluid"
																	alt="" />
													</a>
												</div>
												<div className="single-small-image img-full">
													<a onClick={this.changeImage} data-toggle="tab" id="single-slide-tab-3" href="#single-slide3">
														<img 	src={product.productImage}
																	className="img-fluid"
																	alt="" />
													</a>
												</div>
												<div className="single-small-image img-full">
													<a onClick={this.changeImage} data-toggle="tab" id="single-slide-tab-4" href="#single-slide4">
														<img 	src={product.productImage}
																	alt="" />
													</a>
												</div>
												<div className="single-small-image img-full">
													<a onClick={this.changeImage} data-toggle="tab" id="single-slide-tab-2" href="#single-slide2">
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
										<h2 className="product-title mb-15">{product.productDesc}</h2>
										<h2 className="product-price mb-15">
											<span className="main-price">$ {product.productRrp}</span>
											<span className="discounted-price"> $10.00</span>
										</h2>
										<p className="product-description mb-20">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus</p>
										<div className="cart-buttons mb-20">
											<div className="pro-qty mr-10">
												<input onChange={this.updateQuantity} type="text" value={this.state.quantity} />
												<a onClick={this.incrementQuantity} href="#" className="inc qty-btn">+</a>
												<a onClick={this.decrementQuantity} href="#" className="dec qty-btn">-</a>
											</div>
											<div className="add-to-cart-btn">
												<a onClick={this.addToCart} href="#"><i className="fa fa-shopping-cart"></i> Add to Cart</a>
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
}

export default QuickViewProduct;
