import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class CompareSidebar extends Component {

  render() {
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Compare</h3>
        <ul className="product-list">
          <li>
            <a href="single-product.html" className="remove" title="Remove">x</a>
            <a className="title" href="single-product.html">Cillum dolore tortor nisl fermentum</a>
          </li>
          <li>
            <a href="single-product.html" className="remove" title="Remove">x</a>
            <a className="title" href="single-product.html">Condimentum posuere consectetur</a>
          </li>
        </ul>
        <div className="compare-btns">
          <a href="#" className="clear-all">Clear all</a>
          <a href="#" className="compare">Compare</a>
        </div>
      );
  }
}

export default CompareSidebar;
