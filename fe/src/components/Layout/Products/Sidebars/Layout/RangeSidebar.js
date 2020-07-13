import React from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

export const RangeSidebar = (props) => {
  const { currentPrice, maxPrice, changePrice } = props;
  console.log(currentPrice);
    return (
      <div className="sidebar mb-35">
        <p>Value less than: {currentPrice}</p>
        <Slider 
        onChange={(value) => changePrice(value)}
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
        step={maxPrice / 5}
        defaultValue={maxPrice}
        value={currentPrice}
        railStyle={{ height: 10 }}
        />
      </div>
    );
  }
  