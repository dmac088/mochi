import React, { useEffect, useRef } from "react";
import { Spinner } from '../../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import { getShippingType } from '../../../../../services/Shipping/Type/index';

function ShippingType(props) {
    const { destination, destinationCode } = props;
    const dispatch = useDispatch();
    const discovery = useSelector(state => state.discovery);
    const shippingTypes = useSelector(state => state.shippingTypes);
    const prevDestinationCode = usePrevious(destinationCode);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    const renderTypes = (types) => {
        return types.map((p, index) => {
          return <option key={index}>{p.data.shippingTypeDesc}</option>
        })
    }

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {
           
            if (!discovery.loading && discovery.isDone && prevDestinationCode !== destinationCode && destination) {
                dispatch(getShippingType(destination));
            }
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone,
        destinationCode]);

    const shippingTypesReady = (!shippingTypes.isDone || shippingTypes.loading);
        
    return (
        (shippingTypesReady)
        ? <Spinner />
        :
            <div className="col-md-6 col-12 mb-25">
                <select className="nice-select">
                    {renderTypes(shippingTypes._embedded.shippingTypeResources)}
                </select>
            </div>
    );
}

export default ShippingType;