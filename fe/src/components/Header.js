
import React from 'react';
import Greeting from './Greeting';
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
           </Nav>
           <Nav>
             <div
               className="cart-icon">
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
           </Nav>
           <Nav>
             <LanguageSelector
               changeLang={props.changeLang}
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
