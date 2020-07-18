import React from 'react';
import queryString from 'query-string';

function Pagination(props) {
    const { totalPages, number } = props.page;
    const { history } = props;

    const changePage = (e) => {
        e.preventDefault();
        const page = e.target.id;

        const query = queryString.parse(history.location.search);

        const newQuery = {
            ...query,
            page,
        }

        history.push({
            search: '?' + new URLSearchParams(newQuery).toString(),
        });
    }

    const renderPaginator = (pages, current) => {
        return Array.apply(null, {length: pages}).map(Number.call,page => {
          return (
            <li key={page}>
              <a
                  id={page}
                  href="#"
                  className={(Number(page) ===  Number(current)) ?  "active" : ""}
                  onClick={changePage}>
                {page+1}
              </a>
            </li>
          )
        });
      }

    return (
        <div className="pagination-container">
            <div className="container">
                <div className="row">
                    <div className="col-lg-12">
                        <div className="pagination-content text-center">
                            <ul>
                                {renderPaginator(totalPages, number)}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};


export default Pagination;