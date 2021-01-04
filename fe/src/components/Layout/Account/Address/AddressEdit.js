import React, { useEffect }  from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getAddress, updateAddress } from '../../../../services/Address/index';
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function AddressEdit() {
    const address = useSelector(state => state.address);
    const customer = useSelector(state => state.customer);
    const dispatch = useDispatch();
    
    const saveButtonClick = (e) => {
        e.preventDefault();     
        console.log("saveButtonClick");   
        dispatch(updateAddress());
    }

    useEffect(() => {
        let isSubscribed = true;
        if(isSubscribed) {
            if (!customer.loading && customer.isDone) {
                if (!address.loading && !address.isDone) {
                    dispatch(getAddress(customer));
                }
            }
        }
        return () => (isSubscribed = false);
    }, [customer.loading, 
        customer.isDone, 
        address.loading, 
        address.isDone]);
        
    return (
        ((!address.isDone || address.loading))
        ? <Spinner />
        : <React.Fragment>
            <h3>Edit Billing Address</h3>
            <div className="account-details-form">
                <form action="#">
                    <div className="row">
                        <div className="col-12 mb-30">
                            <input id="address-line-1" placeholder="Address Line 1" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-2" placeholder="Address Line 2" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="address-line-3" placeholder="Address Line 3" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="country" placeholder="Country" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input id="post-code" placeholder="Post Code" type="text" />
                        </div>

                        <div className="col-12">
                            <button onClick={saveButtonClick} className="save-change-btn">Save Changes</button>
                        </div>

                    </div>
                </form>
            </div>
        </React.Fragment>
    );
}

export default AddressEdit;