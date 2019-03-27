
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { isMobile, slide, updateParams } from '../../services/helpers/Helper';
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
    const urlParams = (qs.parse(this.props.history.location.search));
    console.log(this.props.history.location.search);
    this.setState({
      inputTerm: urlParams.term
    });
  }

  updateSearch = (e) => {
    this.setState({
      inputTerm: e.currentTarget.value
    }, () => {
      console.log(this.state.inputTerm);
    });
  }

  handleSearch = (e) => {
    if(!e) {return}
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push({
      "pathname": '/' + locale + '/' + currency + '/search',
      "search": updateParams(this.props.history.location.search, {term: this.state.inputTerm, page: 0}, this.props.history),
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
