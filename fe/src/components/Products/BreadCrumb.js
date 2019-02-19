import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BreadCrumb extends Component {


  render() {
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><a href="index.html"><i className="fa fa-home"></i> Home</a></li>
    							<li className="active">Shop</li>
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
