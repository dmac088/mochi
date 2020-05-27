import React, { useState } from 'react';
import { Transition } from 'react-transition-group'
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import CategoryMenuItem from './CategoryMenuItem/CategoryMenuItem';

function CategoryMenu(props) {
 
    let container = null;

    const setScope = (c) => {
         container = c;
    }

    const slide = (container, direction, params = { duration: 500}) => {
        const element = ReactDOM.findDOMNode(container);
        if(element === undefined) { return; }
        Velocity(element, direction, params);
    }

    return (
        <Transition in={props.in} 
                    timeout={2000}
                    onEnter={() => { console.log('enter') }}
                    onEntering={() => { console.log('entering');
                                        slide(container, 'slideDown', null); }}
                    onEntered={() => { console.log(' entered') }}
                    onExit={() => { console.log(' exit') }}
                    onExiting={() => {  console.log('exiting');
                                        slide(container, 'slideUp', null); 
                                        }}
                    onExited={() => { console.log(' exited') }}>
        <ul ref={setScope}>
            <li><a href="#">Vegatables</a></li>
            <li className="menu-item-has-children"><a href="#">Salad</a>
                <ul className="category-mega-menu">
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Vegetables</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruits"} />
                            <CategoryMenuItem categoryDesc={"Peanuts"} />
                        </ul>
                    </li>
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Fast Foods</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruits"} />
                            <CategoryMenuItem categoryDesc={"Peanuts"} />
                        </ul>
                    </li>
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Salad</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruits"} />
                            <CategoryMenuItem categoryDesc={"Peanuts"} />
                        </ul>
                    </li>
                </ul>

            </li>
            <li className="menu-item-has-children"><a href="#">Fast Foods</a>


                <ul className="category-mega-menu">
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Vegetables</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruits"} />
                            <CategoryMenuItem categoryDesc={"Peanuts"} />
                        </ul>
                    </li>
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Fast Foods</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruit"} />
                            <CategoryMenuItem categoryDesc={"Butter"} />
                        </ul>
                    </li>
                    <li className="menu-item-has-children">
                        <a className="megamenu-head" href="#">Salad</a>
                        <ul>
                            <CategoryMenuItem categoryDesc={"Salad"} />
                            <CategoryMenuItem categoryDesc={"Fast Food"} />
                            <CategoryMenuItem categoryDesc={"Fruits"} />
                            <CategoryMenuItem categoryDesc={"Peanuts"} />
                        </ul>
                    </li>
                </ul>

            </li>
            <li><a href="#">Beans</a></li>
            <li><a href="#">Bread</a></li>
            <li><a href="#">Fish &amp; Meats</a></li>
            <li><a href="#">Peanuts</a></li>
            <li><a href="#">Birds</a></li>
            <li className="hidden" ><a href="#">Eggs</a></li>
            <li className="hidden" ><a href="#">Fruits</a></li>
            <li><a href="#" id="more-btn"><span className="icon_plus_alt2"></span> More Categories</a></li>
        </ul>
        </Transition>
    );
}

export default CategoryMenu;