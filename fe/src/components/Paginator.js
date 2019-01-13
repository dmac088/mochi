import React from 'react';
import { Pagination }  from 'react-bootstrap';

const Paginator = (props) => {
  return (
    <nav aria-label="Page navigation example">
      <Pagination>
        <Pagination.First />
        {renderPaginator(props.page.totalPages, props.changePage)}
        <Pagination.Last />
      </Pagination>
    </nav>
  )
}

const renderPaginator = (pages, changePage) => {
  return Array.apply(null, {length: pages}).map(Number.call,page => {
    return (
      <Pagination.Item key={page} className="page-link" id={page}  onClick={changePage}>{page+1}</Pagination.Item>
    )
  });
}

export default Paginator;
