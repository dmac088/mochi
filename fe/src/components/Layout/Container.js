import React, { useEffect, useRef, useState } from "react";
import { useDispatch } from 'react-redux';
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
import Scroller from "../Layout/Scroller/Scroller";
import { isHomePath } from './Helpers/Route/Route';
import * as discoveryService from "../../services/Discovery/index";
import { reauthenticate } from "../../services/Session";
import QuickViewProduct from "./Landing/QuickView/QuickViewProduct";

function Container(props) {
    const { path, params } = props.match;
    const { lang, curr } = params;
    const prevParams = usePrevious({ lang, curr });

    const dispatch = useDispatch();

    const [stateObject, setObjectState] = useState({
        showQVModal: false,
        currenctProduct: null,
    });

    const toggleQuickView = (e, product) => {
        e.preventDefault();
        setObjectState((prevState) => ({
            ...prevState,
            showQVModal: !prevState.showQVModal,
            currentProduct: product,
        }));
    }

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {
        dispatch(reauthenticate())
        .then(() => {
            dispatch(discoveryService.initialize());
        });
    }, []);

    //we really only want to reinitialize on a change of locale or currency
    //not on every reload, but worry about this later
    useEffect(() => {
        if (prevParams && (lang !== prevParams.lang || curr !== prevParams.curr)) {
            dispatch(discoveryService.initialize());
        }
    }, [lang, curr]);

    return (
        <React.Fragment>
            <Header
                {...props} />
            {isHomePath(path)
                ? <React.Fragment />
                : <BreadCrumb
                    {...props} />}
            {React.cloneElement(props.children, 
                { 'toggleQuickView': toggleQuickView})}
            <Scroller />
            <Footer />
            {(stateObject.showQVModal && stateObject.currentProduct)
                ? <QuickViewProduct
                    {...props}
                    toggleQuickView={toggleQuickView}
                    showQVModal={stateObject.showQVModal}
                    product={stateObject.currentProduct} />
                : <React.Fragment />}
        </React.Fragment>
    );
}

export default Container;