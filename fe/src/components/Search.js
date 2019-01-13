
import React from 'react';
import { Button, Form, FormGroup, ControlLabel, FormControl } from 'react-bootstrap';

const Search = (props) => {
    return(
      <Form inline>
        <FormGroup controlId="formInlineName">
          <FormControl type="text" placeholder="Search" />
        </FormGroup>
        <Button type="submit">Search</Button>
      </Form>
    );
}


export default Search;
