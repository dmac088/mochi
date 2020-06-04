import React from "react";
import { withRouter } from 'react-router-dom';
import { routeToPage } from '../../../services/Routing/Helper';
import { localization } from '../Localization/Localization';


export const HeaderItem = withRouter(({...props}) => {
    const { params } = props.match;
    const{ history, routePage } = props;

    return (
        <HeaderItemBase  params={params}
                        history={history} 
                        routePage={routePage}/>
    );
});

export const HeaderItemBase = (props) => {
    const { params, history, routePage } = props;
    const {lang} =  params;


    const routeHeaderItem = (e) => {
        e.preventDefault();
        routeToPage(history, params, routePage);
    }

    return (
        <li> 
            <a href="#" onClick={routeHeaderItem}>{localization[lang][routePage]}</a>
        </li>
    );
};

export default HeaderItem;
