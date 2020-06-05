import React from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
import Scroller from "../Layout/Scroller/Scroller";
import { withRouter } from 'react-router-dom';
import { isHomePath } from './Helpers/Route/Route';


function Container(props) {
    const { path } = props.match;
    return (
        <React.Fragment>
            <Header 
                {...props}/>
            {isHomePath(path)
            ? <React.Fragment />
            : <BreadCrumb />}
                {props.children}
            <Scroller />
            <Footer />
        </React.Fragment>
    );
}

export default Container;