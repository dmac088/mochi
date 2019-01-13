
import React from 'react';
import { Button, Form, FormGroup, FormControl } from 'react-bootstrap';

const Search = (props) => {
    return(
      <Form inline>
        <FormGroup controlId="formInlineName">
          <FormControl type="text" placeholder="Search" />
        </FormGroup>
        <Button>Search</Button>
      </Form>
    );
}


export default Search;
