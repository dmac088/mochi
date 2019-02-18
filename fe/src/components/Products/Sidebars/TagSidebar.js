import React, { Component } from 'react';
import { withRouter } from "react-router-dom";

class TagSidebar extends Component {

  render() {
    return (
      <div class="sidebar">
        <h3 class="sidebar-title">Product Tags</h3>
        <!--=======  tag container  =======-->

        <ul class="tag-container">
          <li><a href="shop-left-sidebar.html">new</a> </li>
          <li><a href="shop-left-sidebar.html">bags</a> </li>
          <li><a href="shop-left-sidebar.html">new</a> </li>
          <li><a href="shop-left-sidebar.html">kids</a> </li>
          <li><a href="shop-left-sidebar.html">fashion</a> </li>
          <li><a href="shop-left-sidebar.html">Accessories</a> </li>
        </ul>

        <!--=======  End of tag container  =======-->
      </div>
    );
  }
}
