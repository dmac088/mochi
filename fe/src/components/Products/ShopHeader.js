import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class ShopHeader extends Component {

  render() {
    return (
      <div class="shop-header mb-35">
        <div class="row">
          <div class="col-lg-4 col-md-4 col-sm-12 d-flex align-items-center">
            <div class="view-mode-icons mb-xs-10">
              <a href="#" data-target="grid"><i class="fa fa-th"></i></a>
              <a class="active" href="#" data-target="list"><i class="fa fa-list"></i></a>
            </div>
          </div>
          <div class="col-lg-8 col-md-8 col-sm-12 d-flex flex-column flex-sm-row justify-content-between align-items-left align-items-sm-center">
            <div class="sort-by-dropdown d-flex align-items-center mb-xs-10">
              <p class="mr-10">Sort By: </p>
              <select name="sort-by" id="sort-by" class="nice-select">
                <option value="0">Sort By Popularity</option>
                <option value="0">Sort By Average Rating</option>
                <option value="0">Sort By Newness</option>
                <option value="0">Sort By Price: Low to High</option>
                <option value="0">Sort By Price: High to Low</option>
              </select>
            </div>
            <p class="result-show-message">Showing 1–12 of 41 results</p>
          </div>
        </div>
      </div>
    );
  }
}

export default ShopHeader;
