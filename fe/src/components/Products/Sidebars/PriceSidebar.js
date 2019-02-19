import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class PriceSidebar extends Component {

  render() {
    return (
      <div class="sidebar mb-35">
        <h3 class="sidebar-title">Filter By Price</h3>
        <div class="sidebar-price">
          <div id="price-range"></div>
          <input type="text" id="price-amount" readonly />
        </div>
      </div>
    );
  }
}

export default PriceSidebar;
