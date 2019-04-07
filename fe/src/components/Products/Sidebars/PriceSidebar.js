import React, { Component } from 'react';
import Slider, { Range } from 'rc-slider';
import 'rc-slider/assets/index.css';

class PriceSidebar extends Component {


  getMaxBrandPrice = (brands, currentBrand) => {
    return brands.map(brand => {
      if (currentBrand === brand.brandDesc) {
          return brand.maxMarkDownPrice;
      }
    });
  }

  render() {
    const { category, brand } = this.props;
    if(!category) { return null }
    console.log(category);
    console.log(brand);
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <p>Value less than: {(!brand) ? category.maxMarkDownPrice : this.getMaxBrandPrice(category.categoryBrands, brand)}</p>
          <Slider
            defaultValue={30}
            trackStyle={{ backgroundColor: '#80bb01', height: 10 }}
            handleStyle={{
              borderColor: '#80bb01',
              height: 28,
              width: 28,
              marginLeft: -14,
              marginTop: -9,
              backgroundColor: 'grey',
            }}
            railStyle={{ height: 10 }}/>
      </div>
    );
  }
}

export default PriceSidebar;
