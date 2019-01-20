
import React, { Component } from 'react';
import { Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';

class Search extends Component {

  constructor(props) {
    super(props);
      this.state = {
       currentSearchTerm: "-Z",
    };
  }

  updateSearch = (event) => {
    this.setState({
      currentSearchTerm: event.target.value
    });
  }

  render() {
      return(
        <Navbar.Form>
            <FormGroup controlId="formInlineName">
              <FormControl onChange={this.updateSearch} type="text" placeholder="Search" />
            </FormGroup>
            <Button onClick={this.props.handleSearch} value={this.state.currentSearchTerm}>Search</Button>
        </Navbar.Form>
      );
    }
}


export default Search;
