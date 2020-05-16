import React from "react";

function Search() {
    return (
        <div className="header-advance-search">
            <form action="#">
                <input type="text" placeholder="Search your product" />
                <button><span className="icon_search"></span></button>
            </form>
        </div>
    );
}

export default Search;