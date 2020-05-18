import React from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";

function Container(props) {
    return (
        <React.Fragment>
            <Header />
                {props.children}
            <Footer />
        </React.Fragment>
    );
}

export default Container;