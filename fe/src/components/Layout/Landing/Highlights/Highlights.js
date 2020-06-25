import React, { useState, useEffect }  from 'react';
import { useSelector } from 'react-redux';
import Category from './Tabs/Category';
import { categoryMaster } from './Categories';

function Highlights() {

  const [stateObject, setObjectState] = useState({
    selectedCategoryCode: categoryMaster[0],
  });

  const showTab = (e) => {
    e.preventDefault();
    console.log(e.currentTarget.id);
    setObjectState((prevState) => ({
      ...prevState,
      selectedCategoryCode: e.currentTarget.id
    }));
  }

  //on mounting set the selected category to the first in our master list 
  useEffect(() => {
    setObjectState((prevState) => ({
      ...prevState,
      selectedCategoryCode: categoryMaster[0],
    }));
  }, []);

  const categories = useSelector(state => state.categories.list);

  const renderCategoryTabHeaders = (categories) => {
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

  const renderCategoryTabs = (categories) => {
    if(!categories) { return null; }
    return categories.filter(c => categoryMaster.includes(c.data.categoryCode)).map(c => {
      const isActive = (c.data.categoryCode === stateObject.selectedCategoryCode);
      return (
        <div key={c.data.categoryCode}
             className={"tab-pane fade "  + ((isActive) ? " show active" : "")}
             id={c.data.categoryCode}
             role="tabpanel"
             aria-labelledby="featured-tab">
          <Category
            category={c}
          />
        </div>
      )
    });
  }

  return (
    <div className="slider tab-slider mb-35">
      <div className="container">
        <div className="row">
          <div className="col-lg-12">
            <div className="tab-slider-wrapper">
              <nav>
                <div className="nav nav-tabs" id="nav-tab" role="tablist">
                  {renderCategoryTabHeaders(categories)}
                </div>
              </nav>
              <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" role="tabpanel" aria-labelledby="featured-tab">
                  {renderCategoryTabs(categories)}
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
