import React from 'react';
import product01 from '../../../../assets/images/products/product01.jpg';
import product02 from '../../../../assets/images/products/product02.jpg';
import { Link } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import { getCheckoutPath, getBagPath } from '../../Helpers/Route/Route'
import { localization } from '../../Localization/Localization';
const $ = window.$;

function AccordionBase(props) {
    const { match } = props;
    const { lang } = match.params;

    return (
        <React.Fragment>
            <div className="cart-items">
            <div className="cart-float-single-item d-flex">
             <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
             <div className="cart-float-single-item-image">
               <a href="single-product.html"><img src={product01} className="img-fluid" alt="" /></a>
             </div>
             <div className="cart-float-single-item-desc">
               <p className="product-title"> <a href="single-product.html">Duis pulvinar obortis eleifend </a></p>
               <p className="price"><span className="count">1x</span> $20.50</p>
             </div>
           </div>
           <div className="cart-float-single-item d-flex">
             <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
             <div className="cart-float-single-item-image">
               <a href="single-product.html"><img src={product02} className="img-fluid" alt="" /></a>
             </div>
             <div className="cart-float-single-item-desc">
               <p className="product-title"> <a href="single-product.html">Fusce ultricies dolor vitae</a></p>
               <p className="price"><span className="count">1x</span> $20.50</p>
             </div>
           </div>
         </div>
            <div className="cart-calculation">
                <div className="calculation-details">
                    <p className="total">{ localization[lang]['subtotal'] }<span>0</span></p>
                </div>
                <div className="floating-cart-btn text-center">
                    <Link to={getCheckoutPath(match)}>
                      { localization[lang]['checkout'] }
                    </Link>
                    <Link to={getBagPath(match)}>
                    { localization[lang]['viewbag'] }
                    </Link>
                </div>
            </div>
        </React.Fragment>
    )
}

const Accordion = withRouter(function({...props}) {
  return <AccordionBase 
              {...props}/>
});

export default Accordion;