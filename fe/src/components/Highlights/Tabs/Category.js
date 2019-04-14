import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Column from '../Column';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight, chunkArray } from '../../../services/helpers/Helper';
import { getValue } from '../../../config/lang/selector';
const $ = window.$;

class Category extends Component {

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
    const { category, match, history, setCurrentProductId } = this.props;
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
    const { products } = this.props.category;
    if(!products) {return null;}
    const columns = chunkArray(products, 3);
    return(
        <div key={0} className="tab-slider-container">
          <Slider ref={c => (this.slider = c)} {...settings}>
            {this.renderColumns(columns)}
          </Slider>
        </div>
    )
  }
}

export default withRouter(Category);
