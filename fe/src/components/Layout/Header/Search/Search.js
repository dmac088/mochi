import React from "react";
import { localization } from '../../Localization/Localization';

function Search(props) {
    const { match } = props;
    const {lang} = match.params;
    return (
        <div className="header-advance-search">
            <form action="#">
                <input type="text" placeholder={localization[lang]['searchyourproduct'] + "..."} />
                <button><span className="icon_search"></span></button>
            </form>
        </div>
    );
}

export default Search;