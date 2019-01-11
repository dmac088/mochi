import React from 'react';

const PageSize = (props) => {
  return (
      <div className="btn-group">
        <button type="button" className="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Page Size ({props.currentPageSize})
        </button>
        <ul className="dropdown-menu">
          <li id="5" onClick={props.changePageSize} className="dropdown-item">5</li>
          <li id="10" onClick={props.changePageSize} className="dropdown-item">10</li>
          <li id="20" onClick={props.changePageSize} className="dropdown-item">20</li>
        </ul>
      </div>
    )
};

export default PageSize;
