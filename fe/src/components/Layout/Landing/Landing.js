import React from "react";
import HeroSlider from "./HeroSlider/HeroSlider";
import Policy from "./Policy/Policy";
import Highlights from "./Highlights/Highlights";
import Banner from "./Banner/Banner";
import BestSeller from "./BestSeller/BestSeller";
import BrandSlider from "./BrandSlider/BrandSlider";

function Landing(props) { 
  
  return (
    <React.Fragment>
      <HeroSlider 
        {...props}/>
      <Policy />
      <Highlights />
      <Banner />
      <BestSeller />
      <BrandSlider />
       <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">Introduction To HATEOAS</h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Landing;

