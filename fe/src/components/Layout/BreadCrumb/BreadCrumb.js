import React from 'react';
import { Link } from "react-router-dom";

function BreadCrumb() {
    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                                <Link to={}><i className="fa fa-home"> Home</i></Link>
                                <li> Test</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default BreadCrumb;
