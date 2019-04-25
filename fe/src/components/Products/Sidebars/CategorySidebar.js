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
      "selectedFacets":  [],
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
    if(this.state.selectedFacets.find(o => o.token === e.currentTarget.id)) {
        this.setState({
          "selectedFacets": this.state.selectedFacets.filter(o => o.token !== e.currentTarget.id),
        }, () => {
          //console.log(this.state.selectedFacets);
          this.props.refresh(0, this.state.selectedFacets);
        });
        return;
    }
    const newSelectedFacets = _.cloneDeep(this.state.selectedFacets, true);
    newSelectedFacets.push(this.state.categoryFacets.filter(o => o.token === e.currentTarget.id)[0]);
    this.setState({
      "selectedFacets": newSelectedFacets,
    }, () => {
        //console.log(this.state.selectedFacets);
        this.props.refresh(0, this.state.selectedFacets);
    });
  }

  isActive = (categoryFacet, selectedFacets) => {
    return (selectedFacets.find(o => o.facetToken === categoryFacet.facetToken));
  }

  renderCategoryFacets = (categoryFacets, selectedFacets, props) => {
    return categoryFacets.map(categoryFacet => {
      return(
        <li key={categoryFacet.id}>
          <a className={(this.isActive(categoryFacet, selectedFacets)) ? "active" : ""} onClick={(e) => {
                                e.preventDefault();
                                this.applyFacet(e, props);
                             }} id={categoryFacet.token} href="#">
            {categoryFacet.desc} <span className="badge badge-pill badge-secondary">{categoryFacet.count}</span>
          </a>
        </li>
      );
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

  render() {
    const { category, history, match, location } = this.props;
    const { categoryFacets, selectedFacets } = this.state;
    const routeProps = createRouteProps(history, match, location);
    const isSearch = (routeProps.match.params[0].toLowerCase() === "search");
    const isCategory = (routeProps.match.params[0].toLowerCase() === "category");
    if(isCategory && !category) {return null}
    if(isCategory && !(category.children)) {return null}
    if(isCategory && category.children.length === 0) { return null }
    if(isSearch && !categoryFacets) { return null }

    console.log(categoryFacets);

    return (
      <div className="sidebar mb-35">
        <h3 className="sidebar-title">PRODUCT CATEGORIES</h3>
        <ul className="product-categories">
          {(isCategory)
            ? this.renderCategories(category, routeProps)
            : this.renderCategoryFacets(categoryFacets, selectedFacets, routeProps)}
        </ul>
      </div>
    );
  };

}

export default withRouter(CategorySidebar);
