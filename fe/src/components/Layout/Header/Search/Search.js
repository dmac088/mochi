import React, { useState, Fragment } from "react";
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import 'react-bootstrap-typeahead/css/Typeahead.css';

const SEARCH_URI = 'https://localhost:8090/api/Search/en-GB/HKD/Suggest/' + 'pome';

function Search(props) {

    const { match } = props;
    const { lang } = match.params;

    const [isLoading, setIsLoading] = useState(false);
    const [options, setOptions] = useState([]);

    const handleSearch = (query) => {
        setIsLoading(true);

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
            renderMenuItemChildren={(option, props) => {
                console.log(option);
                return (
                    <Fragment>
                        {/* <img
                            alt={option.value}
                            src={option.value}
                            style={{
                                height: '24px',
                                marginRight: '10px',
                                width: '24px',
                            }} />  */}
                        {option.suggestion}
                        {/* <span>{option.suggestion}</span> */}
                    </Fragment>
                )
            }
            }
        />
    );


}


export default Search;