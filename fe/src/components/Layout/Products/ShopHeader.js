import React from 'react';
import queryString from 'query-string';

function ShopHeader(props) {

    const { page, history, changeGrid, type } = props;

    const changeSort = (e) => {
        e.preventDefault();
        const sort = e.target.value;
        const query = queryString.parse(history.location.search);

        const newQuery = {
            ...query,
            sort,
        }

        history.push({
            search: '?' + new URLSearchParams(newQuery).toString(),
        });
    } 
    
    return (
        <div className="shop-header mb-35">
            <div className="row">
                <div className="col-lg-4 col-md-4 col-sm-12 d-flex align-items-center">
                    <div className="view-mode-icons mb-xs-10">
                        <a id="grid" onClick={changeGrid} href="#"><i id="grid" className="fa fa-th"></i></a>
                        <a id="list" onClick={changeGrid} href="#"><i id="list" className="fa fa-list"></i></a>
                    </div>
                </div>
                <div className="col-lg-8 col-md-8 col-sm-12 d-flex flex-column flex-sm-row justify-content-between align-items-left align-items-sm-center">
                    <div className="sort-by-dropdown d-flex align-items-center mb-xs-10">
                        <p className="mr-10">Sort By: </p>
                        <select onChange={changeSort} name="sort-by" id="sort-by" className="nice-select">
                            {(type==='search') && <option value="bestMatch">Best match</option>}

                            <option value="nameAsc">Name [A-Z]</option>
                            <option value="nameDesc">Name [Z-A]</option>
                            <option value="priceAsc">Price: Low to High</option>
                            <option value="priceDesc">Price: High to Low</option>
                        </select>
                    </div>
                    <p className="result-show-message">Showing {page.number+1} of {page.totalPages} results</p>
                </div>
            </div>
        </div>
    );
};


export default ShopHeader;