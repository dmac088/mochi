
import React, {Component} from 'react';

const Search = (props) => {
    return(
      <input
        className="form-control mr-sm-2"
        type="search"
        placeholder="Search"
        aria-label="Search" />
        <button
          className="btn btn-outline-success mr-sm-5 my-2 my-sm-0"
          type="submit">Search</button>
    );
}


export default Search;
