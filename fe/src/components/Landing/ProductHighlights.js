import React, { Component } from 'react';
import Featured from './Tabs/Featured';
import NewArrival from './Tabs/NewArrival';
import OnSale from './Tabs/OnSale';

class ProductHighlights extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showFeatured: true,
        showArrival: false,
        showOnSale: false,
    };
  }


   showTab = (e) => {
     e.preventDefault();
     this.setState({
       showFeatured: e.target.id === "featured-tab",
       showArrival: e.target.id === "new-arrival-tab",
       showOnSale: e.target.id === "nav-onsale-tab",
     });
   }

   render() {
    const { showFeatured, showArrival,  showOnSale } = this.state;
    return (
      <div className="slider tab-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="tab-slider-wrapper">
                  <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((showFeatured) ? "active" : "")} id="featured-tab" data-toggle="tab" href="#featured" role="tab" aria-selected="true">Featured</a>
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((showArrival) ? "active" : "")} id="new-arrival-tab" data-toggle="tab" href="#new-arrivals" role="tab" aria-selected="false">New Arrival</a>
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((showOnSale)? "active" : "")} id="nav-onsale-tab" data-toggle="tab" href="#on-sale" role="tab" aria-selected="false">On Sale</a>
                    </div>
                  </nav>
                  <div className="tab-content" id="nav-tabContent">
                    <div className={"tab-pane fade " + ((showFeatured) ? "show active" : "")} id="featured" role="tabpanel" aria-labelledby="featured-tab">
                      <Featured />
                    </div>
                    <div className={"tab-pane fade " + ((showArrival) ? "show active" : "")} id="new-arrivals" role="tabpanel" aria-labelledby="new-arrival-tab">
                      <NewArrival />
                    </div>
                    <div className={"tab-pane fade " + ((showOnSale)? "show active" : "")} id="on-sale" role="tabpanel" aria-labelledby="nav-onsale-tab">
                      <OnSale />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    )
  }
}

export default ProductHighlights;
