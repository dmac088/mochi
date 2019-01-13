
import React from 'react';
import { Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';

const Search = (props) => {
    return(
      <React.Fragment>
          <FormGroup controlId="formInlineName">
            <FormControl type="text" placeholder="Search" />
          </FormGroup>
          <Button onClick={props.handleSearch}>Search</Button>
      </React.Fragment>
    );
}


export default Search;
