import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getAddress, updateAddress } from '../../../../services/Address/index';
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';

function AddressEdit() {
    const address = useSelector(state => state.address);
    const customer = useSelector(state => state.customer);
    const dispatch = useDispatch();

    const [stateObject, setObjectState] = useState({
        addressLine1: null,
        addressLine2: null, 
        addressLine3: null,
        country: null,
        postCode: null,
    });

    const setAddressLine1 = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          addressLine1: value,
        }));
    }

    const setAddressLine2 = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          addressLine2: value,
        }));
    }

    const setAddressLine3 = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          addressLine3: value,
        }));
    }

    const setAddressCountry = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          country: value,
        }));
    }

    const setAddressPostCode = (e) => {
        e.preventDefault();
        const value = e.target.value;
        setObjectState((prevState) => ({ 
          ...prevState, 
          postCode: value,
        }));
    }

    const saveButtonClick = (e) => {
        e.preventDefault();     
        console.log("saveButtonClick");   
        dispatch(updateAddress(address, stateObject));
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

    console.log(stateObject);
    return (
        ((!address.isDone || address.loading))
        ? <Spinner />
        : <React.Fragment>
            <h3>Edit {address.data.addressTypeDesc}</h3>
            <div className="account-details-form">
                <form action="#">
                    <div className="row">
                        <div className="col-12 mb-30">
                            <input onChange={setAddressLine1} id="address-line-1" placeholder="Address Line 1" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input onChange={setAddressLine2} id="address-line-2" placeholder="Address Line 2" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input onChange={setAddressLine3} id="address-line-3" placeholder="Address Line 3" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input onChange={setAddressCountry} id="country" placeholder="Country" type="text" />
                        </div>

                        <div className="col-12 mb-30">
                            <input onChange={setAddressPostCode} id="post-code" placeholder="Post Code" type="text" />
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