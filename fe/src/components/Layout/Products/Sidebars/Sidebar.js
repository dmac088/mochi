import React from 'react';
import { Link } from "react-router-dom";

const renderItems = (items, modFacet, filterType) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        <a onClick={(e) => {  e.preventDefault();
                              modFacet(i.code, filterType, i.display);
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
      <h3 className="sidebar-title">{props.heading}</h3>
      <ul className="product-categories">
        {renderItems(props.items, props.modFacet, props.filterType)}
      </ul>
    </div>
  )
}
