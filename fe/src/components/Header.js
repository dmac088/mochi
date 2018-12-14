
import React from 'react';
import Greeting from './Greeting';
import Selector from './Selector';
import Search from './Search';
import { Link } from 'react-router-dom';


const Header = (props) => {
      return(
      <nav className="navbar navbar-light bg-light justify-content-between">
        <div className="navbar-brand">
            <Link to="/">HOME</Link>
        </div>
        <div className="form-inline">
            <Search
              handleSearch={props.handleSearch}
            />
                        <div className="cart-info">
                          <table>
                            <tbody>
                              <tr>
                                <td>No. of items</td>
                                <td>:</td>
                                <td>
                                  <strong>{props.totalItems}</strong>
                                </td>
                              </tr>
                              <tr>
                                <td>Sub Total</td>
                                <td>:</td>
                                <td>
                                  <strong>{props.total}</strong>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </div>

            <Greeting
              authenticated={props.authenticated}
              customer={props.customer}
            />
            <Selector
              authenticated={props.authenticated}
              customer={props.customer}
            />
        </div>
      </nav>
    );
}

export default Header;
