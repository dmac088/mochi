
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class ShopBanner extends Component {

  render() {
    return (
      <div class="shop-page-banner mb-35">
        <a href="shop-left-sidebar.html">
          <img src="assets/images/banners/shop-banner.jpg" class="img-fluid" alt="">
        </a>
      </div>
    );
  }
}
