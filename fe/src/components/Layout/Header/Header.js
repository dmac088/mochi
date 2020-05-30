import React, { useState, useEffect } from "react";
import WebMenu from './MainMenu/Web/WebMenu';
import MobileMenu from './MainMenu/Mob/MobileMenu';
import Search from './Search/Search';
import Language from './Language/Language';
import Currency from './Currency/Currency';
import MyAccount from './MyAccount';
import MyBag from './MyBag';
import MyWishList from './MyWishList';
import MyCheckout from './MyCheckout';
import BagMenu from './Bag/BagMenu';
import { isMobile } from '../Helpers/Mobile/Mobile';
import logo from '../../../assets/images/logo.png'
import iconPhone from '../../../assets/images/icon-phone.png';

function Header() {

  const [stateObject, setObjectState] = useState({
    scrollPosition: 0,
    isMobile: false,
  });

  const renderMenu = () => {
    if (stateObject.isMobile === isMobile()) { return; }
    setObjectState({
      isMobile: isMobile(),
    });
  }

  const listenToScroll = () => {
    let scroll = document.documentElement.scrollTop;
    if(stateObject.scrollPosition === scroll) { return; }
    setObjectState({
      scrollPosition: scroll,
    });
  }

  useEffect(() => {
    // initiate the event handler
    window.addEventListener('scroll', listenToScroll, { passive: true });
    window.addEventListener('resize', renderMenu , { passive: true });
    renderMenu();

    // this will clean up the event every time the component is re-rendered
    return function cleanup() {
        window.removeEventListener('scroll', listenToScroll, { passive: true });
        window.removeEventListener('resize', renderMenu, { passive: true });
    };
  });

  return (
    <header>
      {/*<!--=======  header top  =======-->*/}
      <div className="header-top pt-10 pb-10 pt-lg-10 pb-lg-10 pt-md-10 pb-md-10">
        <div className="container">
          <div className="row">
            <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12 text-center text-sm-left">
              {/*<!-- currncy language dropdown -->*/}
              <div className="lang-currency-dropdown">
                <ul>
                  <Language />
                  <Currency />
                </ul>
              </div>
              {/*<!-- end of currncy language dropdown -->*/}
            </div>
            <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12  text-center text-sm-right">
              {/*<!-- header top menu -->*/}
              <div className="header-top-menu">
                <ul>
                  <MyAccount />
                  <MyWishList />
                  <MyBag />
                  <MyCheckout />
                </ul>
              </div>
              {/*<!-- end of header top menu -->*/}
            </div>
          </div>
        </div>
      </div>
      {/*<!--=======  End of header top  =======-->*/}

      {/*<!--=======  header bottom  =======-->*/}
      <div className={"header-bottom header-bottom-one header-sticky"
                      + ((stateObject.theposition >= 300) ? "is-sticky" : "")}>
        <div className="container">
          <div className="row">
            <div className="col-md-3 col-sm-12 col-xs-12 text-lg-left text-md-center text-sm-center">
              {/*<!-- logo -->*/}
              <div className="logo mt-15 mb-15">
                <a href="index.html">
                  <img src={logo} className="img-fluid" alt="" />
                </a>
              </div>
              {/*<!-- end of logo -->*/}
            </div>
            <div className="col-md-9 col-sm-12 col-xs-12">
              <div className={"menubar-top d-flex justify-content-between align-items-center flex-sm-wrap flex-md-wrap flex-lg-nowrap mt-sm-15"
                              + ((stateObject.scrollPosition >= 300) ? " d-none" : " d-flex")}>
                {/*<!-- header phone number -->*/}
                <div className="header-contact d-flex">
                  <div className="phone-icon">
                    <img src={iconPhone} className="img-fluid" alt="" />
                  </div>
                  <div className="phone-number">
                    Phone: <span className="number">1-888-123-456-89</span>
                  </div>
                </div>
                {/*<!-- end of header phone number -->*/}
                <Search />
                {/*<!-- shopping cart -->*/}
                <BagMenu />
              </div>

              {/*<!-- navigation section -->*/}
              {(stateObject.isMobile) ? <MobileMenu /> : <WebMenu />}

              {/*<!-- end of navigation section -->*/}
            </div>
            <div className="col-12">
              {/*<!-- Mobile Menu -->*/}
              <div className="mobile-menu d-block d-lg-none"></div>
            </div>
          </div>
        </div>
      </div>
      {/*<!--=======  End of header bottom  =======-->*/}
    </header>
  );
}


export default Header;
