import React, { Component } from 'react';
import { connect } from 'react-redux';
import HighlightedColumn from '../HighlightedColumn';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../../services/helpers/Helper';
const $ = window.$;

class Featured extends Component {


  next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

  render() {
    const settings = {
      arrows: true,
      autoplay: false,
      dots: false,
      infinite: true,
      slidesToShow: 4,
      prevArrow: <SlickArrowLeft />,
      nextArrow: <SlickArrowRight />,
      responsive: [{
        breakpoint: 1499,
        settings: {
          slidesToShow: 4,
        }
      },
      {
        breakpoint: 1199,
        settings: {
          slidesToShow: 4,
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
          slidesToShow: 2,
        }
      },
      {
        breakpoint: 479,
        settings: {
          slidesToShow: 1,
        }
      }
    ]};
    return(
        <div className="tab-slider-container">
          <Slider ref={c => (this.slider = c)} {...settings}>
            <HighlightedColumn />
            <HighlightedColumn />
            <HighlightedColumn />
            <HighlightedColumn />
            <HighlightedColumn />
          </Slider>
        </div>
    )
  }
}

export default Featured;
