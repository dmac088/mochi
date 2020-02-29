import React from 'react';
import { withRouter } from 'react-router-dom';
import { Column } from '../Column';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../../services/helpers/uiHelper';
import { chunkArray } from '../../../services/helpers/functionHelper';
import { createRouteProps } from '../../../services/helpers/routeHelper';
import { getValue } from '../../../config/lang/selector';
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
]};

  const next = () => {
    this.slider.slickNext();
  }

  const previous = () => {
    this.slider.slickPrev();
  }

  const renderColumns = (columns, category, setCurrentProductId, routeProps) => {
    return columns.map(column => {
      return (
        <Column
          key={columns.indexOf(column)}
          category={category}
          products={column}
          setCurrentProductId={setCurrentProductId}
        />
      )
    })
  }

  export const Category = withRouter(({history, match, location, ...props}) => {
    let slider;
    const { products } = props.category;
    const routeProps = createRouteProps(history, match, location);
    if(!products) {return null;}
    const columns = chunkArray(products, 3);
    return(
        <div key={0} className="tab-slider-container">
          <Slider ref={c => (slider = c)} {...settings}>
            {renderColumns(columns, props.category, props.setCurrentProductId, routeProps)}
          </Slider>
        </div>
    )
  });
