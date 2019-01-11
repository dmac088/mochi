import React from 'react';

const PageSize = (props) => {
  return (
      <div className="btn-group">
        <button type="button" className="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Page Size ({props.currentPageSize})
        </button>
        <div className="dropdown-menu">
          <a id="5" onClick={props.changePageSize} className="dropdown-item" href="#">5</a>
          <a id="10" onClick={props.changePageSize} className="dropdown-item" href="#">10</a>
          <a id="20" onClick={props.changePageSize} className="dropdown-item" href="#">20</a>
        </div>
      </div>
    )
};

export default PageSize;
