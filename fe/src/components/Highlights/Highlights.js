import React, { Component } from 'react';
import Category from './Tabs/Category';

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
    this.setState({
      "selectedCategory": landingCategories[0].categoryCode,
    });
  }


  renderTabHeaders = (categoryList, selectedCategory) => {
    return categoryList.map(category => {
      const isActive = (category.categoryCode === selectedCategory);
      return (
        <a  key={category.categoryId}
            onClick={this.showTab}
            className={"nav-item nav-link " + ((isActive) ? " active" : "")}
            id={category.categoryCode}
            data-toggle="tab"
            href="#{category.categoryCode}"
            role="tab"
            aria-selected="true">
          {category.categoryDesc}
        </a>
      )
    });
  }

  renderTabs = (categoryList, selectedCategory) => {
    const { match, history, setCurrentProductId } = this.props;
    return categoryList.map(category => {
      const isActive = (category.categoryCode === selectedCategory);
      return (
        <div key={category.categoryId}
             className={"tab-pane fade "  + ((isActive) ? " show active" : "")}
             id={category.categoryCode}
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
    const { landingCategories } = this.props;
    if(landingCategories.length === 0) { return null; }
    const { selectedCategory } = this.state;
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
