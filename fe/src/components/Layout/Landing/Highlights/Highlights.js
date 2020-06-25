import React, { useState, useEffect }  from 'react';
import { useSelector } from 'react-redux';
import Category from './Tabs/Category';
import { categoryMaster } from './Categories';

function Highlights() {

  const [stateObject, setObjectState] = useState({
    selectedCategory: null,
  });

  const showTab = (e) => {
    e.preventDefault();
    console.log(e.currentTarget.id);
    setObjectState((prevState) => ({
      ...prevState,
      "selectedCategory": e.currentTarget.id
    }));
 }

  const categories = useSelector(state => state.categories.list);

  const renderCategoryTabs = (categories) => {
    if(!categories) { return null; }
    return categories.filter(c => categoryMaster.includes(c.data.categoryCode)).map(c => {
      // <a className="nav-item nav-link active" data-toggle="tab" href="#" role="tab" aria-selected="true">
        return (
          <a  className="nav-item nav-link" 
              id={c.data.categoryCode}
              onClick={showTab}
              data-toggle="tab" 
              href="#" 
              role="tab" 
              aria-selected="true">
            {c.data.categoryDesc}
          </a>
        )
    }) 
  }

  return (
    <div className="slider tab-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            <div className="tab-slider-wrapper">
              <nav>
                <div className="nav nav-tabs" id="nav-tab" role="tablist">
                  {renderCategoryTabs(categories)}
                </div>
              </nav>
              <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" role="tabpanel" aria-labelledby="featured-tab">
                  <Category />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Highlights;
