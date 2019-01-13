
import React from 'react';
import { Button } from 'react-bootstrap';

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
        <Button type="submit">{props.lang.searchProduct}</Button>
      </div>
    );
}


export default Search;
