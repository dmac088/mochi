import React from 'react';
import Category from './Tabs/Category';

function Highlights() {

  return (
    <div className="slider tab-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12"> 
            <div className="tab-slider-wrapper">
              <nav>
                <div className="nav nav-tabs" id="nav-tab" role="tablist">

                </div>
              </nav>
              <div className="tab-content" id="nav-tabContent">
                <Category />
                <Category />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Highlights;
