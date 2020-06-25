import React from 'react';
import Product from './Product';
const $ = window.$;

const renderProducts = (props) => {
  const { category, products } = props;
  if (!products) { return; }
  return products.map(product => {
    return (
        <Product
          key={product.productUPC}
          product={product}
        />
      )
  });
}

const Column = (props) => {
  console.log(props);
  return (
    <div className="single-tab-slider-item">
        {renderProducts(props)}
    </div>
  )
}


export default Column;