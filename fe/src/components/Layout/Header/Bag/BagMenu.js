import React, { useState, useRef } from "react";
import Accordion from "./Accordian";
import { Transition } from 'react-transition-group';
import { slide } from '../../Helpers/Animation/Slide';

function BagMenu() {

  const [stateInContainer, setInContainer] = useState(false);

  const visibleRef = useRef(stateInContainer);
  visibleRef.current = stateInContainer;

  const [visible, setVisible] = useState(false);

  const setNotIn = () => {
    setInContainer(false);
    getVisible();
  }

  const setIn = () => {
    setInContainer(true);
    getVisible();
  }

  const getVisible = () => {
    setTimeout(() => {  
      setVisible(visibleRef.current);
    }, 500);
  }
  
  let container = null;

  const setContainer = (c) => {
      container = c;
  }

  return (
    <div 
        onMouseEnter={setIn}
        onMouseLeave={setNotIn}
        className="shopping-cart"
        id="shopping-cart">
      <a href="#">
        <div className="cart-icon d-inline-block">
          <span className="icon_bag_alt" />
        </div>
        <div className="cart-info d-inline-block">
          <p>Shopping Cart
						<span>0 items - $0.00</span>
          </p>
        </div>
      </a>
      <Transition 
        in={visible}
        timeout={0}
        onEntering={() => { slide(container, 'slideDown', { duration: 500 }); }}
        onExiting={() => { slide(container, 'slideUp', { duration: 500 }); }}>
          <div className="cart-floating-box" id="cart-floating-box" ref={setContainer}>
            <Accordion />
          </div>
      </Transition>
    </div>
  );
}

export default BagMenu;
