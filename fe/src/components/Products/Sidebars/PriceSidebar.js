import React, { Component } from 'react';
import Slider, { Range } from 'rc-slider';
import qs from 'query-string';
import 'rc-slider/assets/index.css';

class PriceSidebar extends Component {

  componentDidUpdate(prevProps) {
    const { updateMaxPrice, category, brand  } = this.props;
    if(!category) { return }
    if(prevProps.category
      && prevProps.category.categoryCode === category.categoryCode
      && prevProps.brand === brand) { return }
    const maxPrice = ((!brand) ? category.maxMarkDownPrice : this.getMaxBrandPrice(category, brand));
    updateMaxPrice(maxPrice+1);
  }

  getMaxBrandPrice = (category, currentBrand) => {
    let maxPrice = category.maxMarkDownPrice;
    if(!category.categoryBrands) {return maxPrice}
    category.categoryBrands.map(brand => {
      if (currentBrand === brand.brandDesc) {
          maxPrice = brand.maxMarkDownPrice;
      }
    });
    return maxPrice;
  }


  render() {
    const { category, brand, updateMaxPrice, currentMaxPrice } = this.props;
    if(!category) { return null }
    const maxPrice = ((!brand) ? category.maxMarkDownPrice : this.getMaxBrandPrice(category, brand))+1;
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <p>Value: {(!currentMaxPrice) ? maxPrice : currentMaxPrice}</p>
          <Slider
            onChange={(value) => updateMaxPrice(value)}
            trackStyle={{ backgroundColor: '#80bb01', height: 10 }}
            handleStyle={{
              borderColor: '#80bb01',
              height: 28,
              width: 28,
              marginLeft: -14,
              marginTop: -9,
              backgroundColor: 'grey',
            }}
            min={0}
            max={maxPrice}
            defaultValue={maxPrice}
            value={(!currentMaxPrice) ? maxPrice : currentMaxPrice}
            railStyle={{ height: 10 }}/>

      </div>
    );
  }
}

export default PriceSidebar;
