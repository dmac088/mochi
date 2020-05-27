import React from 'react';

function CategoryMenu() {
    return (
        <div className="hero-side-category">

            <div className="category-toggle-wrap">

                <button className="category-toggle"> <span className="arrow_carrot-right_alt2 mr-2"></span> All Categories</button>
            </div>
            <nav className="category-menu" >
                <ul>
                    <li><a href="shop-left-sidebar.html">Vagatables</a></li>
                    <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Salad</a>


                        <ul className="category-mega-menu">
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Vegetables</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Salad</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Fruits</a></li>
                                    <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Fast Foods</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Fruit</a></li>
                                    <li><a href="shop-left-sidebar.html">Butter</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Salad</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Salad</a></li>
                                    <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                                </ul>
                            </li>
                        </ul>

                    </li>
                    <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Fast Foods</a>


                        <ul className="category-mega-menu">
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Vegetables</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Salad</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Fruits</a></li>
                                    <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Fast Foods</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Fruit</a></li>
                                    <li><a href="shop-left-sidebar.html">Butter</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children">
                                <a className="megamenu-head" href="shop-left-sidebar.html">Salad</a>
                                <ul>
                                    <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                                    <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                                    <li><a href="shop-left-sidebar.html">Salad</a></li>
                                    <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                                </ul>
                            </li>
                        </ul>

                    </li>
                    <li><a href="shop-left-sidebar.html">Beans</a></li>
                    <li><a href="shop-left-sidebar.html">Bread</a></li>
                    <li><a href="shop-left-sidebar.html">Fish &amp; Meats</a></li>
                    <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                    <li><a href="shop-left-sidebar.html">Birds</a></li>
                    <li className="hidden" ><a href="shop-left-sidebar.html">Eggs</a></li>
                    <li className="hidden" ><a href="shop-left-sidebar.html">Fruits</a></li>
                    <li><a href="#" id="more-btn"><span className="icon_plus_alt2"></span> More Categories</a></li>
                </ul>
            </nav>
        </div>
    );
}

export default CategoryMenu;