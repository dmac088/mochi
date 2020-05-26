import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Product from './Product';
const $ = window.$;

const renderProducts = (props) => {
  const { category, match, history, setCurrentProductId, products } = props;
  if (products === undefined) { return; }
  return products.map(product => {
    return (
      <Product
        key={product.productUPC}
        product={product}
        category={category}
        setCurrentProductId={setCurrentProductId}
      />
    )
  });
}

const Column = () => {
  return (
    <div className="single-tab-slider-item">
      
    </div>
  )
}


export default Column;