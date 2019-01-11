import React from 'react';


const Paginator = (props) => {
  return (
    <nav aria-label="Page navigation example">
      <ul className="pagination">
        <li className="page-item">
          <a className="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">«</span>
            <span className="sr-only">Previous</span>
          </a>
        </li>
        {renderPaginator(props.page.totalPages, props.changePage)}
        <li className="page-item">
          <a className="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">»</span>
            <span className="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav>
  )
}

const renderPaginator = (pages, changePage) => {
  console.log("pages = " +  pages);
  return Array.apply(null, {length: pages}).map(Number.call,page => {
    return (
      <React.Fragment>
          <li key={page} className="page-item"><a id={page} className="page-link" onClick={changePage} href="#">{page+1}</a></li>
      </React.Fragment>
    )
  });
}

export default Paginator;
