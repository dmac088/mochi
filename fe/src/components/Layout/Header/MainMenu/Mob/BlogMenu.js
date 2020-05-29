import React from 'react';

function BlogMenu() {
  
    // componentWillEnter (callback) {
    //   slide(this.container, 'slideDown', null, callback);
    // }
  
    // componentWillLeave (callback) {
    //   slide(this.container, 'slideUp', null, callback);
    // }
  
    let container = null;

    const setContainer = (c) => {
      container = c;
    }
      
    return (
        <ul ref={setContainer} className="sub-menu"
            style={{display: 'block'}}>
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
      )
  }

  export default BlogMenu;