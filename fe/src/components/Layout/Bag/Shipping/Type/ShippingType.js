import React, { useEffect } from "react";
import { Spinner } from '../../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import { getShippingType } from '../../../../../services/Shipping/Type/index';

function ShippingType(props) {
    const { currentDestination } = props;
    const dispatch = useDispatch();
    const discovery = useSelector(state => state.discovery);
    const shippingTypes = useSelector(state => state.shippingTypes);

    const renderTypes = (types) => {
        return types.map((p, index) => {
          return <option key={index}>{p.data.shippingTypeDesc}</option>
        })
    }

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {
            if (!discovery.loading && discovery.isDone) {
                dispatch(getShippingType(currentDestination));
            }
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone]);

    return (
        ((!shippingTypes.isDone || shippingTypes.loading))
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