import React, { useState } from "react";
import Accordion from "./Accordian";
import { Transition } from 'react-transition-group';
import { slide } from '../../Helpers/Animation/Slide';

function BagMenu() {

  const [stateObject, setObjectState] = useState({
    visible: false,
    inContainer: false,
  });

  const setinContainer = () => {
    setObjectState((prevState) => ({
      inContainer: true,
      visible: true,
    }));
  }

  const setNotinContainer = () => {
    setObjectState((prevState) => ({ inContainer: false, }));
    setTimeout(() => {
      if (!(stateObject.inContainer)) {
        setObjectState((prevState) => ({
          visible: false,
        }));
      }
    }, 500);
  }
  
  let container = null;

  const setContainer = (c) => {
      container = c;
  }

  return (
    <div onMouseEnter={setinContainer}
         onMouseLeave={setNotinContainer}
      className="shopping-cart"
      id="shopping-cart">
      <a href="#">
        <div className="cart-icon d-inline-block">
          <span className="icon_bag_alt" />
        </div>
        <div className="cart-info d-inline-block">
          <p>Shopping Cart
												<span>
              0 items - $0.00
												</span>
          </p>
        </div>
      </a>
      <Transition 
        in={stateObject.visible}
        timeout={2000}
        onEntering={() => { slide(container, 'slideDown', 1000); }}
        onExiting={() => { slide(container, 'slideUp', 1000); }}>
          <div className="cart-floating-box" id="cart-floating-box" ref={setContainer}>
            <Accordion />
          </div>
      </Transition>
    </div>
  );
}

export default BagMenu;

// function CartMenu() {
//   return (
//     <div className="shopping-cart" id="shopping-cart">
//       <a href="cart.html">
//         <div className="cart-icon d-inline-block">
//           <span className="icon_bag_alt"></span>
//         </div>
//         <div className="cart-info d-inline-block">
//           <p>Shopping Cart
// 												<span>
//               0 items - $0.00
// 												</span>
//           </p>
//         </div>
//       </a>
//       {/*<!-- end of shopping cart -->*/}

//       {/*<!-- cart floating box -->*/}
//       <div className="cart-floating-box" id="cart-floating-box">
//         <div className="cart-items">
//           <div className="cart-float-single-item d-flex">
//             <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
//             <div className="cart-float-single-item-image">
//               <a href="single-product.html"><img src="assets/images/products/product01.jpg" className="img-fluid" alt="" /></a>
//             </div>
//             <div className="cart-float-single-item-desc">
//               <p className="product-title"> <a href="single-product.html">Duis pulvinar obortis eleifend </a></p>
//               <p className="price"><span className="count">1x</span> $20.50</p>
//             </div>
//           </div>
//           <div className="cart-float-single-item d-flex">
//             <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
//             <div className="cart-float-single-item-image">
//               <a href="single-product.html"><img src="assets/images/products/product02.jpg" className="img-fluid" alt="" /></a>
//             </div>
//             <div className="cart-float-single-item-desc">
//               <p className="product-title"> <a href="single-product.html">Fusce ultricies dolor vitae</a></p>
//               <p className="price"><span className="count">1x</span> $20.50</p>
//             </div>
//           </div>
//         </div>
//         <div className="cart-calculation">
//           <div className="calculation-details">
//             <p className="total">Subtotal <span>$22</span></p>
//           </div>
//           <div className="floating-cart-btn text-center">
//             <a href="checkout.html">Checkout</a>
//             <a href="cart.html">View Cart</a>
//           </div>
//         </div>
//       </div>
//       {/*<!-- end of cart floating box -->*/}
//     </div>
//   );
// }

