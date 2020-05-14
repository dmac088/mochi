import React from "react";

function Header() {
    return (
      <header>
            {/*<!--=======  header top  =======-->*/}
            <div className="header-top pt-10 pb-10 pt-lg-10 pb-lg-10 pt-md-10 pb-md-10">
              <div className="container">
                <div className="row">
                  <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12 text-center text-sm-left">
                    {/*<!-- currncy language dropdown -->*/}
                    <div className="lang-currency-dropdown">
                      <ul>
                        <li> <a href="#">English <i className="fa fa-chevron-down"></i></a>
                          <ul>
                            <li><a href="#">French</a></li>
                            <li><a href="#">Japanease</a></li>
                          </ul>
                        </li>
                        <li><a href="#">Dollar <i className="fa fa-chevron-down"></i></a>
                          <ul>
                            <li><a href="#">Euro</a></li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                    {/*<!-- end of currncy language dropdown -->*/}
                  </div>
                  <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12  text-center text-sm-right">
                    {/*<!-- header top menu -->*/}
                    <div className="header-top-menu">
                      <ul>
                        <li><a href="my-account.html">My account</a></li>
                        <li><a href="wishlist.html">Wishlist</a></li>
                        <li><a href="checkout.html">Checkout</a></li>
                      </ul>
                    </div>
                    {/*<!-- end of header top menu -->*/}
                  </div>
                </div>
              </div>
            </div>
            {/*<!--=======  End of header top  =======-->*/}

            {/*<!--=======  header bottom  =======-->*/}
            <div className="header-bottom header-bottom-one header-sticky">
              <div className="container">
                <div className="row">
                  <div className="col-md-3 col-sm-12 col-xs-12 text-lg-left text-md-center text-sm-center">
                    {/*<!-- logo -->*/}
                    <div className="logo mt-15 mb-15">
                      <a href="index.html">
                        <img src="assets/images/logo.png" className="img-fluid" alt="" />
                      </a>
                    </div>
                    {/*<!-- end of logo -->*/}
                  </div>
                  <div className="col-md-9 col-sm-12 col-xs-12">
                    <div className="menubar-top d-flex justify-content-between align-items-center flex-sm-wrap flex-md-wrap flex-lg-nowrap mt-sm-15">
                      {/*<!-- header phone number -->*/}
                      <div className="header-contact d-flex">
                        <div className="phone-icon">
                          <img src="assets/images/icon-phone.png" className="img-fluid" alt="" />
                        </div>
                        <div className="phone-number">
                          Phone: <span className="number">1-888-123-456-89</span>
                        </div>
                      </div>
                      {/*<!-- end of header phone number -->*/}
                      {/*<!-- search bar -->*/}
                      <div className="header-advance-search">
                        <form action="#">
                          <input type="text" placeholder="Search your product" />
                          <button><span className="icon_search"></span></button>
                        </form>
                      </div>
                      {/*<!-- end of search bar -->*/}
                      {/*<!-- shopping cart -->*/}
                      <div className="shopping-cart" id="shopping-cart">
                        <a href="cart.html">
                          <div className="cart-icon d-inline-block">
                            <span className="icon_bag_alt"></span>
                          </div>
                          <div className="cart-info d-inline-block">
                            <p>Shopping Cart
												<span>
                                0 items - $0.00
												</span>
                            </p>
                          </div>
                        </a>
                        {/*<!-- end of shopping cart -->*/}

                        {/*<!-- cart floating box -->*/}
                        <div className="cart-floating-box" id="cart-floating-box">
                          <div className="cart-items">
                            <div className="cart-float-single-item d-flex">
                              <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
                              <div className="cart-float-single-item-image">
                                <a href="single-product.html"><img src="assets/images/products/product01.jpg" className="img-fluid" alt="" /></a>
                              </div>
                              <div className="cart-float-single-item-desc">
                                <p className="product-title"> <a href="single-product.html">Duis pulvinar obortis eleifend </a></p>
                                <p className="price"><span className="count">1x</span> $20.50</p>
                              </div>
                            </div>
                            <div className="cart-float-single-item d-flex">
                              <span className="remove-item"><a href="#"><i className="fa fa-times"></i></a></span>
                              <div className="cart-float-single-item-image">
                                <a href="single-product.html"><img src="assets/images/products/product02.jpg" className="img-fluid" alt="" /></a>
                              </div>
                              <div className="cart-float-single-item-desc">
                                <p className="product-title"> <a href="single-product.html">Fusce ultricies dolor vitae</a></p>
                                <p className="price"><span className="count">1x</span> $20.50</p>
                              </div>
                            </div>
                          </div>
                          <div className="cart-calculation">
                            <div className="calculation-details">
                              <p className="total">Subtotal <span>$22</span></p>
                            </div>
                            <div className="floating-cart-btn text-center">
                              <a href="checkout.html">Checkout</a>
                              <a href="cart.html">View Cart</a>
                            </div>
                          </div>
                        </div>
                        {/*<!-- end of cart floating box -->*/}
                      </div>
                    </div>

                    {/*<!-- navigation section -->*/}
                    <div className="main-menu">
                      <nav>
                        <ul>
                          <li className="active menu-item-has-children"><a href="#">HOME</a>
                            <ul className="sub-menu">
                              <li><a href="index.html">Home Shop 1</a></li>
                              <li><a href="index-2.html">Home Shop 2</a></li>
                              <li><a href="index-3.html">Home Shop 3</a></li>
                              <li><a href="index-4.html">Home Shop 4</a></li>
                            </ul>
                          </li>
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
                          <li><a href="contact.html">CONTACT</a></li>
                        </ul>
                      </nav>
                    </div>
                    {/*<!-- end of navigation section -->*/}
                  </div>
                  <div className="col-12">
                    {/*<!-- Mobile Menu -->*/}
                    <div className="mobile-menu d-block d-lg-none"></div>
                  </div>
                </div>
              </div>
            </div>

            {/*<!--=======  End of header bottom  =======-->*/}
          </header>
    );
}


export default Header;
