
import React, { Component } from 'react';
import Greeting from './Greeting';
import Search from './Search';
import Menu from './Menu';
import MobileMenu from './MobileMenu';
import HeaderCartSummary from './HeaderCartSummary';
import LanguageSelector from './LanguageSelector';
import CurrencySelector from './CurrencySelector';
import { isMobile } from '../../services/helpers/Helper';
import { Link } from 'react-router-dom';
//const $ = window.$;
//
// const windows = $(window);
// const categoryToggle = $('.category-toggle');
// const categoryMenu = $('.category-menu');

class Header extends Component {

  constructor(props) {
    super(props);
    this.state = {
      theposition: 0,
      renderMobile: false,
    };
  }

  componentDidMount() {
    window.addEventListener('scroll', this.listenToScroll, { passive: true })
    window.addEventListener('resize', this.renderMenu , { passive: true });
    this.renderMenu();
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.listenToScroll, { passive: true })
    window.removeEventListener('resize', this.renderMenu , { passive: true });
  }

  renderMenu = () => {
    this.setState({
      renderMobile: isMobile(),
    });
  }

  listenToScroll = () => {
    let scroll = document.documentElement.scrollTop;
    this.setState({
      theposition: scroll,
    });
  }

  checkout = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/Checkout');
  }

  account = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    if (this.props.authenticated) {
      this.props.history.push('/' + locale + '/' + currency + '/Account');
    } else {
      this.props.history.push('/' + locale + '/' + currency + '/Auth');
    }
  }

  wishlist = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/Wishlist');
  }

  render() {
    const { locale, currency } = this.props.match.params;
    return(
      <header>
        <div className="header-top pt-10 pb-10 pt-lg-10 pb-lg-10 pt-md-10 pb-md-10">
          <div className="container">
            <div className="row">
              <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12 text-center text-sm-left">
                <div className="lang-currency-dropdown">
                  <ul>
                    <li> <a onClick={(e) => e.preventDefault()} href="#">Language <i className="fa fa-chevron-down" /></a>
                      <LanguageSelector/>
                    </li>
                    <li><a onClick={(e) => e.preventDefault()} href="#">Dollar <i className="fa fa-chevron-down" /></a>
                      <CurrencySelector/>
                    </li>
                  </ul>
                </div>
              </div>
              <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12  text-center text-sm-right">
                <div className="header-top-menu">
                  <ul>
                    <li><a onClick={this.account} href="#">My account</a></li>
                    <li><a onClick={this.wishlist} href="#">Wishlist</a></li>
                    <li><a onClick={this.checkout} href="#">Checkout</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className={
                          "header-bottom header-bottom-one header-sticky "
                          + ((this.state.theposition >= 300) ? "is-sticky" : "")
                       }>
          <div className="container">
            <div className="row">
              <div className="col-md-3 col-sm-12 col-xs-12 text-lg-left text-md-center text-sm-center">
                <div className="logo mt-15 mb-15">
                  <Link to={'/'+ locale + '/'+ currency} >
                    <img src="assets/images/logo.png" className="img-fluid" alt="" />
                  </Link>
                </div>
              </div>
              <div className="col-md-9 col-sm-12 col-xs-12">
                <div className={
                                "menubar-top justify-content-between align-items-center flex-sm-wrap flex-md-wrap flex-lg-nowrap mt-sm-15"
                                + ((this.state.theposition >= 300) ? " d-none" : " d-flex")
                              }>
                  <div className="header-contact d-flex">
                    <div className="phone-icon">
                      <img src="assets/images/icon-phone.png" className="img-fluid" alt="" />
                    </div>
                    <div className="phone-number">
                      Phone: <span className="number">1-888-123-456-89</span>
                    </div>
                  </div>
                  <Search/>
                  <HeaderCartSummary
                    match={this.props.match}
                    location={this.props.location}
                    history={this.props.history}
                  />
                </div>
                {(this.state.renderMobile) ?
                  <MobileMenu
                    match={this.props.match}
                    location={this.props.location}
                    history={this.props.history}
                  /> :
                  <Menu
                    match={this.props.match}
                    location={this.props.location}
                    history={this.props.history}
                  />
                }
              </div>
              <div className="col-12">
                <div className="mobile-menu d-block d-lg-none" />
              </div>
            </div>
          </div>
        </div>
      </header>
    );
  }
}

export default Header;
