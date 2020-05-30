import React from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
import Scroller from "../Layout/Scroller/Scroller";
import { withRouter } from 'react-router-dom';
import { isHomePath } from './Helpers/Route/Route';

const Container = withRouter(({...props}) => {
    return (
        <ContainerBase {...props} />
    );
});

function ContainerBase(props) {
    const { path } = props.match;
    console.log(path);
    return (
        <React.Fragment>
            <Header />
            {isHomePath(path)
            ? <React.Fragment></React.Fragment>
            : <BreadCrumb />}
                {props.children}
            {/* <Scroller /> */}
            <Footer />
        </React.Fragment>
    );
}

export default Container;