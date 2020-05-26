import React from 'react';
import { SlickArrowLeft, SlickArrowRight } from '../../HeroSlider/sliderHelper';
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

const next = () => {
  this.slider.slickNext();
}

const previous = () => {
  this.slider.slickPrev();
}

const renderColumns = (columns, category, setCurrentProductId, routeProps) => {
  //return columns.map(column => {
    // return (
    //   <Column
    //     key={columns.indexOf(column)}
    //     category={category}
    //     products={column}
    //     setCurrentProductId={setCurrentProductId}
    //   />
    //)
  //})
}

const Category = () => {

  return (
    <div></div>
    // <div key={0} className="tab-slider-container">
    //   <Slider ref={c => (slider = c)} {...settings}>
    //     {renderColumns(columns, props.category, props.setCurrentProductId, routeProps)}
    //   </Slider>
    // </div>
  )
}

export default Category;