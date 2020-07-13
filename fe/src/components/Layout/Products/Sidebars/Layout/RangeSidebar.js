import React from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

export const RangeSidebar = () => {
    return (
      <div className="sidebar mb-35">
        <p>Value less than: </p>
        <Slider 
        onChange={(value) => console.log(value)}
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
        max={1000}
        step={100}
        defaultValue={1000}
        //value={1000}
        railStyle={{ height: 10 }}
        />
      </div>
    );
  }
  