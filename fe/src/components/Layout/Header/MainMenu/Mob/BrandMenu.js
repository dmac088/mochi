import React, { useState } from 'react';
import { Transition } from 'react-transition-group';
import { slide } from '../../../Helpers/Animation/Slide';

function BrandMenu() {

    const [revealMenu, setRevealMenu] = useState(false);

    let container = null;
    const setContainer = (c) => {
        container = c;
    }

    const toggleMenu = (e) => {
        e.preventDefault();
        setRevealMenu(!revealMenu);
    }

    return (
        <React.Fragment>
            <a href="/#">Brands</a>
            <Transition
                in={revealMenu}
                timeout={2000}
                onEntering={() => { slide(container, 'slideDown', { display: "block" }); }}
                onExiting={() => { slide(container, 'slideUp', { display: "none" }); }}>
                <ul ref={setContainer} className="sub-menu mega-menu three-column" >
                    <li><a href="/#">Comvita</a></li>
                    <li><a href="/#">Airborne</a></li>
                    <li><a href="/#">Happy Bee</a></li>
                    <li><a href="/#">Antipodes</a></li>
                    <li><a href="/#">Wild Ferns</a></li>
                    <li><a href="/#">Trilogy</a></li>
                    <li><a href="/#">Anchor</a></li>
                </ul>
            </Transition>
            <a onClick={toggleMenu}
               className="mean-expand"
               href="/#"
               style={{ fontSize: 0 }}>
                {(revealMenu) ? "-" : "+"}
            </a>
        </React.Fragment>
    )
}

export default BrandMenu;   