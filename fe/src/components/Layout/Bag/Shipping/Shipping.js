import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import ShippingProvider from './Provider/ShippingProvider';
import ShippingDestination from './Destination/ShippingDestination';
import ShippingType from './Type/ShippingType';
import { getShippingDestinations, findByCode } from '../../../../services/Shipping/Destination/index';

function Shipping() {

    const dispatch              = useDispatch();
    const discovery             = useSelector(state => state.discovery);
    const bag                   = useSelector(state => state.bag);
    const shippingDestinations  = useSelector(state => state.shippingDestinations);

    //selected shipping destination stored in local state
    const [stateObject, setObjectState] = useState({
        currentDestinationCode: "HKHK1",
        currentDestination: null,
    });

    function setDestinationCode(e) {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          currentDestinationCode: value,
        }));
    }

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {
            if (!discovery.loading && discovery.isDone) {
                if(!bag.loading && bag.isDone) {
                    dispatch(getShippingDestinations(discovery, 0.5));
                }
            }
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone,
        bag.loading,
        bag.isDone]);


    const bagReady          = ((bag.isDone && !bag.loading));
    const destinationsReady = ((shippingDestinations.isDone && !shippingDestinations.loading));

    return (
        (!(destinationsReady && bagReady))
        ? <Spinner />
        :
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
                        destinationCode={stateObject.currentDestinationCode}
                        destination={findByCode(shippingDestinations._embedded.shippingDestinationResources, stateObject.currentDestinationCode)}/>
                    <div className="col-md-6 col-12 mb-25">
                        <input type="submit" defaultValue="Estimate" />
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Shipping;