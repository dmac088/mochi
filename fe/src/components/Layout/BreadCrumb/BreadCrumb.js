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
                                <Route exact path='/:lang/:curr/category/:path'     component={(props) => CategoryBreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/:path'              component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/myaccount/:path'    component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/product/:path'      component={(props) => BreadcrumbItem(props, categories)} />
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

const CategoryBreadcrumbItem = (props, categories) => {
    const { match } = props;
    return (
        <React.Fragment>
            <li>
                <Link to={getHomePath(match)}>
                    {localization[match.params.lang]["home"]}
                </Link>
            </li>
            {renderCategoryBreadCrumb(stripLocale(match).split('/'), match, categories)}
            <Route path={`${match.url}/:path`} component={CategoryBreadcrumbItem} />
        </React.Fragment>
    );
}

const BreadcrumbItem = (props) => {
    const { match } = props;
    return (
        <React.Fragment>
            <li>
                <Link to={getHomePath(match)}>
                    {localization[match.params.lang]["home"]}
                </Link>
            </li>
            {renderBreadCrumb(stripLocale(match).split('/'), match)}
            <Route path={`${match.url}/:path`} component={BreadcrumbItem} />
        </React.Fragment>
    );
}

const renderCategoryBreadCrumb = (array, match, categories) => {
    const { lang } = match.params;

    return (
        array.map((s, index) => {
            const item = (categories.list.filter(c => c.data.categoryCode === s));
            const cat = item[0];
            const desc = (cat) ? cat.data.categoryDesc : '';

            return (
                <li key={index} className={match.isExact ? 'breadcrumb-active' : ''}>
                    <Link to={match.url || ''}>
                        {(!localization[lang][s]) ? desc : (localization[lang][s])}
                    </Link>
                </li>
            );
        })
    );
}

const renderBreadCrumb = (array, match) => {
    const { lang } = match.params;
    return (
        array.map((s, index) => {
            return (
                <li key={index} className={match.isExact ? 'breadcrumb-active' : ''}>
                    <Link to={match.url || ''}>
                        {localization[lang][s] || s}
                    </Link>
                </li>
            );
        })
    );
}

export default BreadCrumb;
