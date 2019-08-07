import React from 'react';
import { withRouter } from 'react-router-dom';
import { Link } from "react-router-dom";
import { changeCategory, homeRouteString, createRouteProps } from '../services/helpers/routeHelper';

  const findCategoryByName = (categoryList, term) => {
    return categoryList.filter(function(value, index, arr){
      return value.facetDisplayValue === term;
    })[0];
  }

  const findCategoryById = (categoryList, categoryId) => {
    return categoryList.filter(function(value, index, arr){
      return value.facetId === categoryId;
    })[0];
  }

  const createLineage = (categoryList, categoryId, result) => {
    const category = findCategoryById(categoryList, categoryId);
    if(!category) { return }
    if(category.facetLevel === 0) {return;}
    result.push(findCategoryById(categoryList, categoryId));
    if(!category.facetParentId) {return;}
    createLineage(categoryList, category.facetParentId, result);
  }

  const renderCategoryLineage = (categoryList, term, routeProps) => {
    const result = [];
    const category = findCategoryByName(categoryList, term);
    if(!category) { return }
    createLineage( categoryList,
                        category.facetId,
                        result);
    return result.reverse().map(category => {
      return (
        <li key={category.facetId} className="active">
          <a id={category.facetDisplayValue} onClick={(e) => changeCategory(e, routeProps)} href="#">
            {category.facetDisplayValue}
          </a>
        </li>
      )
    });
  }

  const renderProduct = (productId) => {
    if(!productId) {return;}
    return (
      <li key={productId}>
          Product ID: {productId}
      </li>
    );
  }

  const renderSearch = (term) => {
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


  const renderPage = (page) => {
    return (
      <li>
          {page}
      </li>
    )
  }

  export const BreadCrumb = withRouter(({history, match, location, ...props}) => {

    const routeProps = createRouteProps(history, match, location);
    const { term, productId } = routeProps.match.params;
    const type = routeProps.match.params[0];
    const { page, categoryList } =  props;
    const isCategory = (type && type.toLowerCase() === "category");
    const isSearch = (type && type.toLowerCase() === "search")
    const renderProductFlag = (productId);

    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={homeRouteString(routeProps.match)}><i className="fa fa-home"></i> Home</Link></li>
    							{(isCategory)
                   ? renderCategoryLineage(categoryList, term, routeProps)
                   : null}
                  {(renderProductFlag)
                    ? renderProduct(productId)
                    : null}
                  {(isSearch)
                    ? renderSearch(term)
                    : null}
                  {(!renderProductFlag && !isCategory && !isSearch)
                    ? renderPage(page)
                    : null}
    						</ul>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    );
  });
