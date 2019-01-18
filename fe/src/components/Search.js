
import React from 'react';
import { Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';

const Search = (props) => {
    return(
      <Navbar.Form>
          <FormGroup controlId="formInlineName">
            <FormControl onChange={props.updateSearch} type="text" placeholder="Search" />
          </FormGroup>
          <Button onClick={props.handleSearch}>Search</Button>
      </Navbar.Form>
    );
}


export default Search;
