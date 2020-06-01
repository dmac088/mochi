import React, { useState } from 'react';
import BrandMenu from './BrandMenu';
import BlogMenu from './BlogMenu';
import { Transition } from 'react-transition-group';

function MobileMenu(props) {

  const [stateObject, setObjectState] = useState({
    revealBrandMenu: false,
    revealBlogMenu: false,
  });

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
    <React.Fragment>
      <li className="active">
        <a href="#">HOME</a>
      </li>
      <li className="menu-item-has-children">
        
        <Transition
          in={stateObject.revealBrandMenu}
          timeout={0}>
          <BrandMenu />
        </Transition>
        
      </li>
      <li className="menu-item-has-children">
        <a href="#">BLOG</a>
        <Transition
          in={stateObject.revealBlogMenu}
          timeout={0}>
          <BlogMenu />
        </Transition>
        <a onClick={toggleBlogMenu}
          className="mean-expand"
          href="#"
          style={{ fontSize: 0 }}>
          {(stateObject.revealBlogMenu) ? "-" : "+"}
        </a>
      </li>
      <li className="mean-last"><a href="#">CONTACT</a></li>
    </React.Fragment>
  )
}

export default MobileMenu;
