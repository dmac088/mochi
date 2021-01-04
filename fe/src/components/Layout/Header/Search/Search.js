import React, { useState } from "react";
import { useSelector } from 'react-redux';
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import { InputGroup, Form } from 'react-bootstrap';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import './Search.css';
import { getSearchPath } from "../../Helpers/Route/Route";

function Search(props) {

    const { history, match } = props;
    const { lang } = match.params;
    const [isLoading, setIsLoading] = useState(false);
    const [options, setOptions] = useState([]);
    const links = useSelector(state => state.discovery.links);

    const handleClick = (e) => {
        e.preventDefault();
        history.push(getSearchPath(match, e.target.id));
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            history.push(getSearchPath(match, e.target.value));
        }
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        history.push(getSearchPath(match, e.target.elements[0].value));
    }

    const handleSearch = (query) => {

        setIsLoading(true);

        const SEARCH_URI = links.searchSuggestion.href.replace('{q}', query);

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
        <div className="typeaheadwrapper">
            <Form onSubmit={handleSubmit}>
                <Form.Group >
                    <InputGroup>
                        <AsyncTypeahead
                            id="async-example"
                            isLoading={isLoading}
                            labelKey="suggestion"
                            minLength={((lang === 'en-GB') ? 3 : 1)}
                            onSearch={handleSearch}
                            onKeyDown={handleKeyDown}
                            options={options}
                            placeholder="Search for products..."
                            inputProps={{
                                style: {
                                    "position": "relative",
                                    "backgroundColor": "#ffffff",
                                    "height": "50px",
                                    "border": "1px solid #e4e4e4",
                                    "paddingRight": "55px",
                                    "borderRadius": "50px"
                                }
                            }}
                            renderMenuItemChildren={option => {
                                return (
                                    <div id={option.suggestion} onClick={handleClick}>
                                        {option.suggestion}
                                    </div>
                                )
                            }}
                        />
                        <InputGroup.Append>
                            <button type="submit" className="search">
                                <span className="icon_search"></span>
                            </button>
                        </InputGroup.Append>
                    </InputGroup>
                </Form.Group>
            </Form>
        </div>
    );


}

export default Search;