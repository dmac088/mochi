import React, { useState } from "react";
import ShippingProvider from './Provider/ShippingProvider';
import ShippingDestination from './Destination/ShippingDestination';
import ShippingType from './Type/ShippingType';

function Shipping() {

    //selected shipping destination stored in local state
    const [stateObject, setObjectState] = useState({
        currentDestination: null,
    });

    const setDestination = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          currentDestination: value,
        }));
    }

    return (
        <div className="calculate-shipping">
            <h4>Calculate Shipping</h4>
            <form action="#">
                <div className="row">
                    <ShippingProvider />
                    <ShippingDestination
                        setDestination={setDestination} />
                    <ShippingType />
                    <div className="col-md-6 col-12 mb-25">
                        <input type="submit" defaultValue="Estimate" />
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Shipping;