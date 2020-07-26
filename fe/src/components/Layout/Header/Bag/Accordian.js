import React from 'react';
import { Link } from "react-router-dom";
import { getCheckoutPath, getBagPath } from '../../Helpers/Route/Route'
import { localization } from '../../Localization/Localization';
import { useDispatch } from 'react-redux';
import * as bagService from '../../../../services/Bag/index';
const $ = window.$;

function Accordion(props) {
  const { match, bag } = props;
  const { lang } = match.params;

  const images = require.context('../../../../assets/images/products', true);
  const dispatch = useDispatch();

  const removeItem = (e) => {
    console.log('removeItem = ' + e.target.id);
    e.preventDefault();
    dispatch(bagService.removeItem(e.target.id));
  }
  
  const renderItems = (items) => {
    return items.map(item => {    
      return (
        <div className="cart-float-single-item d-flex">
          <span className="remove-item"><a onClick={removeItem} href="#"><i id={item.data.productUPC} className="fa fa-times"></i></a></span>
          <div className="cart-float-single-item-image">
            <a href="single-product.html"><img src={images(`./${item.data.productImage}`)} className="img-fluid" alt="" /></a>
          </div>
          <div className="cart-float-single-item-desc">
            <p className="product-title"> <a href="single-product.html">{item.data.productDesc} </a></p>
            <p className="price"><span className="count">{item.quantity}x</span> ${item.data.productMarkdown}</p>
          </div>
        </div>
      )
    });
  }

  return (
    <React.Fragment>
      <div className="cart-items">
        {renderItems(bag.items)}
      </div>
      <div className="cart-calculation">
        <div className="calculation-details">
          <p className="total">{localization[lang]['subtotal']}<span>${bag.totalAmount}</span></p>
        </div>
        <div className="floating-cart-btn text-center">
          <Link to={getCheckoutPath(match)}>
            {localization[lang]['checkout']}
          </Link>
          <Link to={getBagPath(match)}>
            {localization[lang]['viewbag']}
          </Link>
        </div>
      </div>
    </React.Fragment>
  )
}

export default Accordion;