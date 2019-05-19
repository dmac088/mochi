import React from 'react';
import _ from 'lodash';

    const renderFacets = (facets, selectedFacets, props) => {
      return facets.filter(o => !(o.token.toLowerCase() === 'empty')).map(facet => {
        return(
          <React.Fragment>
            <li>
              <a onClick={(e) => {
                                 e.preventDefault();
                                 props.applyFacet(e, null);
                          }}
                 id={facet.token}
                 href="#">
                 {facet.desc} ({facet.productCount})
               </a>
            </li>
          </React.Fragment>
        );
      });
    }

    export const TagSidebar = (props) => {
      const { facets, selectedFacets } = props;
      console.log(facets);
      if(!(facets.length > 0)) { return null }
      return (
        <div className="sidebar">
          <h3 className="sidebar-title">Product Tags</h3>
          <ul className="tag-container">
            {renderFacets(facets, selectedFacets, props)}
          </ul>
        </div>
      );
    }
