import React, { Component } from 'react';
import Slider, { Range } from 'rc-slider';
import qs from 'query-string';
import 'rc-slider/assets/index.css';

class PriceSidebar extends Component {


  componentDidMount() {
    console.log("componentDidMount");
  }

  componentDidUpdate(prevProps) {
    const { updateMaxPrice } = this.props;
    if(!prevProps.category || !this.props.category) { return }
    if(prevProps.category.categoryCode === this.props.category.categoryCode
      && prevProps.brand === this.props.brand) { return }
    const { category, brand } = this.props;
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
    const maxPrice = ((!brand) ? category.maxMarkDownPrice : this.getMaxBrandPrice(category, brand));
    if(!currentMaxPrice) { updateMaxPrice(maxPrice+1) }
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <p>Value: {(!currentMaxPrice) ? maxPrice+1 : currentMaxPrice}</p>
          <Slider
            onChange={(value) => updateMaxPrice(value)}
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
            min={0}
            max={maxPrice+1}
            defaultValue={maxPrice+1}
            value={(!currentMaxPrice) ? maxPrice+1 : currentMaxPrice}
            railStyle={{ height: 10 }}/>

      </div>
    );
  }
}

export default PriceSidebar;
