import React, { Component } from 'react';
import { Link } from "react-router-dom";

class BreadCrumb extends Component {


  constructor(props) {
    super(props);
    this.state = {}
  }

  componentDidMount() {

  }

  changeCategory = () => {

  }

  getParentCategories = (categoryDesc) => {

  }


  render() {
    const { locale, currency, term } = this.props.match.params;
    const type = this.props.match.params[0];
    const { page } =  this.props;
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={'/'+ locale + '/' + currency}><i className="fa fa-home"></i> Home</Link></li>
    							<li onClick={this.changeCategory} className="active">{term}</li>
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
