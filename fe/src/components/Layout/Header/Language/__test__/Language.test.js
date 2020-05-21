import React from 'react';
import ReactDOM from 'react-dom';
import { LanguageBase } from '../Language';
import { BrowserRouter } from 'react-router-dom';
import { createMemoryHistory } from 'history'
import { render, cleanup } from '@testing-library/react';
import "@testing-library/jest-dom/extend-expect";
import renderer from 'react-test-renderer';
import Enzyme, { mount, shallow, configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
 
configure({ adapter: new Adapter() });

describe('Test language menu', () => {

    afterEach(cleanup);
    
 
    it("renders without crashing", () => {
        const div = document.createElement("div"); 
        const historyMock = { history: { push: jest.fn() }}
        ReactDOM.render(
            <BrowserRouter>
                <LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock} }/>
            </BrowserRouter >, div);
    });
 
    it('should change the router params to zh-HK when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { push: jest.fn() }}
        //create a shallow copy of the component we want to render and mock the props
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock}} />);
        //find the element we want to click
        const link = wrapper.find('a#zh-HK');
        //click the element and mock additional function params
        link.simulate('click', {
            preventDefault: () => {},
            currentTarget: { id: 'zh-HK' }
        });
        //get the expected urlString
        const urlString = "/zh-HK/HKD/"
        //assert that the expected result is the expected string
        expect(historyMock.history.push.mock.calls[0][0]).toEqual(urlString);
    });

    it('should change the router params to en-GB when menu item is clicked', () => {
        //mock a history object with mock function
        const historyMock = { history: { push: jest.fn() }}
        //create a shallow copy of the component we want to render and mock the props
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock}} />);
        //find the element we want to click
        const link = wrapper.find('a#en-GB');
        //click the element and mock additional function params
        link.simulate('click', {
            preventDefault: () => {},
            currentTarget: { id: 'en-GB' }
        });
        //get the expected urlString
        const urlString = "/en-GB/HKD/"
        //assert that the expected result is the expected string
        expect(historyMock.history.push.mock.calls[0][0]).toEqual(urlString);
    });

    it('renders English menu item correctly', () => {
        const historyMock = { history: { push: jest.fn() }}
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock}} />);
        const text = wrapper.find('a#en-GB');
        expect(text.text()).toBe('English');
    });

    it('renders Chinese menu item correctly', () => {
        const historyMock = { history: { push: jest.fn() }}
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock}} />);
        const text = wrapper.find('a#zh-HK');
        expect(text.text()).toBe('Chinese');
    });
    
    it("renders english in language menu header correctly", () => {
        const historyMock = { history: { push: jest.fn() }}
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "en-GB", curr: "HKD" }, ...historyMock}} />);
        const text = wrapper.find('a#language');
        expect(text.text()).toBe("en-GB");
    });

    it("renders chinese in language menu header correctly", () => {
        const historyMock = { history: { push: jest.fn() }}
        const wrapper = shallow(<LanguageBase {...{ params: { lang: "zh-HK", curr: "HKD" }, ...historyMock}} />);
        const text = wrapper.find('a#language');
        expect(text.text()).toBe("zh-HK");
    });

    it("matches snapshot 1", () => {
        const history = createMemoryHistory('/en-GB/HKD') 
        const tree = renderer.create(
            <BrowserRouter>
                <LanguageBase
                    { ...{ params: { lang: "en-GB", curr: "HKD" }}}
                    history={history} />
            </BrowserRouter >
        ).toJSON();
        expect(tree).toMatchSnapshot();
    }); 

}); 