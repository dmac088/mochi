import React, { Component } from 'react';
import { Link } from "react-router-dom";

class BreadCrumb extends Component {


  constructor(props) {
    super(props);
    this.state = {}
  }

  componentDidMount() {

  }

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

  renderPage = (page) => {
    return (
      <li className="active">
          {page}
      </li>
    )
  }

  render() {
    const { locale, currency, term } = this.props.match.params;
    const type = this.props.match.params[0];
    const { page, categoryList } =  this.props;
    console.log(this.props.match.params);
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={'/'+ locale + '/' + currency}><i className="fa fa-home"></i> Home</Link></li>
    							{(type.toLowerCase() === "category" && !(categoryList.length === 0))
                    ? this.renderCategoryLineage(categoryList, term)
                    : this.renderPage(page)}
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
