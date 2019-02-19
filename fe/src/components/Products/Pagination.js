import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class Pagination extends Component {

    render() {
			return(
        <div class="pagination-container">
          <div class="container">
            <div class="row">
              <div class="col-lg-12">
                <div class="pagination-content text-center">
                  <ul>
                    <li><a class="active" href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
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
