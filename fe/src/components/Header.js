
import React from 'react';
import Greeting from './Greeting';
import Selector from './Selector';
import Search from './Search';
import LanguageSelector from './LanguageSelector';
import { Link } from 'react-router-dom';
import { Navbar, Nav, NavItem, MenuItem, NavDropdown } from 'react-bootstrap';


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
          <Nav>
              <Search
                handleSearch={props.handleSearch}
                lang={props.lang}
              />
              <div className="cart-info">
                <table>
                  <tbody>
                    <tr>
                      <td>
                        No. of items
                      </td>
                      <td>:</td>
                      <td>
                        <strong>
                          {props.totalItems}
                        </strong>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        Sub Total
                      </td>
                      <td>:</td>
                      <td>
                        <strong>
                          {props.total}
                        </strong>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div
                className="cart-icon"
                href="#">
                <img
                  className={props.cartBounce ? "tada" : " "}
                  src="https://res.cloudinary.com/sivadass/image/upload/v1493548928/icons/bag.png"
                  alt="Cart"
                  />
                {props.totalItems ? (
                  <span className="cart-count">
                    {props.totalItems}
                  </span>
                ) : (
                  ""
                )}
              </div>
              <Selector
                authenticated={props.authenticated}
                customer={props.customer}
                />
          </Nav>
        </Navbar>
    );
}

export default Header;
