import React from 'react';

const renderItems = (items) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        {i.name}
      </li>
    )
  })
}

export const Sidebar = (props) => {
  
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">Filter By {props.filterType}</h3>
      <ul className="product-categories">
        {renderItems(props.items)}
      </ul>
    </div>
  )
}
