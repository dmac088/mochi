import React from 'react';
const $ = window.$;

function Accordion() {

    const removeItem = (e) => {
        e.preventDefault();
        //cartService.removeFromCart(cartSelector.get(), e.currentTarget.id);
    }

    const renderCartItems = (cart) => {
        return (
            <div></div>
        );
    }

    return (
        <React.Fragment>
            <div className="cart-items">
                {/* {this.renderCartItems(cart)} */}
            </div>
            <div className="cart-calculation">
                <div className="calculation-details">
                    <p className="total">Subtotal <span>0</span></p>
                </div>
                <div className="floating-cart-btn text-center">
                    <a href="#">Checkout</a>
                    <a href="#">View Bag</a>
                </div>
            </div>
        </React.Fragment>
    )
}

export default Accordion;