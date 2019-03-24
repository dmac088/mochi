import React, { Component } from 'react';
import { Link } from "react-router-dom";

class BreadCrumb extends Component {

  render() {
    console.log(this.props.match)
    const { locale, currency } = this.props.match.params;
    const { page } =  this.props;
    return (
      <div className="breadcrumb-area mb-50">
    		<div className="container">
    			<div className="row">
    				<div className="col">
    					<div className="breadcrumb-container">
    						<ul>
    							<li><Link to={'/'+ locale + '/' + currency}><i className="fa fa-home"></i> Home</Link></li>
    							<li className="active">{page}</li>
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
