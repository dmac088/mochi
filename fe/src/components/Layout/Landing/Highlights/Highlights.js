import React, { useState, useEffect }  from 'react';
import { useSelector } from 'react-redux';
import Category from './Tabs/Category';
import { categoryMaster } from './Categories';

function Highlights(props) {

  const [stateObject, setObjectState] = useState({
    selectedCategoryCode: categoryMaster[0],
  });

  const showTab = (e) => {
    e.preventDefault();
    setObjectState((prevState) => ({
      ...prevState,
      selectedCategoryCode: e.currentTarget.id
    }));
  }

  //on mounting set the selected category to the first in our master list 
  useEffect(() => {
    let isSubscribed = true;
    if(isSubscribed) {
      setObjectState((prevState) => ({
        ...prevState,
        selectedCategoryCode: categoryMaster[0],
      }));
    }
    return () => (isSubscribed = false);
  }, []);

  const categories = useSelector(state => state.categories.list);

  const renderCategoryTabHeaders = (categories) => {
    if(!categories) { return null; }
    return categories.filter(c => categoryMaster.includes(c.data.id)).map((c, index) => {
      const isActive = (c.data.id === stateObject.selectedCategoryCode);
        return (
          <a  key={index}
              className={"nav-item nav-link" + ((isActive) ? " active" : "")}
              id={c.data.id}
              onClick={showTab}
              data-toggle="tab" 
              href="#" 
              role="tab" 
              aria-selected="true">
            {c.data.desc}
          </a>
        )
    }) 
  }

  const renderCategoryTabs = (categories, props) => {
    if(!categories) { return null; }
    return categories.filter(c => categoryMaster.includes(c.data.id)).map((c, index) => {
      const isActive = (c.data.id === stateObject.selectedCategoryCode);
      return (
        <div key={index}
             className={"tab-pane fade "  + ((isActive) ? " show active" : "")}
             id={c.data.id}
             role="tabpanel"
             aria-labelledby="featured-tab">
          <Category
            {...props}
            key={index}
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
                  {renderCategoryTabs(categories, props)}
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
