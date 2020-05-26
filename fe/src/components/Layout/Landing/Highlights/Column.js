import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Product from './Product';
const $ = window.$;

const Column = () => {
  return (
    <div className="single-tab-slider-item">
        <Product />
        <Product />
    </div>
  )
}


export default Column;