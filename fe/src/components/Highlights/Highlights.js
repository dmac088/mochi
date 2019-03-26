import React, { Component } from 'react';
import Category from './Tabs/Category';

class Highlights extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "selectedCategory": null,
    };
  }

  componentDidUpdate() {
    this.updateData();
  }

  updateData = () => {
    const { landingCategories } = this.props;
    if(!landingCategories) { return; }
    if(landingCategories.length >= 0) { return; }
    this.setState({
      "selectedCategory": landingCategories[0].categoryDesc,
    });
  }

  showTab = (e) => {
     e.preventDefault();
     this.setState({
       "selectedCategory": e.currentTarget.id,
     }, () => {
       console.log(this.state.selectedCategory);
     });
  }

  renderTabHeaders = (categoryList) => {
    return categoryList.map(category => {
      return (
        <a  key={category.categoryId}
            onClick={this.showTab}
            className="nav-item nav-link active"
            id={category.categoryDesc}
            data-toggle="tab"
            href="#{category.categoryDesc}"
            role="tab"
            aria-selected="true">
          {category.categoryDesc}
        </a>
      )
    });
  }

  renderTabs = (categoryList) => {
    const { match, history, setCurrentProductId } = this.props;
    return categoryList.map(category => {
      return (
        <div key={category.categoryId}
             className="tab-pane fade show active"
             id={category.categoryDesc}
             role="tabpanel"
             aria-labelledby="featured-tab">
          <Category
            category={category}
            match={match}
            history={history}
            setCurrentProductId={setCurrentProductId}
          />
        </div>
      )
    });
  }

  render() {
    const { showFeatured, showArrival, showOnSale } = this.state;
    const { landingCategories } = this.props;
    return (
      <div className="slider tab-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="tab-slider-wrapper">
                  <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                      {this.renderTabHeaders(landingCategories)}
                    </div>
                  </nav>
                  <div className="tab-content" id="nav-tabContent">
                    {this.renderTabs(landingCategories)}
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
