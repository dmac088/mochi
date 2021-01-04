import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getAddress, updateAddress } from '../../../../services/Address/index';
import { getAccountSubPath } from "../../Helpers/Route/Route";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { Form } from 'react-bootstrap';

function AddressEdit(props) {
    const { history, match } = props;

    const address = useSelector(state => state.address);
    const customer = useSelector(state => state.customer);
    const dispatch = useDispatch();

    const [stateObject, setObjectState] = useState({
        addressLine1: null,
        addressLine2: null, 
        addressLine3: null,
        country: null,
        postCode: null,
        addressTypeCode: "BIL01"
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

    const handleSubmit = (e) => {
        e.preventDefault();     
        dispatch(updateAddress(address, stateObject))
        .then(() => {
            history.push(getAccountSubPath(match, 'viewaddress'));
        });
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
            <h3>Edit {address.data.addressTypeDesc}</h3>
            <div className="account-details-form">
                <Form onSubmit={handleSubmit}>
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
                            <input onChange={setAddressCountry} id="country" placeholder="Country" type="text" required/>
                        </div>

                        <div className="col-12 mb-30">
                            <input onChange={setAddressPostCode} id="post-code" placeholder="Post Code" type="text" />
                        </div>

                        <div className="col-12">
                            <button type="submit" className="save-change-btn">Save Changes</button>
                        </div>

                    </div>
                </Form>
            </div>
        </React.Fragment>
    );
}

export default AddressEdit;