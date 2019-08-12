import React, { Component } from 'react';
import { Category } from './Tabs/Category';

class Highlights extends Component {


  constructor(props) {
    super(props)
    this.state = {
      "selectedCategory": null,
    }
  }

  showTab = (e) => {
     e.preventDefault();
     this.setState({
       "selectedCategory": e.currentTarget.id,
     })
  }

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    this.reset();
  }

  reset = () => {
    const { landingCategories } = this.props;
    const { selectedCategory } = this.state;

    if(landingCategories.length === 0) {return;}
    if(selectedCategory) { return; }
    //reset the current category to first in array
    this.setState({
      "selectedCategory": landingCategories[0].facetId,
    });
  }


  renderTabHeaders = (categoryList, selectedCategory) => {
    return categoryList.map(c => {
      const isActive = (c.facetId === selectedCategory);
      return (
        <a  key={c.facetId}
            onClick={this.showTab}
            className={"nav-item nav-link " + ((isActive) ? " active" : "")}
            id={c.facetId}
            data-toggle="tab"
            href="#{c.facetId}"
            role="tab"
            aria-selected="true">
          {c.facetDisplayValue}
        </a>
      )
    });
  }

  renderTabs = (categoryList, selectedCategory) => {
    const { match, history, setCurrentProductId } = this.props;
    return categoryList.map(c => {
      
      const isActive = (c.facetId === selectedCategory);
      return (
        <div key={c.facetId}
             className={"tab-pane fade "  + ((isActive) ? " show active" : "")}
             id={c.facetId}
             role="tabpanel"
             aria-labelledby="featured-tab">
          <Category
            category={c}
            setCurrentProductId={setCurrentProductId}
          />
        </div>
      )
    });
  }

  render() {
    const { landingCategories } = this.props;
    const { selectedCategory } = this.state;
    if(landingCategories.length === 0) { return null; }
    return (
      <div className="slider tab-slider mb-35">
        <div className="container">
          <div className="row">
            <div className="col-lg-12">
              <div className="tab-slider-wrapper">
                  <nav>
                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                      {this.renderTabHeaders(landingCategories, selectedCategory)}
                    </div>
                  </nav>
                  <div className="tab-content" id="nav-tabContent">
                    {this.renderTabs(landingCategories, selectedCategory)}
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
