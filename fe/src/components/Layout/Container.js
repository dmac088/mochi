import React from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import BreadCrumb from "./BreadCrumb/BreadCrumb";

function Container(props) {
    return (
        <React.Fragment>
            <Header />
            <BreadCrumb />
                {props.children}
            <Footer />
        </React.Fragment>
    );
}

export default Container;