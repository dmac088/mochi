import React, { Component } from 'react';
import Featured from './Tabs/Featured';
import NewArrival from './Tabs/NewArrival';
import OnSale from './Tabs/OnSale';

class ProductHighlights extends Component {

  constructor(props) {
    super(props);
    this.state = {
        theposition: 0,
        showTab: "featured-tab",
    };
  }


   showTab = (e) => {
     e.preventDefault();
     this.setState({
       showTab: e.target.id
     });
   }

   render() {
    return (
      <div className="slider tab-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="tab-slider-wrapper">
                  <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((this.state.showTab === "featured-tab") ? "active" : "")} id="featured-tab" data-toggle="tab" href="#featured" role="tab" aria-selected="true">Featured</a>
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((this.state.showTab === "new-arrival-tab") ? "active" : "")} id="new-arrival-tab" data-toggle="tab" href="#new-arrivals" role="tab" aria-selected="false">New Arrival</a>
                      <a onClick={this.showTab} className={"nav-item nav-link " + ((this.state.showTab === "nav-onsale-tab") ? "active" : "")} id="nav-onsale-tab" data-toggle="tab" href="#on-sale" role="tab" aria-selected="false">On Sale</a>
                    </div>
                  </nav>
                  <div className="tab-content" id="nav-tabContent">
                    <div className={"tab-pane fade " + ((this.state.showTab === "featured-tab") ? "show active" : "")} id="featured" role="tabpanel" aria-labelledby="featured-tab">
                      <Featured />
                    </div>
                    <div className={"tab-pane fade " + ((this.state.showTab === "new-arrival-tab") ? "show active" : "")} id="new-arrivals" role="tabpanel" aria-labelledby="new-arrival-tab">
                      <NewArrival />
                    </div>
                    <div className={"tab-pane fade " + ((this.state.showTab === "nav-onsale-tab") ? "show active" : "")} id="on-sale" role="tabpanel" aria-labelledby="nav-onsale-tab">
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
