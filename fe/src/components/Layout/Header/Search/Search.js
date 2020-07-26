import React from "react";
import { Typeahead } from 'react-bootstrap-typeahead';
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import { render } from 'react-dom';
import 'react-bootstrap-typeahead/css/Typeahead.css';
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