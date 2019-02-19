import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class PriceSidebar extends Component {

  render() {
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <div className="sidebar-price">
          <div id="price-range"></div>
          <input type="text" id="price-amount" readOnly />
        </div>
      </div>
    );
  }
}

export default PriceSidebar;
