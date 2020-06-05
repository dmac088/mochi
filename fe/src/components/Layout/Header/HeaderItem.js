import React from "react";
import { routeToPage } from '../../../services/Routing/Helper';
import { localization } from '../Localization/Localization';

export const HeaderItem = (props) => {
    const { params, history, routePage, match } = props;
    const {lang} =  match.params;


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
