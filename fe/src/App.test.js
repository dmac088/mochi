// App.test.js
import React from 'react';
import App from './App';
import { shallow } from 'enzyme';
import {sum} from './math';

describe('Examining the syntax of Jest tests', () => {
   
	it('sums numbers', () => {
      expect(sum(1, 2)).toEqual(3);
      expect(sum(2, 2)).toEqual(4);
	});
   
	it('gets a list of categories', () => {
	  shallow(<App />);
      expect(
			sum(1, 2)
			).toEqual(3);
	});
});


