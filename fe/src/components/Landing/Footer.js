


import React, { Component } from 'react';

class Footer extends Component {

  render() {
    return (
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
    )
  }
}

export default Footer;
