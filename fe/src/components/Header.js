
import React from 'react';
import Greeting from './Greeting';
import HeaderCartSummary from './HeaderCartSummary';
import CartMenu from './CartMenu';
import Selector from './Selector';
import Search from './Search';
import LanguageSelector from './LanguageSelector';
import { Link } from 'react-router-dom';
import { Navbar, Nav ,NavItem} from 'react-bootstrap';


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
      <Navbar.Collapse>
        <Nav>
          <Search
            updateSearch={props.updateSearch}
            handleSearch={props.handleSearch}
            lang={props.lang}
            />
        </Nav>
        <Nav pullRight>
          <Nav>
            <HeaderCartSummary/>
          </Nav>
          <LanguageSelector
            changeLang={props.changeLang}
            lang={props.lang}
            />
          <Nav>
            <CartMenu
              cartBounce={props.cartBounce}
              totalItems={props.totalItems}
              />
          </Nav>
          <Nav>
            <Greeting
              authenticated={props.authenticated}
              customer={props.customer}
              />
          </Nav>
          <Nav>
            <Selector
              authenticated={props.authenticated}
              customer={props.customer}
              />
          </Nav>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
    );
}

export default Header;
