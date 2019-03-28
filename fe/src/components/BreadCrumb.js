import React, { Component } from 'react';
import { Link } from "react-router-dom";

class BreadCrumb extends Component {

  findCategoryByName = (categoryList, term) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryDesc === term;
    })[0];
  }

  findCategoryById = (categoryList, categoryId) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryId === categoryId;
    })[0];
  }

  createLineage = (categoryList, categoryId, result) => {
    const category = this.findCategoryById(categoryList, categoryId);
    if(category.categoryLevel === 0) {return;}
    result.push(this.findCategoryById(categoryList, categoryId));
    if(!category.parentId) {return;}
    this.createLineage(categoryList, category.parentId, result);
  }

  renderCategoryLineage = (categoryList, term) => {
    const { changeCategory } = this.props;
    const result = [];
    this.createLineage( categoryList,
                        this.findCategoryByName(categoryList, term).categoryId,
                        result);
    return result.reverse().map(category => {
      return (
        <li key={category.categoryId} className="active">
          <a id={category.categoryDesc} onClick={changeCategory} href="#">
            {category.categoryDesc}
          </a>
        </li>
      )
    });
  }

  renderProduct = (productId) => {
    if(!productId) {return;}
    return (
      <li key={productId}>
          Product ID: {productId}
      </li>
    )
  }

  renderSearch = (term) => { 
    return (
      <React.Fragment>
        <li className="active" key={0}>
            Search
        </li>
        <li key={term}>
            {(term==="undefined") ? "All" : term}
        </li>
      </React.Fragment>
    )
  }


  renderPage = (page) => {
    console.log('renderPage');
    return (
      <li>
          {page}
      </li>
    )
  }

  render() {
    const { locale, currency, term, productId } = this.props.match.params;
    const type = this.props.match.params[0];
    const { page, categoryList } =  this.props;
    const renderCategory = (type && type.toLowerCase() === "category" && !(categoryList.length === 0));
    const renderProduct = (renderCategory && productId);
    const renderSearch = (type && type.toLowerCase() === "search")
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={'/'+ locale + '/' + currency}><i className="fa fa-home"></i> Home</Link></li>
    							{(renderCategory)
                   ? this.renderCategoryLineage(categoryList, term)
                   : null}
                  {(renderProduct)
                    ? this.renderProduct(productId)
                    : null}
                  {(renderSearch)
                    ? this.renderSearch(term)
                    : null}
                  {(!renderProduct && !renderCategory && !renderSearch)
                    ? this.renderPage(page)
                    : null}
    						</ul>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    );
  }
}

export default BreadCrumb;
