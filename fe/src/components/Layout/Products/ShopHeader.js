import React from 'react';

function ShopHeader() {
    return (
        <div className="shop-header mb-35">
            <div className="row">
                <div className="col-lg-4 col-md-4 col-sm-12 d-flex align-items-center">
                    <div className="view-mode-icons mb-xs-10">
                        <a href="#" data-target="grid"><i className="fa fa-th"></i></a>
                        <a href="#" data-target="list" ><i className="fa fa-list"></i></a>
                    </div>
                </div>
                <div className="col-lg-8 col-md-8 col-sm-12 d-flex flex-column flex-sm-row justify-content-between align-items-left align-items-sm-center">
                    <div className="sort-by-dropdown d-flex align-items-center mb-xs-10">
                        <p className="mr-10">Sort By: </p>
                        <select name="sort-by" id="sort-by" className="nice-select">
                            <option value="nameAsc">Name [A-Z]</option>
                            <option value="nameDesc">Name [Z-A]</option>
                            <option value="priceAsc">Price: Low to High</option>
                            <option value="priceDesc">Price: High to Low</option>
                        </select>
                    </div>
                    <p className="result-show-message">Showing x of y results</p>
                </div>
            </div>
        </div>
    );
};


export default ShopHeader;