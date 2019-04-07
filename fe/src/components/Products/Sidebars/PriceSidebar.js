import React, { Component } from 'react';
import Slider, { Range } from 'rc-slider';
import 'rc-slider/assets/index.css';

class PriceSidebar extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "value": null,
    };
  }

  componentDidUpdate(prevProps) {
    if(!prevProps.category || !this.props.category) { return }
    if(prevProps.category.categoryCode === this.props.category.categoryCode
      && prevProps.brand === this.props.brand) { return }
    const { category, brand } = this.props;
    const maxPrice = this.getMaxBrandPrice(category.categoryBrands, brand);
    this.setState({
      "value": maxPrice,
    })
  }

  getMaxBrandPrice = (brands, currentBrand) => {
    let maxPrice = 0;
    brands.map(brand => {
      if (currentBrand === brand.brandDesc) {
          maxPrice = brand.maxMarkDownPrice;
      }
    });
    return maxPrice;
  }

  changeValue = (value) => {
    this.setState({
      "value": value,
    })
  }

  render() {
    const { category, brand } = this.props;
    console.log(brand);
    if(!category) { return null }
    const maxPrice = ((!brand) ? category.maxMarkDownPrice : this.getMaxBrandPrice(category.categoryBrands, brand));
    console.log(this.state.value)
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <p>Value: {(!this.state.value) ? maxPrice : this.state.value}</p>
          <Slider
            onChange={this.changeValue}
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
            max={maxPrice}
            defaultValue={maxPrice}
            value={(!this.state.value) ? maxPrice : this.state.value}
            railStyle={{ height: 10 }}/>

      </div>
    );
  }
}

export default PriceSidebar;
