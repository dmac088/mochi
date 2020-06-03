import React from 'react';
import { withRouter, Route, Link } from 'react-router-dom';
import { getHomePath } from '../Helpers/Route/Route';

export const BreadCrumb = withRouter(({ ...props }) => {
    const { match } = props;

    return (
        <BreadCrumbBase
            match={match} />
    );
});

const stripLocale = (match) => {
    const { lang, curr } = match.params;
    return match.url.replace(`/${lang}/${curr}/`,'');
}

const BreadCrumbBase = (props) => {
    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                                <Route path='/:lang/:curr/*/:path' component={BreadcrumbsItem} />
                                {/* <li><Link to={getHomePath(match)}><i className="fa fa-home"></i> Home</Link></li>
                                <li> Test</li> */}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

const BreadcrumbsItem = ({ match, ...rest }) => (
    <React.Fragment>
        <li>
            <Link to={getHomePath(match)}>
                    {"Home"} 
            </Link>
        </li>
        {renderBreadCrumb(stripLocale(match).split('/'), match)}
        <Route path={`${match.url}/:path`} component={BreadcrumbsItem} />
    </React.Fragment>
)

const renderBreadCrumb = (array, match) => {
    return(
        array.map(s => {
            return (
                <li className={match.isExact ? 'breadcrumb-active' : undefined}>
                    <Link to={match.url || ''}>
                        {s}
                    </Link>
                </li>    
            );     
        })
    );
}

export default BreadCrumb;
