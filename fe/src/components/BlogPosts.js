import React, { Component } from 'react';
import { connect } from 'react-redux';
const $ = window.$;


class BlogPosts extends Component {

componentDidMount(){
    this.initBlogPosts();
}

initBlogPosts = () => {
  /*--
	Single blog post slider active
	-----------------------------------*/
  let blogSlider = $('.blog-slider-container');
	blogSlider.slick({
		arrows: true,
		autoplay: false,
		dots: false,
		infinite: true,
		slidesToShow: 3,
		prevArrow: '<button type="button" class="slick-prev"><i class="fa fa-caret-left"></i></button>',
		nextArrow: '<button type="button" class="slick-next"><i class="fa fa-caret-right"></i></button>',
		responsive: [{
			breakpoint: 1499,
			settings: {
				slidesToShow: 3,
			}
		},
		{
			breakpoint: 1199,
			settings: {
				slidesToShow: 3,
			}
		},
		{
			breakpoint: 991,
			settings: {
				slidesToShow: 2,
			}
		},
		{
			breakpoint: 767,
			settings: {
				slidesToShow: 2,
			}
		},
		{
			breakpoint: 575,
			settings: {
				slidesToShow: 1,
			}
		}
		]
	});
}


render() {
  return (
<div className="slider blog-slider mb-35">
  <div className="container">
    <div className="row">
      <div className="col-lg-12">
        {/*=======  blog slider section title  =======*/}
        <div className="section-title">
          <h3>greenfarm news</h3>
        </div>
        {/*=======  End of blog slider section title  =======*/}
      </div>
    </div>
    <div className="row">
      <div className="col-lg-12">
        {/*=======  blog slide container  =======*/}
        <div className="blog-slider-container pt-30 pb-30 pr-30 pl-30">
          {/*=======  single blog post  =======*/}
          <div className="col">
            <div className="single-post-wrapper">
              <div className="post-thumb">
                <a href="blog-post-image-format.html">
                  <img src="assets/images/blog-image/blog01.jpg" className="img-fluid" alt />
                </a>
              </div>
              <div className="post-info">
                <div className="post-meta">
                  <div className="post-date">29.09.2018</div>
                </div>
                <h3 className="post-title"><a href="blog-post-image-format.html">Blog image post</a></h3>
                <a href="blog-post-image-format.html" className="readmore-btn">continue <i className="fa fa-arrow-right" /></a>
              </div>
            </div>
          </div>
          {/*=======  End of single blog post  =======*/}
          {/*=======  single blog post  =======*/}
          <div className="col">
            <div className="single-post-wrapper">
              <div className="post-thumb">
                <a href="blog-post-image-gallery.html">
                  <img src="assets/images/blog-image/blog02.jpg" className="img-fluid" alt />
                </a>
              </div>
              <div className="post-info">
                <div className="post-meta">
                  <div className="post-date">29.09.2018</div>
                </div>
                <h3 className="post-title"><a href="blog-post-image-gallery.html">Post with gallery</a></h3>
                <a href="blog-post-image-gallery.html" className="readmore-btn">continue <i className="fa fa-arrow-right" /></a>
              </div>
            </div>
          </div>
          {/*=======  End of single blog post  =======*/}
          {/*=======  single blog post  =======*/}
          <div className="col">
            <div className="single-post-wrapper">
              <div className="post-thumb">
                <a href="blog-post-audio-format.html">
                  <img src="assets/images/blog-image/blog03.jpg" className="img-fluid" alt />
                </a>
              </div>
              <div className="post-info">
                <div className="post-meta">
                  <div className="post-date">29.09.2018</div>
                </div>
                <h3 className="post-title"><a href="blog-post-audio-format.html">Blog with audio</a></h3>
                <a href="blog-post-audio-format.html" className="readmore-btn">continue <i className="fa fa-arrow-right" /></a>
              </div>
            </div>
          </div>
          {/*=======  End of single blog post  =======*/}
          {/*=======  single blog post  =======*/}
          <div className="col">
            <div className="single-post-wrapper">
              <div className="post-thumb">
                <a href="blog-post-video-format.html">
                  <img src="assets/images/blog-image/blog04.jpg" className="img-fluid" alt />
                </a>
              </div>
              <div className="post-info">
                <div className="post-meta">
                  <div className="post-date">29.09.2018</div>
                </div>
                <h3 className="post-title"><a href="blog-post-video-format.html">Blog with video</a></h3>
                <a href="blog-post-video-format.html" className="readmore-btn">continue <i className="fa fa-arrow-right" /></a>
              </div>
            </div>
          </div>
          {/*=======  End of single blog post  =======*/}
        </div>
        {/*=======  End of blog slide container  =======*/}
      </div>
    </div>
  </div>
</div>
)
}

}


export default BlogPosts;
