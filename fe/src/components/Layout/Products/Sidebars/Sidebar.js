import React from 'react';

export const Sidebar = (props) => {
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">Filter By {props.name}</h3>
      <ul className="product-categories">
        {/* {renderFacets(facets, selectedFacets, routeProps, props)} */}
      </ul>
    </div>
  )
}
