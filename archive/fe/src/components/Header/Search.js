
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { isMobile, slide } from '../../services/helpers/uiHelper';
import { updateParams } from '../../services/helpers/functionHelper';
import { routeSearch, createRouteProps} from '../../services/helpers/routeHelper';
import qs from 'query-string';
import _ from 'lodash';

class Search extends Component {

  constructor(props) {
    super(props);
      this.state = {
        "inputTerm": "",
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
      const routeProps = createRouteProps(this.props.history, this.props.match, this.props.location);
      return(
        <div className="header-advance-search">
          <form action="#">
            <input type="text" onChange={this.updateSearch} value={this.state.inputTerm} placeholder="Search your product" />
            <button onClick={(e) => routeSearch(e, routeProps, this.state.inputTerm)}><span className="icon_search" /></button>
          </form>
        </div>
      );
    }
}


export default withRouter(Search);
