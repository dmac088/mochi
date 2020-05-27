import React, { useEffect, useState } from 'react';
import { TransitionGroup, Transition } from 'react-transition-group'
import CategoryMenu from "./CategoryMenu";
import 'velocity-animate/velocity.ui';

function CategoryMenuContainer() {

    const [stateObject, setObjectState] = useState({
        menuVisible: true,
        isMobile: false
    });

    const isMobile = () => {
        return ((window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth) <= 991);
    }

    const renderMenu = (isMounting = false) => {
        if (isMobile() && !stateObject.isMobile) {
            setObjectState({
                isMobile: true,
                menuVisible: (!isMounting),
            });
        }
        if (!isMobile() && stateObject.isMobile) {
            setObjectState({
                isMobile: false,
                menuVisible: true,
            });
        }
    }

    const toggleVisible = () => {
        console.log(stateObject.menuVisible);
        setObjectState((prevState) => ({
            ...prevState,
            menuVisible: !prevState.menuVisible,
        }));
    }

    useEffect(() => {
        renderMenu(true);
        window.addEventListener('resize',  renderMenu , { passive: true });
    });

    return (
        <div className="hero-side-category">
            <div className="category-toggle-wrap">
                <button onClick={toggleVisible} className="category-toggle">
                    <span className="arrow_carrot-right_alt2 mr-2"></span> All Categories</button>
            </div>
            <nav className="category-menu" >
                <TransitionGroup
                    component="nav"
                    className="category-menu">
                    {((stateObject.menuVisible)
                        ? <CategoryMenu />
                        : null)}
                </TransitionGroup>
            </nav>
        </div>
    );
}

export default CategoryMenuContainer;