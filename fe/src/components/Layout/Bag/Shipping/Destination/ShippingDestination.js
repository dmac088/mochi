import React from "react";
import { Spinner } from '../../../../Layout/Helpers/Animation/Spinner';

function ShippingDestination(props) {

    const { shippingDestinations, setDestination, currentDestinationCode } = props;

    const renderDestinations = (providers) => {
        return providers.map((p, index) => {
          return <option  
                         key={index} 
                         value={p.data.productDestinationCode}>{p.data.productDestinationDesc}</option>
        })
    }

    return (
        ((!shippingDestinations.isDone || shippingDestinations.loading))
        ? <Spinner />
        :
            <div className="col-md-6 col-12 mb-25">
                <select defaultValue={currentDestinationCode} onChange={setDestination} className="nice-select">
                    {renderDestinations(shippingDestinations._embedded.shippingDestinationResources)}
                </select>
            </div>
    );
}

export default ShippingDestination;