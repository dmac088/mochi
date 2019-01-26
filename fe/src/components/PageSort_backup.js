import React, { Component } from 'react';
import { DropdownButton, MenuItem  } from 'react-bootstrap';
import { withRouter } from "react-router-dom";
import qs from 'query-string';

const sortAliases = {
  1:"name (desc)",
  2:"price (desc)",
}

class PageSort extends Component {

  changePageSort = (event) => {
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, {
                                                  sort: event.target.id,
                                                  page: 0,
                                                });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }


  render() {
    return (
      <DropdownButton
          id="pageSort"
          title={"Sort: " + sortAliases[this.props.sort]}>
         <MenuItem id="1" onClick={this.changePageSort} eventKey="1" active={this.props.sort === 1}>{sortAliases[1]}</MenuItem>
         <MenuItem id="2" onClick={this.changePageSort} eventKey="2" active={this.props.sort === 2}>{sortAliases[2]}</MenuItem>
      </DropdownButton>
    )
  }

}

export default withRouter(PageSort);
