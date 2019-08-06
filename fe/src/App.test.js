// App.test.js

import {sum} from './math';

describe('Examining the syntax of Jest tests', () => {
   
  it('sums numbers', () => {
      expect(sum(1, 2)).toEqual(3);
      expect(sum(2, 2)).toEqual(4);
   });
});