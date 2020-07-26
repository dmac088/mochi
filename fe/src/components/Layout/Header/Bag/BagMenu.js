import React, { useState, useRef } from "react";
import Accordion from "./Accordian";
import { Transition } from 'react-transition-group';
import { Link } from "react-router-dom";
import { slide } from '../../Helpers/Animation/Slide';
import { getBagPath } from '../../Helpers/Route/Route';
import { localization } from '../../Localization/Localization';
import { useSelector } from 'react-redux';

function BagMenu(props) {

  const { match } = props;
  const { lang } = match.params;

  const bag = useSelector(state => state.bag);

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
          <p>{localization[lang]['mybag']}
          <span>{bag.totalItems} {localization[lang]['items']} - ${bag.totalAmount}</span>
          </p>
        </div>
        </Link>
      <Transition 
        in={visible}
        timeout={0}
        onEntering={() => { slide(container, 'slideDown', { duration: 500 }); }}
        onExiting={() => { slide(container, 'slideUp', { duration: 500 }); }}>
          <div className="cart-floating-box" id="cart-floating-box" ref={setContainer}>
            <Accordion 
              {...props}
              bag={bag}/>
          </div>
      </Transition>
    </div>
  );
}

export default BagMenu;
