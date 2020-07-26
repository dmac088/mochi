import React, { useState } from "react";
import { instance as axios } from "../../../../components/Layout/Helpers/api/axios";
import { Typeahead } from 'react-bootstrap-typeahead';
import { AsyncTypeahead } from 'react-bootstrap-typeahead';
import { render } from 'react-dom';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import { localization } from '../../Localization/Localization';

const PER_PAGE = 50;
const SEARCH_URI = 'https://localhost:8090/api/Search/en-GB/HKD/Suggest/' + 'pome';

function makeAndHandleRequest(query, page = 1) {
    return axios.get(`${SEARCH_URI}/${query}+in:login&page=${page}&per_page=50`)
        .then(resp => {
            console.log(`${SEARCH_URI}/${query}+in:login&page=${page}&per_page=50`);
            console.log(resp);
            return resp.json()
        })
        .then(({ items, total_count }) => {
            const options = items.map(i => ({
                avatar_url: i.avatar_url,
                id: i.id,
                login: i.login,
            }));
            return { options, total_count };
        });
}

function Search(props) {

    const { match } = props;
    const { lang } = match.params;

    const [stateObject, setObjectState] = useState({
        isLoading: false,
        options: [],
        query: '',
    });

    const _cache = {};

    const _handleInputChange = query => {
        setObjectState((prevState) => ({ 
            ...prevState,
            query 
        }));
    };

    const _handlePagination = (e, shownResults) => {
        const { query } = stateObject;
        const cachedQuery = _cache[query];

        // Don't make another request if:
        // - the cached results exceed the shown results
        // - we've already fetched all possible results
        if (
            cachedQuery.options.length > shownResults ||
            cachedQuery.options.length === cachedQuery.total_count
        ) {
            return;
        }

        setObjectState((prevState) => ({ 
            ...prevState,
            isLoading: true,
        }));

        const page = cachedQuery.page + 1;

        makeAndHandleRequest(query, page).then(resp => {
            const options = cachedQuery.options.concat(resp.options);
            _cache[query] = { ...cachedQuery, options, page };
            setObjectState((prevState) => ({
                ...prevState,
                isLoading: false,
                options,
            }));
        });
    };

    const _handleSearch = query => {
        if (_cache[query]) {
            setObjectState((prevState) => ({ 
                ...prevState,
                options: _cache[query].options 
            }));
            return;
        }

        setObjectState({ isLoading: true });
        makeAndHandleRequest(query).then(resp => {
            _cache[query] = { ...resp, page: 1 };
            setObjectState((prevState) => ({
                ...prevState,
                isLoading: false,
                options: resp.options,
            }));
        });
    };

    return (
        // <div className="header-advance-search">
        <AsyncTypeahead
            {...stateObject}
            id="async-pagination-example"
            labelKey="login"
            maxResults={PER_PAGE - 1}
            minLength={2}
            onInputChange={_handleInputChange}
            onPaginate={_handlePagination}
            onSearch={_handleSearch}
            paginate
            placeholder="Search for a product..."
            renderMenuItemChildren={option => (
                <div key={option.id}>
                    <img
                        alt={option.login}
                        src={option.avatar_url}
                        style={{
                            height: '24px',
                            marginRight: '10px',
                            width: '24px',
                        }}
                    />
                    <span>{option.login}</span>
                </div>
            )}
            useCache={false}
        />
        // </div>
    );


}


export default Search;