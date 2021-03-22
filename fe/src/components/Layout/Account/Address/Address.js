import React, { useEffect, useState } from "react";
import { Spinner } from '../../../Layout/Helpers/Animation/Spinner';
import { useDispatch, useSelector } from 'react-redux';
import AddressView from "./AddressView";
import AddressEdit from "./AddressEdit";

function Address(props) {
    const { type, address, getAddress } = props;
    const dispatch = useDispatch();
    const customer = useSelector(state => state.customer);

    const [stateObject, setObjectState] = useState({
        showEdit: false,
    });

    const toggleEdit = () => {
        setObjectState((prevState) => ({ 
          ...prevState, 
          showEdit: !stateObject.showEdit,
        }));
    }

    useEffect(() => {
        let isSubscribed = true;
        if(isSubscribed) {
            if (!customer.loading && customer.isDone) {
                dispatch(getAddress(customer, type));
            }
        }
        return () => (isSubscribed = false);
    }, [customer.loading, customer.isDone]);

    return (
        ((!address.isDone || address.loading))
            ? <Spinner />
            : (stateObject.showEdit)
                ? <AddressEdit 
                        {...props}
                        toggleEdit={toggleEdit} />
                : <AddressView  
                        {...props}
                        toggleEdit={toggleEdit} />
    );
}

export default Address;

