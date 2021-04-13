import React, { useEffect } from "react";
import { Spinner } from '../../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import { getShippingProviders } from '../../../../../services/Shipping/Provider/index';

function ShippingProvider() {

    const dispatch = useDispatch();
    const discovery = useSelector(state => state.discovery);
    const shippingProviders = useSelector(state => state.shippingProviders);

    const renderProviders = (providers) => {
        return providers.map((p, index) => {
          return <option key={index}>{p.data.brandDesc}</option>
        })
    }

    useEffect(() => {
        let isSubscribed = true;
        if (isSubscribed) {
            if (!discovery.loading && discovery.isDone) {
                dispatch(getShippingProviders(discovery));
            }
        }
        return () => (isSubscribed = false);
    }, [discovery.loading,
        discovery.isDone]);

    return (
        ((!shippingProviders.isDone || shippingProviders.loading))
        ? <Spinner />
        :
            <div className="col-md-6 col-12 mb-25">
                <select className="nice-select">
                    {renderProviders(shippingProviders._embedded.brandViewResources)}
                </select>
            </div>
    );
}

export default ShippingProvider;