import React, { useState, useEffect } from 'react';
import CategoryMenu from "./CategoryMenu";
import 'velocity-animate/velocity.ui';
import { isMobile } from '../../../Helpers/Mobile/Mobile';

function CategoryMenuContainer() {

    const [stateObject, setObjectState] = useState({
        menuVisible: true,
        isMobile: false
    });

    const renderMenu = (displayMenu = true) => {
        setObjectState({
            isMobile: isMobile(),
            menuVisible: displayMenu,
        });
    }

    const toggleVisible = () => {
        setObjectState((prevState) => ({
            ...prevState,
            menuVisible: !prevState.menuVisible,
        }));
    }

    useEffect(() => {
        // initiate the event handler
        renderMenu(!isMobile());
        window.addEventListener('resize', renderMenu, { passive: true });

        // this will clean up the event every time the component is re-rendered
        return function cleanup() {
            window.removeEventListener('resize', renderMenu, { passive: true });
        };
    }, []);

    return (

        <div className="hero-side-category">
            <div className="category-toggle-wrap">
                <button onClick={toggleVisible} className="category-toggle">
                    <span className="arrow_carrot-right_alt2 mr-2"></span> All Categories</button>
            </div>
            <nav className="category-menu" >
                <CategoryMenu
                    in={stateObject.menuVisible}
                    isMobile={stateObject.isMobile} />
            </nav>
        </div>
    );
}

export default CategoryMenuContainer;