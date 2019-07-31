import React from 'react';
import Slider, { Range } from 'rc-slider';
import qs from 'query-string';
import 'rc-slider/assets/index.css';

export const PriceSidebar = (props) => {
    const { maxPrice, selectedPrice, selectedFacets, updateSelectedPrice, facets, applyFacet, type } = props;
    console.log("maxPrice="+maxPrice);
    //console.log(selectedPrice);

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">Filter By Price</h3>
        {(type === "category")  ? priceSlider(maxPrice, selectedPrice, updateSelectedPrice)
                                : priceRanges(maxPrice, selectedFacets, applyFacet, facets, props)}
      </div>
    );
  }

const priceRanges = (maxPrice, selectedFacets, applyFacet, facets, props) => {
    return facets.map(facet => {
      return(
        <li key={facet.token}>
          <a  className={(props.isActive(facet, selectedFacets, facets)) ? "active" : ""}
              onClick={(e) => {
                                e.preventDefault();
                                props.applyFacet(e, props);
                              }}
              id={facet.token}
              href="#">
            {facet.facetDisplayValue} ({facet.productCount})
          </a>
        </li>
      );
    });
}

const priceSlider = (maxPrice, selectedPrice, updateSelectedPrice ) => {
  const increment = Math.round((maxPrice / 4) * 100) / 100;
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
