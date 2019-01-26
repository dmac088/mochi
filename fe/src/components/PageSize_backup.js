import React, { Component } from 'react';
import { DropdownButton, MenuItem  } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';


class PageSize extends Component {

  changePageSize = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, { size: event.target.id });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }


  render() {
    return (
      <DropdownButton
          id="pageSize"
          title={"Page Size (" + this.props.size + ")"}
        >
         <MenuItem id="5" onClick={this.changePageSize} eventKey="5" active={this.props.size === "5"}>5</MenuItem>
         <MenuItem id="10" onClick={this.changePageSize} eventKey="10" active={this.props.size === "10"}>10</MenuItem>
         <MenuItem id="20" onClick={this.changePageSize} eventKey="20" active={this.props.size === "20"}>20</MenuItem>
      </DropdownButton>
    )
  }

};

export default withRouter(PageSize);
