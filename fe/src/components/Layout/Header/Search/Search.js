import React, { useState, Fragment } from "react";
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';



function Search(props) {

    const [isLoading, setIsLoading] = useState(false);
    const [options, setOptions] = useState([]);

    const handleSearch = (query) => {
        console.log(query);

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

    console.log(options);
    return (
        <AsyncTypeahead
            id="async-example"
            isLoading={isLoading}
            labelKey="suggestion"
            minLength={3}
            onSearch={handleSearch}
            options={options}
            placeholder="Search for products..."
            renderMenuItemChildren={option => {
                console.log(option);
                return (
                    <div key={option.suggestion}>
                        {/* <img
                            alt={option.login}
                            src={option.avatar_url}
                            style={{
                                height: '24px',
                                marginRight: '10px',
                                width: '24px',
                            }}
                        /> */}
                        <span>{option.suggestion}</span>
                    </div>
                )
            }
            }
        />
    );


}


export default Search;