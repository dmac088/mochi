import React, { Component } from 'react';
import HighlightedProduct from './HighlightedProduct';
const $ = window.$;

class HighlightedColumn extends Component {

  constructor(props) {
    super(props);
  }

  renderProducts = (products) => {
    if (products === undefined) { return; }
    return products.map(product => {
      return (
          <HighlightedProduct
            key={product.productId}
            product={product}
            setCurrentProductId={this.props.setCurrentProductId}
          />
        )
    });
  }

  render() {
    const { products, key } = this.props;
    return (
      <div key={key} className="single-tab-slider-item">
        {this.renderProducts(products)}
      </div>
    )
  }
}

export default HighlightedColumn;
