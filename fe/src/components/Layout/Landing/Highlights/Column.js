import React from 'react';
import Product from './Product';
const $ = window.$;

const renderProducts = (props) => {
  const { products } = props;
  if (!products) { return; }
  return products.map((product, index) => {
    return (
        <Product
          key={index}
          product={product}
        />
      )
  });
}

const Column = (props) => {
  return (
    <div className="single-tab-slider-item">
        {renderProducts(props)}
    </div>
  )
}


export default Column;