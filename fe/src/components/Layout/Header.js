import React from "react"; 
import MainMenu from './MainMenu/MainMenu';

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
                    <MainMenu />                   
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
