import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../../data/categories/api';
import { changeCategory, createRouteProps } from '../../../services/helpers/RouteHelper';
import _ from 'lodash';

class CategorySidebar extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "categoryFacets": null,
      "selectedFacets": [],
    };
  }

  componentDidMount() {
    this.setState({
      "categoryFacets": this.props.categoryFacets,
    });
  }

  componentDidUpdate(prevProps, prevState) {
    if (_.isEqual(this.state.categoryFacets, this.props.categoryFacets)) {return}
    this.setState({
      "categoryFacets": this.props.categoryFacets,
    });
  }

  applyFacet = (e, props) => {
    console.log(e.currentTarget.id);
    console.log(this.state.categoryFacets);
    const selectedFacets = _.cloneDeep(this.state.selectedFacets, true);
    if(this.state.selectedFacets.find(o => o.value === e.currentTarget.id)) { return }
    selectedFacets.push(this.state.categoryFacets.find(o =>
       o.value === e.currentTarget.id
    ));
    this.setState({
      "selectedFacets": selectedFacets,
    });
  }


  renderCategories = (category, props) => {
    const { getMaxPrice } = props;
    return category.children.map(child => {
      return(
        <li key={child.categoryId} >
          <a onClick={(e) => changeCategory(e, props)} id={child.categoryDesc} href="#">
            {child.categoryDesc} <span className="badge badge-pill badge-secondary">{child.productCount}</span>
          </a>
        </li>
      );
    });
  }

  renderCategoryFacets = (categoryFacets, props) => {
    return categoryFacets.map(categoryFacet => {
      return(
        <li key={categoryFacet.value} >
          <a onClick={(e) => {
                                e.preventDefault();
                                this.applyFacet(e, props);
                             }} id={categoryFacet.value} href="#">
            {categoryFacet.value} <span className="badge badge-pill badge-secondary">{categoryFacet.count}</span>
          </a>
        </li>
      );
    });
  }

  render() {
    const { category, categoryFacets, history, match, location } = this.props;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (routeProps.match.params[0].toLowerCase() === "search");
    const isCategory = (routeProps.match.params[0].toLowerCase() === "category");
    if(isCategory && !category) {return null}
    if(isCategory && !(category.children)) {return null}
    if(isCategory && category.children.length === 0) { return null }
    if(isSearch && !categoryFacets) { return null }

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {(isCategory)
            ? this.renderCategories(category, routeProps)
            : this.renderCategoryFacets(categoryFacets, routeProps)}
        </ul>
      </div>
    );
  };

}

export default withRouter(CategorySidebar);
