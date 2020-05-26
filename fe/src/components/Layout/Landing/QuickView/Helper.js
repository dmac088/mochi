import React from 'react';
import ReactDOM from 'react-dom';


export const SlickArrowPrev = ({ currentSlide, slideCount, ...props }) => (
      <i {...props} className="fa fa-angle-left slick-arrow"></i>
);

export const SlickArrowNext = ({ currentSlide, slideCount, ...props }) => (
      <i {...props} className="fa fa-angle-right slick-next-btn slick-arrow"></i>
);

export const initialState = {
	"locale": "en-GB",
	"productId": null,
	"product": {"productImage": ""},
	"currentImage": "",
	"quantity": 1,
	"isShowing": false,
	"isLoading": false,
};

export const settings = {
	prevArrow: <SlickArrowPrev />,
	nextArrow: <SlickArrowNext />,
	slidesToShow: 3,
	responsive: [{
		breakpoint: 1200,
		settings: {
			slidesToShow: 3,
			slidesToScroll: 3
		}
	},
	{
		breakpoint: 991,
		settings: {
			slidesToShow: 2,
			slidesToScroll: 2
		}
	},
	{
		breakpoint: 767,
		settings: {
			slidesToShow: 3,
			slidesToScroll: 3
		}
	},
	{
		breakpoint: 480,
		settings: {
			slidesToShow: 3,
			slidesToScroll: 3
		}
	}
]};
