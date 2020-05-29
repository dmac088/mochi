import React, { useState } from 'react';
import { Transition } from 'react-transition-group';

function MobileMenu() {

  const [stateObject, setObjectState] = useState({
    revealMenu: false,
    revealBrandMenu: false,
    revealBlogMenu: false,
  });

  const toggleMobileMenu = (e) => {
    e.preventDefault();
    setObjectState({
      revealMenu: !stateObject.revealMenu,
    });
  }

  const toggleBrandMenu = (e) => {
    e.preventDefault();
    setObjectState({
      revealBrandMenu: !stateObject.revealBrandMenu,
    });
  }

  const toggleBlogMenu = (e) => {
    e.preventDefault();
    setObjectState({
      revealBlogMenu: !stateObject.revealBlogMenu,
    });
  }

  return (
      <div className="mobile-menu d-block d-lg-none mean-container">
        <div className="mean-bar">
          <a  onClick={toggleMobileMenu}
              href="#nav"
              className={"meanmenu-reveal " + ((stateObject.revealMenu) ? "meanclose" : "")}
              style={{background: '',
                      color: '',
                      right: 0,
                      left: 'auto'}}>

          <span className={(stateObject.revealMenu) ? "menu-close" : "menu-bar"} />
          </a>
          <nav className="mean-nav">
            <ul style={(stateObject.revealMenu) ? {display: 'block'} : {display: 'none'}}>
              <li className="active">
                <a href="#">HOME</a>
              </li>
              <li className="menu-item-has-children">
                <a href="#">Brands</a>
                  <Transition
                        component={React.Fragment}>
                      {(stateObject.revealBrandMenu) ? <BrandMenu /> : null}
                  </Transition>
                <a  onClick={toggleBrandMenu}
                    className="mean-expand"
                    href="#"
                    style={{fontSize: 0}}>
                    {(stateObject.revealBrandMenu) ? "-" : "+" }
                </a>
              </li>
              <li className="menu-item-has-children">
                <a href="#">BLOG</a>
                  <Transition
                        component={React.Fragment}>
                      {(stateObject.revealBlogMenu) ? <BlogMenu /> : null}
                  </Transition>
                <a  onClick={toggleBlogMenu}
                    className="mean-expand"
                    href="#"
                    style={{fontSize: 0}}>
                    {(stateObject.revealBlogMenu) ? "-" : "+" }
                </a>
              </li>
              <li className="mean-last"><a href="#">CONTACT</a></li>
            </ul>
          </nav>
        </div>
      </div>
    )
}

export default MobileMenu;
