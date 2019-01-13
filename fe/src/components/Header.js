
import React from 'react';
import Greeting from './Greeting';
import HeaderCartSummary from './HeaderCartSummary';
import CartMenu from './CartMenu';
import Selector from './Selector';
import Search from './Search';
import LanguageSelector from './LanguageSelector';
import { Link } from 'react-router-dom';
import { Navbar, Nav } from 'react-bootstrap';


const Header = (props) => {
      return(
        <Navbar>
          <Navbar.Header>
            <Navbar.Brand>
              <Link to="/">
                New Zealand Bee
              </Link>
            </Navbar.Brand>
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav>
              <Search
                handleSearch={props.handleSearch}
                lang={props.lang}
              />
           </Nav>
           <Nav>
            <HeaderCartSummary
                totalItems={props.totalItems}
                total={props.total}
            />
           </Nav>
           <Nav>

           </Nav>
           <Nav>
             <LanguageSelector
               changeLang={props.changeLang}
               />
           </Nav>
           <Nav>
             <CartMenu
               cartBounce={props.cartBounce}
               totalItems={props.totalItems}
             />
           </Nav>
           <Nav>
             <Selector
               authenticated={props.authenticated}
               customer={props.customer}
               />
           </Nav>
         </Navbar.Collapse>
        </Navbar>
    );
}

export default Header;
