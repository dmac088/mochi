import React from 'react';

const renderItems = (items, addFacet) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        <a onClick={(e) => {
                              e.preventDefault();
                              addFacet(i);
                            }}>{`${i.data.desc} (${i.data.count})`}
        </a>
      </li>
    )
  })
}

export const ListSidebar = (props) => {
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">{props.heading}</h3>
      <ul className="product-categories">
        {renderItems(props.items, props.addFacet)}
      </ul>
    </div>
  )
}
