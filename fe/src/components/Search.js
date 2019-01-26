
import React, { Component } from 'react';
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
    console.log(event.target.value);
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
        <div className="header-advance-search">
          <form action="#">
            <input type="text" onChange={this.updateSearch} value={this.state.inputTerm} placeholder="Search your product" />
            <button onClick={this.handleSearch}><span className="icon_search" /></button>
          </form>
        </div>
      );
    }
}


export default withRouter(Search);
