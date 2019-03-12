import React, { Component } from 'react';
import { connect } from 'react-redux';
import HighlightedColumn from '../HighlightedColumn';
import * as productApi from '../../../data/products/api';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight, chunkArray } from '../../../services/helpers/Helper';
const $ = window.$;

class Featured extends Component {

  constructor(props) {
    super(props);
    this.state = {
      products: [],
    };
  }

  componentWillMount() {
    this.getProducts();
  }

  getProducts= (lang = "en-GB", featured = 1) =>
    productApi.findAllFeatured(lang, featured)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        this.setState({
          products: responseJSON,
        })
    })
    .catch(()=>{
        console.log('getProducts failed!');
  });

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
    const ca = chunkArray(this.state.products, 3);
    return(
        <div key={0} className="tab-slider-container">
          <Slider ref={c => (this.slider = c)} {...settings}>
            <HighlightedColumn
              products={ca[0]}
              key={0}
            />
            <HighlightedColumn
              products={ca[1]}
              key={1}
            />
            <HighlightedColumn
              products={ca[2]}
              key={2}
            />
            <HighlightedColumn
              products={ca[3]}
              key={3}
            />
          </Slider>
        </div>
    )
  }
}

export default Featured;
