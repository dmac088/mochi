import React, { useState, useRef } from "react";
import Accordion from "./Accordian";
import { Transition } from 'react-transition-group';
import { Link } from "react-router-dom";
import { slide } from '../../Helpers/Animation/Slide';
import { getBagPath } from '../../Helpers/Route/Route';
import { withRouter } from 'react-router-dom';

function BagMenuBase(props) {

  const { match } = props;

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
      <Link to={getBagPath(match)}>
        <div className="cart-icon d-inline-block">
          <span className="icon_bag_alt" />
        </div>
        <div className="cart-info d-inline-block">
          <p>Shopping Cart
						<span>0 items - $0.00</span>
          </p>
        </div>
        </Link>
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

const BagMenu = withRouter(function({...props}) {
  return <BagMenuBase 
              {...props}/>
});

export default BagMenu;
