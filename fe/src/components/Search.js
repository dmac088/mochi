
import React, { Component } from 'react';
import { Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

class Search extends Component {

  constructor(props) {
    super(props);
      this.state = {
       term: "",
    };
  }

  updateSearch = (event) => {
    this.setState({
      term: event.target.value
    });
  }

  handleSearch = (event) => {
    //get the query parameters
    const query =  { term: this.state.term };
    const searchString = qs.stringify(query);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  render() {
      return(
        <Navbar.Form>
            <FormGroup controlId="formInlineName">
              <FormControl onChange={this.updateSearch} type="text" placeholder="Search" />
            </FormGroup>
            <Button onClick={this.handleSearch} value={this.state.currentSearchTerm}>Search</Button>
        </Navbar.Form>
      );
    }
}


export default withRouter(Search);
