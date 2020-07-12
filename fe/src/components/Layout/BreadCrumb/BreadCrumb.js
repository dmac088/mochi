import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Link } from 'react-router-dom';
import { getHomePath } from '../Helpers/Route/Route';
import { localization } from '../Localization/Localization';

const stripLocale = (match) => {
    const { lang, curr } = match.params;
    return match.url.replace(`/${lang}/${curr}/`, '');
}

function BreadCrumb(props) {

    const categories = useSelector(state => state.categories);

    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                                <Route exact path='/:lang/:curr/category/:path' component={(props) => BreadcrumbsItem(props, categories)} />
                                <Route exact path='/:lang/:curr/:path' component={(props) => BreadcrumbsItem(props, categories)} />
                                <Route exact path='/:lang/:curr/myaccount/:path' component={(props) => BreadcrumbsItem(props, categories)} />
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

const BreadcrumbsItem = (props, categories) => {
    const { match } = props;
    return (
        <React.Fragment>
            <li>
                <Link to={getHomePath(match)}>
                    {localization[match.params.lang]["home"]}
                </Link>
            </li>
            {renderBreadCrumb(stripLocale(match).split('/'), match, categories)}
            <Route path={`${match.url}/:path`} component={BreadcrumbsItem} />
        </React.Fragment>
    );
}

const renderBreadCrumb = (array, match, categories) => {
    const { lang } = match.params;
    console.log('hello');
    
    return (
        array.map((s, index) => {
            console.log(s);
            const cat = (categories.list.filter(c => c.data.categoryCode === s));
            console.log(cat);

            return (
                <li key={index} className={match.isExact ? 'breadcrumb-active' : ''}>
                    <Link to={match.url || ''}>
                        {(!localization[lang][s]) ? s : (localization[lang][s])}
                    </Link>
                </li>
            );
        })
    );
}

export default BreadCrumb;
