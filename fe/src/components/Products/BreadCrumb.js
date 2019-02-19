import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class BreadCrumb extends Component {


  render() {
    return (
      <div class="breadcrumb-area mb-50">
    		<div class="container">
    			<div class="row">
    				<div class="col">
    					<div class="breadcrumb-container">
    						<ul>
    							<li><a href="index.html"><i class="fa fa-home"></i> Home</a></li>
    							<li class="active">Shop</li>
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
