import React, { Component } from 'react';
import Product from './Product';
const $ = window.$;

class Column extends Component {

  constructor(props) {
    super(props);
  }

  renderProducts = (products) => {
    const { categoryDesc, match, history, setCurrentProductId } = this.props;
    if (products === undefined) { return; }
    return products.map(product => {
      return (
          <Product
            key={product.productId}
            match={match}
            history={history}
            product={product}
            categoryDesc={categoryDesc}
            setCurrentProductId={setCurrentProductId}
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

export default Column;
