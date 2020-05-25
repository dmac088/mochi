import React from 'react';
import ReactDOM from 'react-dom';
import { LanguageBase } from '../Language';
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
        const historyMock = { history: { push: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        ReactDOM.render(
            <BrowserRouter>
                <LanguageBase {...matchMock} {...historyMock} />
            </BrowserRouter >, div);
    }); 
 
    it('should change the router params to zh-HK when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { replace: jest.fn() }}
        //const historyMock = { history: { replace: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        //create a shallow copy of the component we want to render and mock the props 
        const wrapper = shallow(<LanguageBase {...matchMock} {...historyMock} />);
        //find the element we want to click 
        const link = wrapper.find('a#zh-HK');
        //click the element and mock additional function params
        link.simulate('click', {
            preventDefault: () => {},
            ...{currentTarget: { id: 'zh-HK' }},
        });
        //get the expected urlString
        const urlString = "/zh-HK/USD/";
        //assert that the expected result is the expected string 
        expect(historyMock.history.replace.mock.calls[0][0]).toEqual(urlString);
    });

    it('should change the router params to en-GB when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { replace: jest.fn() }}
        //const historyMock = { history: { replace: jest.fn() }}
        const matchMock = { match: { params: { lang: "zh-HK", curr: "USD" }, path: "/:lang/:curr/"}}
        //create a shallow copy of the component we want to render and mock the props 
        const wrapper = shallow(<LanguageBase {...matchMock} {...historyMock} />);
        //find the element we want to click 
        const link = wrapper.find('a#en-GB'); 
        //click the element and mock additional function params
        link.simulate('click', {
            preventDefault: () => {},
            ...{currentTarget: { id: 'en-GB' }},
        });
        //get the expected urlString
        const urlString = "/en-GB/USD/";
        //assert that the expected result is the expected string 
        expect(historyMock.history.replace.mock.calls[0][0]).toEqual(urlString);
    });

    it('renders English menu item correctly', () => {
        const historyMock = { history: { push: jest.fn() }}
        const matchMock = { match: { params: { lang: "zh-HK", curr: "USD" }, path: "/:lang/:curr/"}}
        const wrapper = shallow(<LanguageBase { ...matchMock } {...historyMock} />);
        const text = wrapper.find('a#en-GB');
        expect(text.text()).toBe('English');
    });

    it('renders Chinese menu item correctly', () => {
        const historyMock = { history: { push: jest.fn() }}
        const matchMock = { match: { params: { lang: "zh-HK", curr: "USD" }, path: "/:lang/:curr/"}}
        const wrapper = shallow(<LanguageBase { ...matchMock } {...historyMock} />);
        const text = wrapper.find('a#zh-HK');
        expect(text.text()).toBe('Chinese');
    });
    
    it("renders english in language menu header correctly", () => {
        const historyMock = { history: { push: jest.fn() }}
        const matchMock = { match: { params: { lang: "en-GB", curr: "USD" }, path: "/:lang/:curr/"}}
        const wrapper = shallow(<LanguageBase { ...matchMock } {...historyMock} />);
        const text = wrapper.find('a#language');
        expect(text.text()).toBe("English");
    });

    it("renders chinese in language menu header correctly", () => {
        const historyMock = { history: { push: jest.fn() }}
        const matchMock = { match: { params: { lang: "zh-HK", curr: "USD" }, path: "/:lang/:curr/"}}
        const wrapper = shallow(<LanguageBase { ...matchMock } {...historyMock} />);
        const text = wrapper.find('a#language');
        expect(text.text()).toBe("Chinese");
    });
 
    it("matches snapshot 1", () => {
        const history = createMemoryHistory('/en-GB/HKD');
        const matchMock = { match: { params: { lang: "zh-HK", curr: "USD" }, path: "/:lang/:curr/"}};
        const tree = renderer.create(
            <BrowserRouter>
                <LanguageBase
                    {...matchMock}
                    {...history} />
            </BrowserRouter >
        ).toJSON();
        expect(tree).toMatchSnapshot();
    }); 

 }); 