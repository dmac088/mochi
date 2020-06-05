import React from 'react';
import { Route, Link } from 'react-router-dom';
import { getHomePath } from '../Helpers/Route/Route';
import { localization } from '../Localization/Localization';

const stripLocale = (match) => {
    const { lang, curr } = match.params;
    return match.url.replace(`/${lang}/${curr}/`,'');
}

const BreadCrumb = (props) => {
    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                                <Route exact path='/:lang/:curr/category/:path' component={BreadcrumbsItem} />
                                <Route exact path='/:lang/:curr/:path' component={BreadcrumbsItem} />
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
                    {localization[match.params.lang]["home"]} 
            </Link>
        </li>
        {renderBreadCrumb(stripLocale(match).split('/'), match)}
        <Route path={`${match.url}/:path`} component={BreadcrumbsItem} />
    </React.Fragment>
)

const renderBreadCrumb = (array, match) => {
    const { lang } = match.params;
    
    return(
        array.map(function(s, index) {
            return (
                <li key={index} className={match.isExact ? 'breadcrumb-active' : undefined}>
                    <Link to={match.url || ''}>
                        {(!localization[lang][s]) ? s : (localization[lang][s])}
                    </Link>
                </li>    
            );     
        })
    );
}

export default BreadCrumb;
