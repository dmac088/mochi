import React from 'react';
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import qs from 'query-string';

export const isMobile = () => {
  return  ((window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth) <= 991);
}

export const SlickArrowLeft = ({ currentSlide, slideCount, ...props }) => (
      <button
        {...props}
        className={
          "slick-prev slick-arrow" +
          (currentSlide === 0 ? " slick-disabled" : "")
        }
        aria-disabled={currentSlide === 0 ? true : false}
        style={{}}
        type="button"
      >
        <i className="fa fa-caret-left"></i>
      </button>
    );

export const SlickArrowRight = ({ currentSlide, slideCount, ...props }) => (
      <button
        {...props}
        className={
          "slick-next slick-arrow" +
          (currentSlide === slideCount - 1 ? " slick-disabled" : "")
        }
        aria-disabled={currentSlide === slideCount - 1 ? true : false}
        style={{}}
        type="button"
      >
        <i className="fa fa-caret-right"></i>
      </button>
    );

export const HsSlickArrowLeft = ({ currentSlide, slideCount, ...props }) => (
      <button
        {...props}
        className={
          "slick-prev slick-arrow" +
          (currentSlide === 0 ? " slick-disabled" : "")
        }
        aria-label="Previous"
        aria-disabled={currentSlide === 0 ? true : false}
        type="button"
      >
        <i className="fa fa-chevron-left"></i>
      </button>
    );

export const HsSlickArrowRight = ({ currentSlide, slideCount, ...props }) => (
          <button
            {...props}
            className={
              "slick-next slick-arrow" +
              (currentSlide === slideCount - 1 ? " slick-disabled" : "")
            }
            aria-label="Next"
            aria-disabled={currentSlide === slideCount - 1 ? true : false}
            type="button"
          >
            <i className="fa fa-chevron-right"></i>
          </button>
        );

export const slide = (container, direction, params = { duration: 500}, callback) => {
      let element = ReactDOM.findDOMNode(container);
      if(element === undefined) {return}
      Velocity(element, direction, params).then(callback);
}

export const spinner = () => {
	return (
		<div className="d-flex justify-content-center">
		  <div className="spinner-border text-success" role="status">
		    <span className="sr-only">Loading...</span>
		  </div>
		</div>
	);
}
