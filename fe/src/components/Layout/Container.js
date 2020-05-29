import React from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";
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
            ? null
            : <BreadCrumb />}
                {props.children}
            <Footer />
        </React.Fragment>
    );
}

export default Container;