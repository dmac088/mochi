import React, { useState, useEffect } from 'react';
import CategoryMenu from "./CategoryMenu";
import { useDispatch, useSelector } from 'react-redux'
import 'velocity-animate/velocity.ui';
import { isMobile } from '../../../Helpers/Mobile/Mobile';
import { getAllCategories } from '../../../../../actions/CategoryActions';

function CategoryMenuContainer(props) {

    const [stateObject, setObjectState] = useState({
        menuVisible: false,
        isMobile: isMobile(),
    });

    const renderMenu = (displayMenu = true) => {
        const newState = {
            menuVisible: displayMenu,
            isMobile: isMobile(),
        };
        if (stateObject.isMobile === newState.isMobile
            && stateObject.menuVisible === displayMenu) { return; }
        setObjectState(newState);
    }

    const toggleVisible = () => {
        setObjectState((prevState) => ({
            ...prevState,
            menuVisible: !prevState.menuVisible,
        }));
    }

    const dispatch = useDispatch();

    useEffect(() => {
        renderMenu(!isMobile());

        dispatch(getAllCategories());

        // initiate the event handler
        window.addEventListener('resize', renderMenu, { passive: true });

        // this will clean up the event every time the component is re-rendered
        return function cleanup() {
            window.removeEventListener('resize', renderMenu, { passive: true });
        };
    }, []);

    const categories = useSelector(state => state.categories.list);

    return (

        <div className="hero-side-category">
            <div className="category-toggle-wrap">
                <button onClick={toggleVisible} className="category-toggle">
                    <span className="arrow_carrot-right_alt2 mr-2"></span> All Categories</button>
            </div>
            <nav className="category-menu" >
                <CategoryMenu
                    {...props}
                    categories={categories}
                    in={stateObject.menuVisible}
                    isMobile={stateObject.isMobile} />
            </nav>
        </div>
    );
}

export default CategoryMenuContainer;