import React from 'react';


const Paginator = (props) => {
  return (
    <nav aria-label="Page navigation example">
      <ul className="pagination">
        <li className="page-link">
            <span aria-hidden="true">«</span>
            <span className="sr-only">Previous</span>
        </li>
        {renderPaginator(props.page.totalPages, props.changePage)}
        <li className="page-link">
            <span aria-hidden="true">»</span>
            <span className="sr-only">Next</span>
        </li>
      </ul>
    </nav>
  )
}

const renderPaginator = (pages, changePage) => {
  console.log("pages = " +  pages);
  return Array.apply(null, {length: pages}).map(Number.call,page => {
    return (
      <li key={page} className="page-link" id={page}  onClick={changePage} href="#">{page+1}</li>
    )
  });
}

export default Paginator;
