import React from 'react';
import { useSelector } from 'react-redux';
import Category from './Tabs/Category';

function Highlights() {

  const categories = useSelector(state => state.categories.list);

  console.log(categories);

  return (
    <div className="slider tab-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            <div className="tab-slider-wrapper">
              <nav>
                <div className="nav nav-tabs" id="nav-tab" role="tablist">
                  <a className="nav-item nav-link active" data-toggle="tab" href="#" role="tab" aria-selected="true">Category A</a>
                  <a className="nav-item nav-link" data-toggle="tab" href="#" role="tab" aria-selected="true">Category B</a>
                </div>
              </nav>
              <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" role="tabpanel" aria-labelledby="featured-tab">
                  <Category />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Highlights;
