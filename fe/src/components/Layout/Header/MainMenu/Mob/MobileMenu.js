import React, { useState } from 'react';
import BrandMenu from './BrandMenu';
// import BlogMenu from './BlogMenu';
import { Transition } from 'react-transition-group';
import { Link } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import { getContactPath } from "../../../Helpers/Route/Route";

function MobileMenuBase(props) {

  const { match } = props;

  const [stateObject, setObjectState] = useState({
    revealBrandMenu: false,
    revealBlogMenu: false,
  });

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
            <React.Fragment />  
          {/* <BlogMenu /> */}
        </Transition>
        <a onClick={toggleBlogMenu}
          className="mean-expand"
          href="#"
          style={{ fontSize: 0 }}>
          {(stateObject.revealBlogMenu) ? "-" : "+"}
        </a>
      </li>
      <li className="mean-last">
        <Link to={getContactPath(match)}>
        CONTACT
        </Link>
      </li>
    </React.Fragment>
  )
}

const MobileMenu = withRouter(function({...props}) {
  return <MobileMenuBase 
              {...props}/>
});

export default MobileMenu;
