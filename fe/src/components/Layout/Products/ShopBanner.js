import React from 'react';
import veg01 from '../../../assets/images/categories/VEG01.jpg';

 function ShopBanner(props) {
    return (
      <div className="shop-page-banner mb-35">
        <a href="shop-left-sidebar.html">
          <img src={veg01} className="img-fluid" alt="" />
        </a>
      </div>
    );
  }

  export default ShopBanner;