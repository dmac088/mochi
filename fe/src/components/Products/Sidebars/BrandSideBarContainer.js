import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { changeBrand, createRouteProps } from '../../../services/helpers/RouteHelper';

class BrandSidebarContainer extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "facets": null,
      "selectedFacets":  [],
    };
  }

  componentDidMount() {
    this.setState({
      "facets": this.props.facets,
    });
  }

  componentDidUpdate(prevProps, prevState) {
    if (_.isEqual(this.state.facets, this.props.facets)) {return}
    this.setState({
      "facets": this.props.facets,
    });
  }

  renderBrandsForCategory = (category, selectedFacet, routeProps) => {
    return category.categoryBrands.map(brand => {
      const isActive = (selectedFacet === brand.brandDesc)
      return(
        <li key={brand.brandId}>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={brand.brandDesc} href="#">
            {brand.brandDesc} <span className="badge badge-pill badge-secondary">{brand.productCount}</span>
          </a>
        </li>
      )
    })
  }

  renderAll = (category, isActive, routeProps) => {
      if(category.categoryBrands.length <= 1) {return}
      return (
        <li>
          <a className={(isActive) ? "active" : ""} onClick={(e) => changeBrand(e, routeProps)} id={"All"} href="#">
            All
          </a>
        </li>
      )
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
    newSelectedFacets.push(this.state.facets.filter(o => o.token === e.currentTarget.id)[0]);
    this.setState({
      "selectedFacets": newSelectedFacets,
    }, () => {
      //  console.log(this.state.selectedFacets);
        this.props.refresh(0, this.state.selectedFacets);
    });
  }


  isActive = (facet, selectedFacets) => {
    return (selectedFacets.find(o => o.token === facet.token));
  }


  renderFacets = (facets, selectedFacets, props) => {
    return facets.map(facet => {
      return(
        <li key={facet.id}>
          <a className={(this.isActive(facet, selectedFacets)) ? "active" : ""} onClick={(e) => {
                                e.preventDefault();
                                this.applyFacet(e, props);
                             }} id={facet.token} href="#">
            {facet.desc} <span className="badge badge-pill badge-secondary">{facet.count}</span>
          </a>
        </li>
      );
    });
  }


  render() {
    const { brand, history, match, location } = this.props;
    const routeProps = createRouteProps(history, match, location);
    const { facets, selectedFacets } = this.state;
    const isSearch = (match.params[0] === "search");
    const isCategory = (match.params[0] === "category");
    if(isCategory && !category) { return null }
    if(isSearch && !facets) { return null }
    return (
        <div className="sidebar mb-35">
          <h3 className="sidebar-title">Filter By Brand</h3>
          <ul className="product-categories">
            {(isCategory)
              ? renderAll(category, isActive, routeProps)
              : null}
            {(isCategory)
              ? this.renderBrandsForCategory(category, selectedFacet, routeProps)
              : this.renderFacets(facets, selectedFacets, routeProps)}
          </ul>
        </div>
      );
  }
}

export default withRouter(BrandSidebarContainer);
