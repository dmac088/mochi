import React from 'react';
import { Link } from "react-router-dom";
import { withRouter } from 'react-router-dom';
import { getHomePath } from "../Helpers/Route/Route";


export const BreadCrumb = withRouter(({...props}) => {
    const { history, match } = props;

    return (
        <BreadCrumbBase     
            match={match}    />
    );
});


const BreadCrumbBase = (props) => {
    const { match } = props;
    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                            <li><Link to={getHomePath(match)}><i className="fa fa-home"></i> Home</Link></li>
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
