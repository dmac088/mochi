import React from 'react';
import { withRouter } from 'react-router-dom';
import { routeHome, routeContact, createRouteProps } from '../../services/helpers/routeHelper';
import { filterCategories } from '../../services/helpers/filterHelper';

export const WebMenu = withRouter(({history, match, location, ...props}) => {
    const routeProps = createRouteProps(history, match, location);
    const { brandCategoryList } = props;
    return (
      <div className="main-menu">
        <nav>
          <ul>
            <HomeMenu />
            <BrandCategoryMenu
              brandCategoryList={brandCategoryList}
             />
            <BlogMenu />
            <ContactMenu />
          </ul>
        </nav>
      </div>
    )
  });

const HomeMenu = () => {
    return (
      <li className="active">
        <a onClick={(e) => routeHome(e, routeProps)} href="#">HOME</a>
      </li>
    );
}

const ContactMenu = () => {
    return (
      <li>
        <a onClick={(e) => routeContact(e, routeProps)} href="#">CONTACT</a>
      </li>
    );
}

const BrandCategoryMenu = (props) => {
  const { brandCategoryList } = props;
  if(!brandCategoryList) { return null; }
  if(brandCategoryList.length === 0) { return null; }

  return brandCategoryList.map(bc => {

    console.log(bc);
    return (
      <li key={bc.facetId} className="menu-item-has-children">
        <a href="#">{bc.facetDisplayValue}</a>
        <ul className="mega-menu three-column">
          {renderBrands(bc.payload.brands)}
        </ul>
      </li>
    );
  });
}

const renderBrands = (brandList) => {
  return brandList.map(b => {
    return (
      <li key={b.facetId}><a href="#">{b.facetDisplayValue}</a></li>
    );
  });
}

const BlogMenu = () => {
    return (
      <li className="menu-item-has-children">
        <a href="#">BLOG</a>
        <ul className="sub-menu">
          <li><a href="blog-3-column.html">Blog 3 column</a></li>
          <li><a href="blog-grid-left-sidebar.html">Blog Grid Left Sidebar</a></li>
          <li><a href="blog-grid-right-sidebar.html">Blog Grid Right Sidebar</a></li>
          <li><a href="blog-list-left-sidebar.html">Blog List Left Sidebar</a></li>
          <li><a href="blog-list-right-sidebar.html">Blog List Right Sidebar</a></li>
          <li><a href="blog-post-left-sidebar.html">Blog Post Left Sidebar</a></li>
          <li><a href="blog-post-right-sidebar.html">Blog Post Right Sidebar</a></li>
          <li><a href="blog-post-image-format.html">Blog Post Image Format</a></li>
          <li><a href="blog-post-image-gallery.html">Blog Post Image Gallery Format</a></li>
          <li><a href="blog-post-audio-format.html">Blog Post Audio Format</a></li>
          <li><a href="blog-post-video-format.html">Blog Post Video Format</a></li>
        </ul>
      </li>
    );
}
