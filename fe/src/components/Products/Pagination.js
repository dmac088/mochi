import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class Pagination extends Component {

    render() {
			return(
        <div className="pagination-container">
          <div className="container">
            <div className="row">
              <div className="col-lg-12">
                <div className="pagination-content text-center">
                  <ul>
                    <li><a className="active" href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#"><i className="fa fa-angle-right"></i></a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      )
    }
}

export default Pagination;
