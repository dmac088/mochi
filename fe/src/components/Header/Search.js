
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { isMobile, slide, updateParams } from '../../services/helpers/Helper';
import { routeSearch } from '../../services/helpers/RouteHelper';
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
    this.setState({
      inputTerm: urlParams.term
    });
  }

  updateSearch = (e) => {
    this.setState({
      inputTerm: e.currentTarget.value
    });
  }

  handleKeyPress = (target) => {
    if(target.charCode === 13){
      this.handleSearch();
    }
  }

  render() {
      const { history, match } = this.props;
      return(
        <div className="header-advance-search">
          <form action="#">
            <input type="text" onChange={this.updateSearch} value={this.state.inputTerm} placeholder="Search your product" />
            <button onClick={(e) => routeSearch(e, match, history, this.state.inputTerm)}><span className="icon_search" /></button>
          </form>
        </div>
      );
    }
}


export default withRouter(Search);
