import React from 'react';
import { useSelector } from 'react-redux';

function Checkout() {

  const bag = useSelector(state => state.bag);
  const bagContents = useSelector(state => state.bagContents);

  const renderItems = (items) => {
    return items.map((i, index) => {
      return <li key={index}>{i.data.itemDesc} x {i.data.itemQty}<span>${i.data.bagItemTotal}</span></li>
    })
  }
    return(
      <React.Fragment>
        <div className="page-section section mb-50">
          <div className="container">
            <div className="row">
              <div className="col-12">
                <form action="#" className="checkout-form">
                  
                        <div className="col-12 mb-60">

                          <h4 className="checkout-title">Cart Total</h4>

                          <div className="checkout-cart-total">

                            <h4>Product <span>Total</span></h4>

                            <ul>
                              {renderItems(bagContents.items)}
                            </ul> 

                            <p>Sub Total <span>${bag.totalAmount}</span></p>
                            <p>Shipping Fee <span>$00.00</span></p>

                            <h4>Grand Total <span>${bag.totalAmount}</span></h4>

                          </div>

                        </div>
                </form>

              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    )
  }

  export default Checkout;