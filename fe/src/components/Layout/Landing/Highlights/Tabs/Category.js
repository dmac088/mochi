import React, { useEffect } from 'react';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../HeroSlider/sliderHelper';
import Column from '../Column'
const $ = window.$;

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
  ]
};

function Category(props) {


  const { category } = props;
  console.log(category);


  useEffect(() => {
  
  }, []);
  
  const next = () => {
    this.slider.slickNext();
  }
  
  const previous = () => {
    this.slider.slickPrev();
  }


  let slider;
  return (
    <div key={0} className="tab-slider-container">
      <Slider ref={c => (slider = c)} {...settings}>
        <Column />
        <Column />
        <Column />
        <Column />
        <Column />
        <Column />
        <Column />  
      </Slider>
    </div>
  );
}

export default Category;