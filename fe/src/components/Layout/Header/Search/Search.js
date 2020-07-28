import React, { useState, Fragment } from "react";
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';


function Search(props) {

    const { match } = props;
    const { lang } = match.params;

    const [isLoading, setIsLoading] = useState(false);
    const [options, setOptions] = useState([]);

    const handleClick = () => {
        console.log('handleClick');
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            console.log('search....');
        }
    }

    const handleSearch = (query) => {
        setIsLoading(true);

        const SEARCH_URI = `https://localhost:8090/api/Search/{locale}/{currency}/Suggest/${query}`;

        axios.get(`${SEARCH_URI}`)
            .then((resp) => {
                return resp.data;
            })
            .then((items) => {
                const options = items.map((i) => ({
                    suggestion: i,
                }));

                setOptions(options);
                setIsLoading(false);
            });
    };

    return (
        // <div className={"header-advance-search"}>
        <React.Fragment>
        <AsyncTypeahead
            id="async-example"
            isLoading={isLoading}
            labelKey="suggestion"
            minLength={((lang === 'en-GB') ? 3 : 1)}
            onSearch={handleSearch}
            onKeyDown={handleKeyDown}
            options={options}
            placeholder="Search for products..."
            renderMenuItemChildren={option => {
                return (
                    <div onClick={(e)=>handleClick(e)}>
                        {option.suggestion}
                    </div>
                    // <div key={option.suggestion}>
                    //     {/* <img
                    //         alt={option.login}
                    //         src={option.avatar_url}
                    //         style={{
                    //             height: '24px',
                    //             marginRight: '10px',
                    //             width: '24px',
                    //         }}
                    //     /> */}
                    //     <span>{option.suggestion}</span>
                    // </div>
                )
            }}
        />
        </React.Fragment>
        // </div>
    );


}


export default Search;