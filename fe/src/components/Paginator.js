import React from 'react';
import { Pager, Pagination }  from 'react-bootstrap';

const Paginator = (props) => {
  return (
    <nav aria-label="Page navigation example">
      <Pagination>
        <Pagination.Item bsSize="medium">
            <span aria-hidden="true">«</span>
            <span className="sr-only">Previous</span>
        </Pagination.Item>
        {renderPaginator(props.page.totalPages, props.changePage)}
        <Pagination.Item bsSize="medium">
            <span aria-hidden="true">»</span>
            <span className="sr-only">Next</span>
        </Pagination.Item>
      </Pagination>
    </nav>
  )
}

const renderPaginator = (pages, changePage) => {
  console.log("pages = " +  pages);
  return Array.apply(null, {length: pages}).map(Number.call,page => {
    return (
      <Pagination.Item bsSize="medium" key={page} className="page-link" id={page}  onClick={changePage} href="#">{page+1}</Pagination.Item>
    )
  });
}

export default Paginator;
