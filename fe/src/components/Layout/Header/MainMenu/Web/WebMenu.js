import React from "react";
import HomeMenu from './HomeMenu';
import BrandMenu from './BrandMenu';
import ContactMenu from './ContactMenu';

function MainMenu(props) {
    const {isMobile} = props;
    return (
        <nav style={{"display" : ((isMobile) ? "none" : "block")}}>
            <ul>
                <li>
                    <HomeMenu />
                </li>
                <li className="menu-item-has-children">
                    <BrandMenu />
                </li>
                    {/* <li className="menu-item-has-children"> */}
                        
                    {/* </li> */}
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
    );
}

export default MainMenu;