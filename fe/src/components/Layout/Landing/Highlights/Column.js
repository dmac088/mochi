import React from 'react';
import Product from './Product';
const $ = window.$;

const renderProducts = (props) => {
  const { products, category } = props;
  if (!products) { return; }
  return products.map((product, index) => {
    return (
        <Product
          {...props}
          key={index}
          category={category}
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