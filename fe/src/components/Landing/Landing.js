import React, { Component } from 'react';
import HeroSlider from './HeroSlider';
import Policy from './Policy';
import TabSliderContiner from './TabSliderContiner';
import BannerSlider from './BannerSlider';
import BestSeller from './BestSeller';
import BlogPosts from './BlogPosts';
import BrandSlider from './BrandSlider';
import CategoryPreview from './CategoryPreview';
import ProductHighlights from './ProductHighlights';
import { withRouter } from 'react-router-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
const $ = window.$;



class Landing extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showScroller: false
    };
  }

  componentDidMount() {
    window.addEventListener('scroll', this.listenToScroll, { passive: true });
    this.animateScroll();
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.listenToScroll, { passive: true });
  }

  listenToScroll = () => {
    let scroll = document.documentElement.scrollTop;

    this.setState({
      theposition: scroll,
    })
  }

  animateScroll = (e) => {
    if(!(e === undefined)) {e.preventDefault()}
    $('.scroll-top').click(() => {
        $('html,body')
    			.velocity("scroll", { duration: 1000 });
    });
  }


  render() {
    let scrollTop = ((this.state.theposition >= 400) ? <a onClick={this.animateScroll}
                                                          href="#"
                                                          className="scroll-top fadeIn"
                                                      /> :
                                                      <a  href="#"
                                                          className="scroll-top fadeOut"
                                                      />);

    return(
      <div>
        <HeroSlider
          match={this.props.match}
          categoryList={this.props.categoryList}
        />
        <Policy />
        <ProductHighlights />
        {/*=====  End of Tab slider  ======*/}
        {/*=============================================
	=            Double banner          =
	=============================================*/}
        <div className="double-banner-section mb-35">
          <div className="container">
            <div className="row">
              <div className="col-lg-6 col-md-6 col-sm-12 mb-xs-35">
                {/*=======  single banner  =======*/}
                <div className="single-banner">
                  <a href="shop-left-sidebar.html">
                    <img src="assets/images/banners/home2-banner1-1.jpg" className="img-fluid" alt="" />
                  </a>
                </div>
                {/*=======  End of single banner  =======*/}
              </div>
              <div className="col-lg-6 col-md-6 col-sm-12">
                {/*=======  single banner  =======*/}
                <div className="single-banner">
                  <a href="shop-left-sidebar.html">
                    <img src="assets/images/banners/home2-banner1-2.jpg" className="img-fluid" alt="" />
                  </a>
                </div>
                {/*=======  End of single banner  =======*/}
              </div>
            </div>
          </div>
        </div>
        {/*=====  End of Double banner   ======*/}
        <CategoryPreview/ >
        <BestSeller />
        <BlogPosts />
        {/*=============================================
	=            Brand logo slider         =
	=============================================*/}
        <BrandSlider/>
        {/*=====  End of Brand logo slider  ======*/}
        {/*=============================================
	=            Footer         =
	=============================================*/}
        <footer>
          {/*=======  newsletter section  =======*/}
          <div className="newsletter-section pt-50 pb-50">
            <div className="container">
              <div className="row">
                <div className="col-lg-4 col-md-12 col-sm-12 mb-sm-20 mb-xs-20">
                  {/*=======  newsletter title =======*/}
                  <div className="newsletter-title">
                    <h1>
                      <img src="assets/images/icon-newsletter.png" alt="" />
                      Send Newsletter
                    </h1>
                  </div>
                  {/*=======  End of newsletter title  =======*/}
                </div>
                <div className="col-lg-8 col-md-12 col-sm-12">
                  {/*=======  subscription-form wrapper  =======*/}
                  <div className="subscription-form-wrapper d-flex flex-wrap flex-sm-nowrap">
                    <p className="mb-xs-20">Sign up for our newsletter to get up-to-date from us</p>
                    <div className="subscription-form">
                      <form id="mc-form" className="mc-form subscribe-form">
                        <input type="email" id="mc-email" autoComplete="off" placeholder="Your email address" />
                        <button id="mc-submit" type="submit"> subscribe!</button>
                      </form>
                      {/* mailchimp-alerts Start */}
                      <div className="mailchimp-alerts">
                        <div className="mailchimp-submitting" />{/* mailchimp-submitting end */}
                        <div className="mailchimp-success" />{/* mailchimp-success end */}
                        <div className="mailchimp-error" />{/* mailchimp-error end */}
                      </div>{/* mailchimp-alerts end */}
                    </div>
                  </div>
                  {/*=======  End of subscription-form wrapper  =======*/}
                </div>
              </div>
            </div>
          </div>
          {/*=======  End of newsletter section  =======*/}
          {/*=======  social contact section  =======*/}
          <div className="social-contact-section pt-50 pb-50">
            <div className="container">
              <div className="row">
                <div className="col-lg-4 col-md-12 order-2 order-md-2 order-sm-2 order-lg-1">
                  {/*=======  social media links  =======*/}
                  <div className="social-media-section">
                    <h2>Follow us</h2>
                    <div className="social-links">
                      <a className="facebook" href="http://www.facebook.com" data-tooltip="Facebook"><i className="fa fa-facebook" /></a>
                      <a className="twitter" href="http://www.twitter.com" data-tooltip="Twitter"><i className="fa fa-twitter" /></a>
                      <a className="instagram" href="http://www.instagram.com" data-tooltip="Instagram"><i className="fa fa-instagram" /></a>
                      <a className="linkedin" href="http://www.linkedin.com" data-tooltip="Linkedin"><i className="fa fa-linkedin" /></a>
                      <a className="rss" href="http://www.rss.com" data-tooltip="RSS"><i className="fa fa-rss" /></a>
                    </div>
                  </div>
                  {/*=======  End of social media links  =======*/}
                </div>
                <div className="col-lg-8 col-md-12 order-1 order-md-1 order-sm-1 order-lg-2  mb-sm-50 mb-xs-50">
                  {/*=======  contact summery  =======*/}
                  <div className="contact-summery">
                    <h2>Contact us</h2>
                    {/*=======  contact segments  =======*/}
                    <div className="contact-segments d-flex justify-content-between flex-wrap flex-lg-nowrap">
                      {/*=======  single contact  =======*/}
                      <div className="single-contact d-flex mb-xs-20">
                        <div className="icon">
                          <span className="icon_pin_alt" />
                        </div>
                        <div className="contact-info">
                          <p>Address: <span>123 New Design Str, Melbourne, Australia</span></p>
                        </div>
                      </div>
                      {/*=======  End of single contact  =======*/}
                      {/*=======  single contact  =======*/}
                      <div className="single-contact d-flex mb-xs-20">
                        <div className="icon">
                          <span className="icon_mobile" />
                        </div>
                        <div className="contact-info">
                          <p>Phone: <span>1-888-123-456-89</span></p>
                        </div>
                      </div>
                      {/*=======  End of single contact  =======*/}
                      {/*=======  single contact  =======*/}
                      <div className="single-contact d-flex">
                        <div className="icon">
                          <span className="icon_mail_alt" />
                        </div>
                        <div className="contact-info">
                          <p>Email: <span>support@hastech.company</span></p>
                        </div>
                      </div>
                      {/*=======  End of single contact  =======*/}
                    </div>
                    {/*=======  End of contact segments  =======*/}
                  </div>
                  {/*=======  End of contact summery  =======*/}
                </div>
              </div>
            </div>
          </div>
          {/*=======  End of social contact section  =======*/}
          {/*=======  footer navigation  =======*/}
          <div className="footer-navigation-section pt-40 pb-40">
            <div className="container">
              <div className="row">
                <div className="col-lg-3 col-md-3 col-sm-6 col-xs-12 mb-xs-30">
                  {/*=======  single navigation section  =======*/}
                  <div className="single-navigation-section">
                    <h3 className="nav-section-title">INFORMATION</h3>
                    <ul>
                      <li> <a href="about-us.html">About Us</a></li>
                      <li> <a href="#">Delivery Information</a></li>
                      <li> <a href="#">Privacy Policy</a></li>
                      <li> <a href="#">Terms &amp; Condition</a></li>
                    </ul>
                  </div>
                  {/*=======  End of single navigation section  =======*/}
                </div>
                <div className="col-lg-3 col-md-3 col-sm-6 col-xs-12 mb-xs-30">
                  {/*=======  single navigation section  =======*/}
                  <div className="single-navigation-section">
                    <h3 className="nav-section-title">MY ACCOUNT</h3>
                    <ul>
                      <li> <a href="my-account.html">My Account</a></li>
                      <li> <a href="wishlist.html">Wishlist</a></li>
                      <li> <a href="cart.html">Shopping Cart</a></li>
                      <li> <a href="#">Newsletter</a></li>
                    </ul>
                  </div>
                  {/*=======  End of single navigation section  =======*/}
                </div>
                <div className="col-lg-3 col-md-3 col-sm-6 col-xs-12 mb-xs-30">
                  {/*=======  single navigation section  =======*/}
                  <div className="single-navigation-section">
                    <h3 className="nav-section-title">CUSTOMER SERVICE</h3>
                    <ul>
                      <li> <a href="contact.html">Contact</a></li>
                      <li> <a href="#">OUR SERVICE</a></li>
                      <li> <a href="#">RETURNS</a></li>
                      <li> <a href="#">SITE MAP</a></li>
                    </ul>
                  </div>
                  {/*=======  End of single navigation section  =======*/}
                </div>
                <div className="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                  {/*=======  single navigation section  =======*/}
                  <div className="single-navigation-section">
                    <h3 className="nav-section-title">Extras</h3>
                    <ul>
                      <li> <a href="contact.html">BRANDS</a></li>
                      <li> <a href="#">GIFT VOUCHERS</a></li>
                      <li> <a href="#">AFFILIATES</a></li>
                      <li> <a href="#">SPECIALS</a></li>
                    </ul>
                  </div>
                  {/*=======  End of single navigation section  =======*/}
                </div>
              </div>
            </div>
          </div>
          {/*=======  End of footer navigation  =======*/}
          {/*=======  copyright section  =======*/}
          <div className="copyright-section pt-35 pb-35">
            <div className="container">
              <div className="row align-items-md-center align-items-sm-center">
                <div className="col-lg-4 col-md-6 col-sm-12 col-xs-12 text-center text-md-left">
                  {/*=======  copyright text	  =======*/}
                  <div className="copyright-segment">
                    <p>
                      <a href="#">Privacy Policy</a>
                      <span className="separator">|</span>
                      <a href="#">Term and conditions</a>
                    </p>
                    <p className="copyright-text">© 2018 <a href="/">Greenfarm</a>. All Rights Reserved</p>
                  </div>
                  {/*=======  End of copyright text	  =======*/}
                </div>
                <div className="col-lg-8 col-md-6 col-sm-12 col-xs-12">
                  {/*=======  payment info  =======*/}
                  <div className="payment-info text-center text-md-right">
                    <p>Allow payment base on <img src="assets/images/payment-icon.png" className="img-fluid" alt="" /></p>
                  </div>
                  {/*=======  End of payment info  =======*/}
                </div>
              </div>
            </div>
          </div>
          {/*=======  End of copyright section  =======*/}
        </footer>
        {/*=====  End of Footer  ======*/}
        {/*=============================================
	=            Quick view modal         =
	=============================================*/}
        <div className="modal fade quick-view-modal-container" id="quick-view-modal-container" tabIndex={-1} role="dialog" aria-hidden="true">
          <div className="modal-dialog modal-dialog-centered" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span>
                </button>
              </div>
              <div className="modal-body">
                <div className="row">
                  <div className="col-lg-5 col-md-6 col-xs-12">
                    {/* product quickview image gallery */}
                    <div className="product-image-slider">
                      {/*Modal Tab Content Start*/}
                      <div className="tab-content product-large-image-list" id="myTabContent">
                        <div className="tab-pane fade show active" id="single-slide1" role="tabpanel" aria-labelledby="single-slide-tab-1">
                          {/*Single Product Image Start*/}
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product01.jpg" className="img-fluid" alt="" />
                          </div>
                          {/*Single Product Image End*/}
                        </div>
                        <div className="tab-pane fade" id="single-slide2" role="tabpanel" aria-labelledby="single-slide-tab-2">
                          {/*Single Product Image Start*/}
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product02.jpg" className="img-fluid" alt="" />
                          </div>
                          {/*Single Product Image End*/}
                        </div>
                        <div className="tab-pane fade" id="single-slide3" role="tabpanel" aria-labelledby="single-slide-tab-3">
                          {/*Single Product Image Start*/}
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product03.jpg" className="img-fluid" alt="" />
                          </div>
                          {/*Single Product Image End*/}
                        </div>
                        <div className="tab-pane fade" id="single-slide4" role="tabpanel" aria-labelledby="single-slide-tab-4">
                          {/*Single Product Image Start*/}
                          <div className="single-product-img img-full">
                            <img src="assets/images/products/product04.jpg" className="img-fluid" alt="" />
                          </div>
                          {/*Single Product Image End*/}
                        </div>
                      </div>
                      {/*Modal Content End*/}
                      {/*Modal Tab Menu Start*/}
                      <div className="product-small-image-list">
                        <div className="nav small-image-slider" role="tablist">
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-1" href="#single-slide1"><img src="assets/images/products/product01.jpg" className="img-fluid" alt="" /></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-2" href="#single-slide2"><img src="assets/images/products/product02.jpg" className="img-fluid" alt="" /></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-3" href="#single-slide3"><img src="assets/images/products/product03.jpg" className="img-fluid" alt="" /></a>
                          </div>
                          <div className="single-small-image img-full">
                            <a data-toggle="tab" id="single-slide-tab-4" href="#single-slide4"><img src="assets/images/products/product04.jpg" alt="" /></a>
                          </div>
                        </div>
                      </div>
                      {/*Modal Tab Menu End*/}
                    </div>
                    {/* end of product quickview image gallery */}
                  </div>
                  <div className="col-lg-7 col-md-6 col-xs-12">
                    {/* product quick view description */}
                    <div className="product-feature-details">
                      <h2 className="product-title mb-15">Kaoreet lobortis sagittis laoreet</h2>
                      <h2 className="product-price mb-15">
                        <span className="main-price">$12.90</span>
                        <span className="discounted-price"> $10.00</span>
                      </h2>
                      <p className="product-description mb-20">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco,Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus</p>
                      <div className="cart-buttons mb-20">
                        <div className="pro-qty mr-10">
                          <input type="text" defaultValue={1} />
                        </div>
                        <div className="add-to-cart-btn">
                          <a href="#"><i className="fa fa-shopping-cart" /> Add to Cart</a>
                        </div>
                      </div>
                      <div className="social-share-buttons">
                        <h3>share this product</h3>
                        <ul>
                          <li><a className="twitter" href="#"><i className="fa fa-twitter" /></a></li>
                          <li><a className="facebook" href="#"><i className="fa fa-facebook" /></a></li>
                          <li><a className="google-plus" href="#"><i className="fa fa-google-plus" /></a></li>
                          <li><a className="pinterest" href="#"><i className="fa fa-pinterest" /></a></li>
                        </ul>
                      </div>
                    </div>
                    {/* end of product quick view description */}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/*=====  End of Quick view modal  ======*/}
        {/* scroll to top  */}
        <ReactTransitionGroup>
          {scrollTop}
        </ReactTransitionGroup>
      </div>
      );
  }
}

export default withRouter(Landing);
