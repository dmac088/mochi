
import React, { Component } from 'react';
import { Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';
import _ from 'lodash';

class Search extends Component {

  constructor(props) {
    super(props);
      this.state = {
        inputTerm: "",
     };
  }

  componentWillMount() {
    let urlParams = (qs.parse(this.props.history.location.search));
    this.setState({
      inputTerm: urlParams.term
    });
  }

  updateSearch = (event) => {
    this.setState({
      inputTerm: event.target.value
    });
  }

  handleSearch = () => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(_.cloneDeep(urlParams), { term: this.state.inputTerm });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  handleKeyPress = (target) => {
    if(target.charCode === 13){
            this.handleSearch();
    }
  }

  render() {
      return(
        <Navbar.Form>
            <FormGroup controlId="formInlineName">
              <FormControl onChange={this.updateSearch} type="text" value={this.state.inputTerm} placeholder="Search..." onKeyPress={this.handleKeyPress} />
              <Button onClick={this.handleSearch} value={this.state.currentSearchTerm}>Search</Button>
            </FormGroup>
        </Navbar.Form>
      );
    }
}


export default withRouter(Search);
