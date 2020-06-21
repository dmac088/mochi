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
    const { path } = props.match;
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(reauthenticate());
        dispatch(discover());
    }, []);

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