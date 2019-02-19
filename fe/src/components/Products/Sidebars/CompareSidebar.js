import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class CompareSidebar extends Component {

  render() {
    return (
      <div class="sidebar mb-35">
        <h3 class="sidebar-title">Compare</h3>
        <ul class="product-list">
          <li>
            <a href="single-product.html" class="remove" title="Remove">x</a>
            <a class="title" href="single-product.html">Cillum dolore tortor nisl fermentum</a>
          </li>
          <li>
            <a href="single-product.html" class="remove" title="Remove">x</a>
            <a class="title" href="single-product.html">Condimentum posuere consectetur</a>
          </li>
        </ul>
        <div class="compare-btns">
          <a href="#" class="clear-all">Clear all</a>
          <a href="#" class="compare">Compare</a>
        </div>
      );
  }
}

export default CompareSidebar;
