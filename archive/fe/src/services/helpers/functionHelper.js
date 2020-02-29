import React from 'react';
import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import qs from 'query-string';

export const updateParams = (search, params) => {
  let urlParams = (qs.parse(search));
  let mergedParams = Object.assign(urlParams, params);
  return qs.stringify(mergedParams);
}

export const chunkArray = (inputArray, perChunk) => {
          return inputArray.reduce((resultArray, item, index) => {
            const chunkIndex = Math.floor(index/perChunk)

            if(!resultArray[chunkIndex]) {
              resultArray[chunkIndex] = [] // start a new chunk
            }

            resultArray[chunkIndex].push(item);

            return resultArray;
          }, []);
        };

export const deepValue = (obj, path, value)  => {
    let parts = path.split('.');
    let curr = obj;
    for(let i=0;i<parts.length-1;i++)
      curr = curr[parts[i]] || {};
      curr[parts[parts.length-1]] = value;
}
