import React, { useEffect } from "react";
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

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(discover(params.lang, params.curr));
        dispatch(reauthenticate());
    });

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