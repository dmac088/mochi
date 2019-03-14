import React from 'react';
import ReactDOM from 'react-dom';


export const SlickArrowPrev = ({ currentSlide, slideCount, ...props }) => (
      <i {...props} className="fa fa-angle-left slick-arrow"></i>
);

export const SlickArrowNext = ({ currentSlide, slideCount, ...props }) => (
      <i {...props} className="fa fa-angle-right slick-next-btn slick-arrow"></i>
);
