import React, { Component } from 'react';
import HighlightedColumn from '../HighlightedColumn';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight, chunkArray } from '../../../services/helpers/Helper';
const $ = window.$;

class Featured extends Component {

  constructor(props) {
    super(props);
  }

  next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

  renderColumns = (columns) => {
    return columns.map(column => {
      return (
        <HighlightedColumn
          {...this.props}
          key={columns.indexOf(column)}
          category={"Featured"}
          products={column}
        />
      )
    })
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
    const { featuredProducts } = this.props;
    if(!featuredProducts) {return null;}
    const columns = chunkArray(this.props.featuredProducts, 3);
    return(
        <div key={0} className="tab-slider-container">
          <Slider ref={c => (this.slider = c)} {...settings}>
            {this.renderColumns(columns)}
          </Slider>
        </div>
    )
  }
}

export default Featured;
