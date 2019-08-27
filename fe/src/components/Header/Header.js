
import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Search from './Search';
import { WebMenu } from './WebMenu';
import MobileMenu from './MobileMenu';
import HeaderCartSummary from './HeaderCartSummary';
import { LanguageSelector } from './LanguageSelector';
import { CurrencySelector } from './CurrencySelector';
import { isMobile } from '../../services/helpers/uiHelper';
import { homeRouteString, routeContact, routeCheckout, routeAccount, routeWishlist, createRouteProps } from '../../services/helpers/routeHelper';
import { Link } from 'react-router-dom';

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

  render() {
    const { location, history, match, authenticated, brandCategoryList} = this.props;
    const routeProps = createRouteProps(history, match, location);
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
                    <li><a onClick={(e) => routeAccount(e, routeProps, authenticated)} href="#">My Account</a></li>
                    <li><a onClick={(e) => routeWishlist(e, routeProps)} href="#">Wishlist</a></li>
                    <li><a onClick={(e) => routeCheckout(e, routeProps)} href="#">Checkout</a></li>
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
                  <Link to={homeRouteString(routeProps.match)} >
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
                  <HeaderCartSummary/>
                </div>
                {(this.state.renderMobile) ?
                  <MobileMenu /> :
                  <WebMenu
                    brandCategoryList={brandCategoryList}
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

export default withRouter(Header);
