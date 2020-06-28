import React from 'react';
import { Link } from "react-router-dom";

const renderItems = (items) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        <Link to={i.path}>
          {i.name}
        </Link>
      </li>
    )
  })
}

export const Sidebar = (props) => {
  console.log(props);
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">Filter By {props.filterType}</h3>
      <ul className="product-categories">
        {renderItems(props.items)}
      </ul>
    </div>
  )
}
