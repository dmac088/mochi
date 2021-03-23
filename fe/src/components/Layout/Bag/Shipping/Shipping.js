import React, { useState, useEffect, useRef } from "react";
import { useDispatch, useSelector } from 'react-redux';
import ShippingProvider from './Provider/ShippingProvider';
import ShippingDestination from './Destination/ShippingDestination';
import ShippingType from './Type/ShippingType';
import { getShippingDestinations, findByCode } from '../../../../services/Shipping/Destination/index';

function Shipping() {

    const dispatch = useDispatch();
    const discovery = useSelector(state => state.discovery);
    const shippingDestinations = useSelector(state => state.shippingDestinations);

    //selected shipping destination stored in local state
    const [stateObject, setObjectState] = useState({
        currentDestinationCode: "HKHK1",
        currentDestination: null,
    });

    const prevDestinationCode = usePrevious(stateObject.currentDestinationCode);

    function setDestinationCode(e) {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          currentDestinationCode: value,
        }));
    }

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {
            if (!discovery.loading && discovery.isDone) {
                dispatch(getShippingDestinations(discovery));
            }
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone]);

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {    
            if (!shippingDestinations.loading && shippingDestinations.isDone && prevDestinationCode !== stateObject.currentDestinationCode) {
                setObjectState((prevState) => ({ 
                    ...prevState, 
                    currentDestination: findByCode(shippingDestinations._embedded.shippingDestinationResources, stateObject.currentDestinationCode),
                  }));
            }    
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone,
        setObjectState.currentDestinationCode]);

        console.log( 'previous = ' + prevDestinationCode);
    return (
        <div className="calculate-shipping">
            <h4>Calculate Shipping</h4>
            <form action="#">
                <div className="row">
                    <ShippingProvider />
                    <ShippingDestination
                        shippingDestinations={shippingDestinations}
                        currentDestinationCode={stateObject.currentDestinationCode}
                        setDestination={setDestinationCode} />
                    <ShippingType 
                        destination={stateObject.currentDestination} />
                    <div className="col-md-6 col-12 mb-25">
                        <input type="submit" defaultValue="Estimate" />
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Shipping;