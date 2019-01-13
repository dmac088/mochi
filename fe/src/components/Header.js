
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
            <Navbar.Toggle />
          </Navbar.Header>

          <Nav>
            <Navbar.Form>
              <Search
                  handleSearch={props.handleSearch}
                  lang={props.lang}
              />
            </Navbar.Form>
          </Nav>
          <Nav>
            <HeaderCartSummary
                    totalItems={props.totalItems}
                    total={props.total}
            />
          </Nav>
          <Nav>
            <LanguageSelector
               changeLang={props.changeLang}
               currentLang={props.currentLang}
            />
          </Nav>
          <Nav>
            <CartMenu
               cartBounce={props.cartBounce}
               totalItems={props.totalItems}
             />
          </Nav>
          <Navbar.Text>
             <Greeting
               authenticated={props.authenticated}
               customer={props.customer}
               />
           </Navbar.Text>
          <Nav>
             <Selector
               authenticated={props.authenticated}
               customer={props.customer}
               />
          </Nav>
        </Navbar>
    );
}

export default Header;
