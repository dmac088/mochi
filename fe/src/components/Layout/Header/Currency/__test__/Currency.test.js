import React from 'react';
import ReactDOM from 'react-dom';
import { CurrencyBase } from '../Currency';
import { BrowserRouter } from 'react-router-dom';
import { createMemoryHistory } from 'history'
import { cleanup } from '@testing-library/react';
import "@testing-library/jest-dom/extend-expect";
import renderer from 'react-test-renderer';
import { shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
 
configure({ adapter: new Adapter() });

describe('Test language menu', () => {

    afterEach(cleanup);

    it("renders without crashing", () => {
        const div = document.createElement("div"); 
        const historyMock = { history: { replace: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        ReactDOM.render(
            <BrowserRouter>
                <CurrencyBase {...matchMock} {...historyMock} />
            </BrowserRouter >, div);
    });

    it('should change the router params to HKD when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { replace: jest.fn() }}
        //const historyMock = { history: { replace: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        //create a shallow copy of the component we want to render and mock the props 
        const wrapper = shallow(<CurrencyBase {...matchMock} {...historyMock} />);
        //find the element we want to click 
        const link = wrapper.find('a#HKD');
        //click the element and mock additional function params 
        link.simulate('click', {
            preventDefault: () => {},
            ...{currentTarget: { id: 'HKD' }},
        });
        //get the expected urlString
        const urlString = "/en-GB/HKD/"
        //assert that the expected result is the expected string 
        expect(historyMock.history.replace.mock.calls[0][0]).toEqual(urlString);
    });

    it('should change the router params to USD when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { replace: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        //create a shallow copy of the component we want to render and mock the props
        const wrapper = shallow(<CurrencyBase {...matchMock} {...historyMock} />);
        //find the element we want to click 
        const link = wrapper.find('a#USD');
        //click the element and mock additional function params
        link.simulate('click', {
            preventDefault: () => {},
            currentTarget: { id: 'USD' },
        });
        //get the expected urlString
        const urlString = "/en-GB/USD/"
        //assert that the expected result is the expected string
        expect(historyMock.history.replace.mock.calls[0][0]).toEqual(urlString);
    });

    it('renders HKD menu item correctly', () => {
        const historyMock = { history: { replace: jest.fn() }}
        const wrapper = shallow(<CurrencyBase {...{ match: {params: { lang: "zh-HK", curr: "HKD" }}, ...historyMock}} />);
        const text = wrapper.find('a#HKD');
        expect(text.text()).toBe('HKD');
    });

    it('renders USD menu item correctly', () => {
        const historyMock = { history: { replace: jest.fn() }}
        const wrapper = shallow(<CurrencyBase {...{ match: {params: { lang: "zh-HK", curr: "USD" }}, ...historyMock}} />);
        const text = wrapper.find('a#USD');
        expect(text.text()).toBe('USD');
    });
    
    it("renders USD in currency menu header correctly", () => {
        const historyMock = { history: { replace: jest.fn() }}
        const wrapper = shallow(<CurrencyBase {...{ match: {params: { lang: "en-GB", curr: "USD" }}, ...historyMock}} />);
        const text = wrapper.find('a#currency');
        expect(text.text()).toBe("USD");
    });

    it("renders HKD in currency menu header correctly", () => {
        const historyMock = { history: { replace: jest.fn() }}
        const wrapper = shallow(<CurrencyBase {...{ match: {params: { lang: "zh-HK", curr: "HKD" }}, ...historyMock}} />);
        const text = wrapper.find('a#currency');
        expect(text.text()).toBe("HKD");
    });

    it("matches snapshot 1", () => {
        const history = createMemoryHistory('/en-GB/HKD');
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        const tree = renderer.create(
            <BrowserRouter>
                <CurrencyBase
                    {...matchMock}
                    {...history} />
            </BrowserRouter >
        ).toJSON();
        expect(tree).toMatchSnapshot();
    }); 

}); 
  