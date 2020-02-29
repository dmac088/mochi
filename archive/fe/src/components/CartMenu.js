import React from 'react';

const CartMenu = (props) =>{
    return(
        <div
          className="cart-icon">
          <img
            className={props.cartBounce ? "tada" : " "}
            src="https://res.cloudinary.com/sivadass/image/upload/v1493548928/icons/bag.png"
            alt="Cart"
          />
          {props.totalItems ? (
            <span className="cart-count">
              {props.totalItems}
            </span>
          ) : (
            ""
          )}
        </div>
      );
}

export default CartMenu;
