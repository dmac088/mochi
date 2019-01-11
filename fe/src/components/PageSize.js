import React from 'react';
import { Link } from  'react-router-dom';

const PageSize = (props) => {
  return (
      <div className="btn-group">
        <button type="button" className="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Page Size ({props.currentPageSize})
        </button>
        <div className="dropdown-menu">
          <Link id="5" onClick={props.changePageSize} className="dropdown-item"  to="/">5</Link>
          <Link id="10" onClick={props.changePageSize} className="dropdown-item"  to="/">10</Link>
          <Link id="20" onClick={props.changePageSize} className="dropdown-item"  to="/">20</Link>
        </div>
      </div>
    )
};

export default PageSize;
