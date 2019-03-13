import React from 'react';
import ReactDOM from 'react-dom';


export const SlickArrowPrevFullScreen = ({ currentSlide, slideCount, ...props }) => (
      <i className="fa fa-angle-up"></i>
);

export const SlickArrowNextFullScreen = ({ currentSlide, slideCount, ...props }) => (
      <i className="fa fa-angle-down slick-next-btn"></i>
);

export const SlickArrowPrevMobile = ({ currentSlide, slideCount, ...props }) => (
      <i className="fa fa-angle-left"></i>
);

export const SlickArrowNextMobile = ({ currentSlide, slideCount, ...props }) => (
      <i className="fa fa-angle-right slick-next-btn"></i>
);
