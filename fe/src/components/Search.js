
import React, {Component} from 'react';

const Search = (props) => {
    return(
      <div>
      <input
        onChange={props.handleSearch}
        className="form-control mr-sm-2"
        type="search"
        placeholder="Search"
        aria-label="Search"
      />
    <button className="btn btn-outline-success mr-sm-5 my-2 my-sm-0" type="submit">{props.lang.searchProduct}</button>
      </div>
    );
}


export default Search;
