import React, { Component } from 'react';
import { DropdownButton, MenuItem  } from 'react-bootstrap';
import {
  Route,
  withRouter,
} from 'react-router-dom';
import qs from 'query-string';


class PageSize extends Component {

  constructor(props) {
    super(props);
  }

  changePageSize = (event) => {
    const query =  { size: event.target.id };
    const searchString = qs.stringify(query);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }


  render() {
    console.log("rendering PageSize");
    return (
      <DropdownButton
          id="pageSize"
          title={"Page Size (" + this.props.currentPageSize + ")"}
        >
         <MenuItem id="5" onClick={this.changePageSize} eventKey="5" active={this.props.currentPageSize === "5"}>5</MenuItem>
         <MenuItem id="10" onClick={this.changePageSize} eventKey="10" active={this.props.currentPageSize === "10"}>10</MenuItem>
         <MenuItem id="20" onClick={this.changePageSize} eventKey="20" active={this.props.currentPageSize === "20"}>20</MenuItem>
      </DropdownButton>
    )
  }

};

export default withRouter(PageSize);
