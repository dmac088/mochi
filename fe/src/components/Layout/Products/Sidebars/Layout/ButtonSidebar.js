import React from 'react';

const renderItems = (items, modFacet) => {
  return items.map((i, index) => {
    return (
      <li key={index}>
        <a onClick={(e) => {
                              e.preventDefault();
                              modFacet(i);
                            }}>{`${i.data.desc} (${i.data.count})`}
        </a>
      </li>
    )
  })
}

export const ButtonSidebar = (props) => {
  return (
    <div className="sidebar mb-35">
      <h3 className="sidebar-title">{props.heading}</h3>
      <ul className="tag-container">
        {renderItems(props.items, props.modFacet)}
      </ul>
    </div>
  )
}

