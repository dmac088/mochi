import React, { useEffect, useRef } from "react";
import { useDispatch } from 'react-redux';
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
import Scroller from "../Layout/Scroller/Scroller";
import { isHomePath } from './Helpers/Route/Route';
import { discover } from "../../actions/DiscoveryActions";
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
        dispatch(discover(lang, curr));
    }, []);

    //if the language or the currency changes be sure to refresh teh urls
    useEffect(() => {
        if(!prevParams) { return;}
        if(prevParams.lang !== lang || prevParams.curr !== curr) {
            dispatch(discover(lang, curr));
        }
    }, [lang, curr])

    return (
        <React.Fragment>
            <Header 
                {...props}/>
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