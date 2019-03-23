import React, { Component } from 'react';
import { Link } from "react-router-dom";

class BreadCrumb extends Component {

  render() {
    const { locale, currency } = this.props.match.params;
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={'/'+ locale + '/' + currency}><i className="fa fa-home"></i> Home</Link></li>
    							<li className="active">{this.props.page}</li>
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
