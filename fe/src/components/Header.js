
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
          <Navbar.Form>
              <Search
                  handleSearch={props.handleSearch}
                  lang={props.lang}
              />
          </Navbar.Form>

          <Nav pullRight>
            <NavItem>
              <HeaderCartSummary
                      totalItems={props.totalItems}
                      total={props.total}
              />
            </NavItem>

            <LanguageSelector
               changeLang={props.changeLang}
               currentLang={props.currentLang}
            />

          <NavItem>
            <CartMenu
               cartBounce={props.cartBounce}
               totalItems={props.totalItems}
             />
         </NavItem>
            <NavItem>
             <Greeting
               authenticated={props.authenticated}
               customer={props.customer}
               />
           </NavItem>
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
