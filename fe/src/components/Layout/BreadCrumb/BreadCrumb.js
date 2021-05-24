import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Link } from 'react-router-dom';
import { getHomePath, getCategoryPath } from '../Helpers/Route/Route';
import { localization } from '../Localization/Localization';



function BreadCrumb(props) {

    const categories = useSelector(state => state.categories);

    const stripLocale = (match) => {
        const { lang, curr } = match.params;
        return match.url.replace(`/${lang}/${curr}/`, '');
    }

    const renderBreadCrumb = (array, match) => {
        let categoryDesc = null;
        return (
            array.map((s, index) => {
    
                //lookup category to get the localized description 
                if(array[index-1] === 'category') {
                    const category = categories.list.find(x => x.data.id === array[index]);
                    categoryDesc = (category) ? category.data.desc : '';
                }

                return (
                    <li key={index} className={match.isExact ? 'breadcrumb-active' : ''}>
                        <Link to={  array[index-1] === 'category'
                                    ? getCategoryPath(s, match)
                                    : match.url || ''}>
                            { array[index-1] === 'category'
                            ? categoryDesc
                            : localization[match.params.lang][s] || s}
                        </Link>
                    </li>
                );
            })
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

    return (
        <div className="breadcrumb-area mb-50">
            <div className="container">
                <div className="row">
                    <div className="col">
                        <div className="breadcrumb-container">
                            <ul>
                                <Route exact path='/:lang/:curr/:type/category/:path'                       component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/category/:path'                             component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/:path'                                      component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/myaccount/:path'                            component={(props) => BreadcrumbItem(props, categories)} />
                                <Route exact path='/:lang/:curr/category/:categoryCode/product/:path'       component={(props) => BreadcrumbItem(props, categories)} />
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};





export default BreadCrumb;
