import React, { useEffect, useRef } from "react";
import { useDispatch } from 'react-redux';
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
import Scroller from "../Layout/Scroller/Scroller";
import { isHomePath } from './Helpers/Route/Route';
import { initialize } from "../../actions/DiscoveryActions";
import { reauthenticate } from "../../actions/SessionActions";

function Container(props) {
    const { path, params } = props.match;
    const { lang, curr } = params;
    const prevParams = usePrevious({lang, curr});

    const dispatch = useDispatch();

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {
        dispatch(reauthenticate());
        dispatch(initialize());
    }, []);

    //we really only want to reinitialize on a change of locale or currency
    //not on every reload, but worry about this later
    useEffect(() => {
        if(prevParams && (lang !== prevParams.lang || curr !== prevParams.curr)) {
            dispatch(initialize());
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
            {props.children}
            <Scroller />
            <Footer />
        </React.Fragment>
    );
}

export default Container;