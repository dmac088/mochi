import React, { Component } from 'react';
import HighlightedProduct from './HighlightedProduct';
const $ = window.$;

class HighlightedColumn extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="single-tab-slider-item">
        <HighlightedProduct />
        <HighlightedProduct />
        <HighlightedProduct />
      </div>
    )
  }
}

export default HighlightedColumn;
