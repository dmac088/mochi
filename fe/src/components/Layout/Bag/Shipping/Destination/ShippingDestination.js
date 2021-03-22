import React, { useEffect } from "react";
import { Spinner } from '../../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import { getShippingDestinations } from '../../../../../services/Shipping/Destination/index';

function ShippingDestination() {

    const dispatch = useDispatch();
    const discovery = useSelector(state => state.discovery);
    const shippingDestinations = useSelector(state => state.shippingDestinations);

    const renderDestinations = (providers) => {
        return providers.map((p, index) => {
          return <option key={index}>{p.data.productDestinationDesc}</option>
        })
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

    return (
        ((!shippingDestinations.isDone || shippingDestinations.loading))
        ? <Spinner />
        :
            <div className="col-md-6 col-12 mb-25">
                <select className="nice-select">
                    {renderDestinations(shippingDestinations._embedded.shippingDestinationResources)}
                </select>
            </div>
    );
}

export default ShippingDestination;