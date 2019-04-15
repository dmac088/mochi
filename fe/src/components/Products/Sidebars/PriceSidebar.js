import React from 'react';
import Slider, { Range } from 'rc-slider';
import qs from 'query-string';
import 'rc-slider/assets/index.css';

export const PriceSidebar = (props) => {
    const { maxPrice, selectedPrice, updateSelectedPrice } = props;
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        <p>Value: {(selectedPrice) ? selectedPrice : maxPrice}</p>
          <Slider
            onChange={(value) => updateSelectedPrice(value)}
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
            value={(selectedPrice) ? selectedPrice : maxPrice}
            railStyle={{ height: 10 }}/>
      </div>
    );
  }
