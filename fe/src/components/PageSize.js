import React from 'react';


const PageSize = (props) => {
  return (
    <div className="dropdown">
      <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Page Size
      </button>
      <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a className="dropdown-item" href="#">5</a>
        <a className="dropdown-item" href="#">10</a>
        <a className="dropdown-item" href="#">30</a>
      </div>
    </div>
  )
}

export default PageSize;
