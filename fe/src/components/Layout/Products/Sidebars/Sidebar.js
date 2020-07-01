import React from 'react';
import { Link } from "react-router-dom";

const renderItems = (items, addFacet, filterType) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        <a onClick={(e) => {  e.preventDefault();
                              addFacet(i.code, filterType, i.display);
                    }}>{i.display}</a>
        {/* <Link to={i.path}>
          {i.name}
        </Link> */}
      </li>
    )
  })
}

export const Sidebar = (props) => {
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">Filter By {props.filterType}</h3>
      <ul className="product-categories">
        {renderItems(props.items, props.addFacet, props.filterType)}
      </ul>
    </div>
  )
}
