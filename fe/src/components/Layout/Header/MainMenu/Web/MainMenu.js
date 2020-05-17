import React from "react";
import HomeMenu from './HomeMenu';
import ContactMenu from './ContactMenu';
import { menuCategories } from '../LayoutHelper';

function MainMenu() {
    return (
        < div className = "main-menu" >
            <nav>
                <ul>
                    <HomeMenu />
                    <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Shop</a>
                        <ul className="sub-menu">
                            <li className="menu-item-has-children"><a href="shop-4-column.html">shop grid</a>
                                <ul className="sub-menu">
                                    <li><a href="shop-3-column.html">shop 3 column</a></li>
                                    <li><a href="shop-4-column.html">shop 4 column</a></li>
                                    <li><a href="shop-left-sidebar.html">shop left sidebar</a></li>
                                    <li><a href="shop-right-sidebar.html">shop right sidebar</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children"><a href="shop-list.html">shop List</a>
                                <ul className="sub-menu">
                                    <li><a href="shop-list.html">shop List</a></li>
                                    <li><a href="shop-list-left-sidebar.html">shop List Left Sidebar</a></li>
                                    <li><a href="shop-list-right-sidebar.html">shop List Right Sidebar</a></li>
                                </ul>
                            </li>
                            <li className="menu-item-has-children"><a href="single-product.html">Single Product</a>
                                <ul className="sub-menu">
                                    <li><a href="single-product.html">Single Product</a></li>
                                    <li><a href="single-product-variable.html">Single Product variable</a></li>
                                    <li><a href="single-product-affiliate.html">Single Product affiliate</a></li>
                                    <li><a href="single-product-group.html">Single Product group</a></li>
                                    <li><a href="single-product-tabstyle-2.html">Tab Style 2</a></li>
                                    <li><a href="single-product-tabstyle-3.html">Tab Style 3</a></li>
                                    <li><a href="single-product-gallery-left.html">Gallery Left</a></li>
                                    <li><a href="single-product-gallery-right.html">Gallery Right</a></li>
                                    <li><a href="single-product-sticky-left.html">Sticky Left</a></li>
                                    <li><a href="single-product-sticky-right.html">Sticky Right</a></li>
                                    <li><a href="single-product-slider-box.html">Slider Box</a></li>

                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-item-has-children"><a href="#">PAGES</a>
                        <ul className="mega-menu three-column">
                            <li><a href="#">Column One</a>
                                <ul>
                                    <li><a href="cart.html">Cart</a></li>
                                    <li><a href="checkout.html">Checkout</a></li>
                                    <li><a href="wishlist.html">Wishlist</a></li>

                                </ul>
                            </li>
                            <li><a href="#">Column Two</a>
                                <ul>
                                    <li><a href="my-account.html">My Account</a></li>
                                    <li><a href="login-register.html">Login Register</a></li>
                                    <li><a href="faq.html">FAQ</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Column Three</a>
                                <ul>
                                    <li><a href="compare.html">Compare</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-item-has-children"><a href="#">BLOG</a>
                        <ul className="sub-menu">
                            <li><a href="blog-3-column.html">Blog 3 column</a></li>
                            <li><a href="blog-grid-left-sidebar.html">Blog Grid Left Sidebar</a></li>
                            <li><a href="blog-grid-right-sidebar.html">Blog Grid Right Sidebar</a></li>
                            <li><a href="blog-list-left-sidebar.html">Blog List Left Sidebar</a></li>
                            <li><a href="blog-list-right-sidebar.html">Blog List Right Sidebar</a></li>
                            <li><a href="blog-post-left-sidebar.html">Blog Post Left Sidebar</a></li>
                            <li><a href="blog-post-right-sidebar.html">Blog Post Right Sidebar</a></li>
                            <li><a href="blog-post-image-format.html">Blog Post Image Format</a></li>
                            <li><a href="blog-post-image-gallery.html">Blog Post Image Gallery Format</a></li>
                            <li><a href="blog-post-audio-format.html">Blog Post Audio Format</a></li>
                            <li><a href="blog-post-video-format.html">Blog Post Video Format</a></li>
                        </ul>
                    </li>
                    <ContactMenu />
                </ul>
            </nav>
        </div >
    );
}

export default MainMenu;