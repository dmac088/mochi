import React, { Component } from 'react';
import Category from './Tabs/Category';

class Highlights extends Component {

  constructor(props) {
    super(props);
    this.state = {
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

  filterLandingCategories = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.landingDisplay === 1;
    });
  }

  renderTabHeaders = (categoryList) => {
    return this.filterLandingCategories(categoryList).map(category => {
      return (
        <a onClick={this.showTab} className="nav-item nav-link active" id={category.categoryId} data-toggle="tab" href="#{category.categoryDesc}" role="tab" aria-selected="true">
          {category.categoryDesc}
        </a>
      )
    });
  }

  renderTabs = (categoryList) => {
    return this.filterLandingCategories(categoryList).map(category => {
      return (
        <div className="tab-pane fade show active" id={category.categoryId} role="tabpanel" aria-labelledby="featured-tab">
          <Category
            {...this.props}
          />
        </div>
      )
    });
  }

  render() {
    const { showFeatured, showArrival, showOnSale } = this.state;
    const { categoryList } = this.props;
    return (
      <div className="slider tab-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="tab-slider-wrapper">
                  <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                      {this.renderTabHeaders(categoryList)}
                    </div>
                  </nav>
                  <div className="tab-content" id="nav-tabContent">
                    {this.renderTabs(categoryList)}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    )
  }
}

export default Highlights;
