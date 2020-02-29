import React from 'react';
import Slider, { Range } from 'rc-slider';
import qs from 'query-string';
import 'rc-slider/assets/index.css';

export const PriceSidebar = (props) => {
    const { maxPrice, selectedPrice, selectedFacets, updateSelectedPrice, facets, updateFacets, type } = props;
    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        {(type === "category")  ? priceSlider(maxPrice, selectedPrice, updateSelectedPrice)
                                : priceRanges(maxPrice, selectedFacets.prices, updateFacets, facets, props)}
      </div>
    );
  }

const priceRanges = (maxPrice, selectedFacets, updateFacets, facets, props) => {
    if(!facets) { return null; }
    return facets.map(facet => {
      return(
        <li key={facet.token}>
          <a  className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""}
              onClick={(e) => {
                                e.preventDefault();
                                props.updateFacets(e, props);
                              }}
              id={facet.token}
              href="#">
            {facet.facetDisplayValue} ({facet.facetProductCount})
          </a>
        </li>
      );
    });
}

const priceSlider = (maxPrice, selectedPrice, updateSelectedPrice ) => {
  //increment must have an odd devisor
  const increment = Math.round((maxPrice / 5) * 100) / 100;
  return (
    <React.Fragment>
      <p>Value less than: {(selectedPrice) ? selectedPrice : maxPrice}</p>
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
        min={increment}
        max={maxPrice}
        step={increment}
        defaultValue={maxPrice}
        value={(selectedPrice) ? selectedPrice : maxPrice}
        railStyle={{ height: 10 }}/>
    </React.Fragment>
  );
}
