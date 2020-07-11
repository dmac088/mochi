import React from 'react';
import Slider from 'rc-slider';

export default priceSlider = () => {
    return (
      <React.Fragment>
        <p>Value less than: </p>
        <Slider
          onChange={(value) => { console.log(value) }}
          trackStyle={{ backgroundColor: '#80bb01', height: 10 }}
          handleStyle={{
            borderColor: '#80bb01',
            height: 28,
            width: 28,
            marginLeft: -14,
            marginTop: -9,
            backgroundColor: 'grey',
          }}
          min={increment}
          max={20000}
          step={increment}
          defaultValue={20000}
          value={1000}
          railStyle={{ height: 10 }}/>
      </React.Fragment>
    );
  }
  