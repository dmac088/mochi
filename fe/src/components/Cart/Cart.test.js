// App.test.js
import React from 'react';
import { Cart } from './Cart';
import { shallow, mount } from 'enzyme';

describe('Examining the syntax of Jest tests', () => {

	it("Cart renders", () => {
		const wrapper = shallow(<Cart  />);
    //wrapper.setProps({ history: { push } });
		expect(wrapper.exists()).toBe(true);

		//wrapper.find("Vegetables").simulate('click');
		//expect(result).toEqual("Hello World!");
	});


	it("another renders", () => {
		const wrapper = shallow(<Cart  />);
    //wrapper.setProps({ history: { push } });
		expect(wrapper.exists()).toBe(true);

		//wrapper.find("Vegetables").simulate('click');
		//expect(result).toEqual("Hello World!");
	});

});
