import React, { useState } from 'react';
import BrandMenu from './BrandMenu';
import BasicMenuItem from '../Web/BasicMenuItem';
import { Transition } from 'react-transition-group';
import { Link } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import { getContactPath, getHomePath } from '../../../Helpers/Route/Route';

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
        <BasicMenuItem 
          routePath={getHomePath(match)}
          descKey={'home'} />
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
        </Transition>
        <a onClick={toggleBlogMenu}
          className="mean-expand"
          href="#"
          style={{ fontSize: 0 }}>
          {(stateObject.revealBlogMenu) ? "-" : "+"}
        </a>
      </li>
      <li className="mean-last">
        <BasicMenuItem
          descKey={'contact'}
          routePath={getContactPath(props.match)} />
      </li>
    </React.Fragment>
  )
}

const MobileMenu = withRouter(function({...props}) {
  return <MobileMenuBase 
              {...props}/>
});

export default MobileMenu;
