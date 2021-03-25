import React, { useState, useRef, useEffect } from "react";
import Accordion from "./Accordian";
import { Transition } from 'react-transition-group';
import { Link } from "react-router-dom";
import { slide } from '../../Helpers/Animation/Slide';
import { getBagPath } from '../../Helpers/Route/Route';
import { localization } from '../../Localization/Localization';
import { useSelector, useDispatch } from 'react-redux';
import * as bagService from '../../../../services/Bag/index';
import { Spinner } from '../../Helpers/Animation/Spinner';


function BagMenu(props) {

  const { match } = props;
  const { lang } = match.params;

  const dispatch = useDispatch();

  const bag = useSelector(state => state.bag);
  const discovery = useSelector(state => state.discovery);
  const session = useSelector(state => state.session);

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

  const readyToGetBag = !discovery.loading && discovery.isDone && session.authenticated;
  const bagIsFetched = !bag.loading && bag.isDone;

  useEffect(() => {
    if(readyToGetBag) {
        dispatch(bagService.getBag());
    }
  }, [discovery.loading, discovery.isDone, session.authenticated]);
  
  let container = null;

  const setContainer = (c) => {
      container = c;
  }

  return (
    (!bagIsFetched)
    ? <Spinner />
    : 
    <React.Fragment>
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
          <span>{bag.bag.totalItems} {localization[lang]['items']} - ${bag.bag.totalAmount}</span>
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
    </React.Fragment>
  );
}

export default BagMenu;
