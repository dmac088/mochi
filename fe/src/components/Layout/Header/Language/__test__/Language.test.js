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
        ReactDOM.render(
            <BrowserRouter>
                <LanguageBase />
            </BrowserRouter >, div);
    });

    it('should change the router params to zh-HK when menu item is clicked', () => {
        const push = jest.fn();
        const wrapper = shallow(<LanguageBase {...{ lang: "en-GB", curr: "USD", history: { push }}} />);
        const link = wrapper.find('a#zh-HK');
        link.simulate('click', {
            preventDefault: () => {},
            currentTarget: { id: 'zh-HK' }
        });
        const result = wrapper.find('a#language');
        expect(result.text()).toBe('zh-HK');
    });

    it('should change the router params to en-GB when menu item is clicked', () => {
        const push = jest.fn();;
        const wrapper = shallow(<LanguageBase {...{ lang: "zh-HK", curr: "USD", history: { push }}} />);
        const link = wrapper.find('a#en-GB');
        link.simulate('click', {
            preventDefault: () => {},
            currentTarget: { id: 'en-GB' }
        });
        const result = wrapper.find('a#language');
        expect(result.text()).toBe('en-GB');
    });

    it('renders English menu item correctly', () => {
        const wrapper = mount(<BrowserRouter>
                                <LanguageBase />
                              </BrowserRouter>);
        const text = wrapper.find('a#en-GB');
        expect(text.text()).toBe('English');
    });

    it('renders Chinese menu item correctly', () => {
        const wrapper = mount(<BrowserRouter>
                                <LanguageBase />
                              </BrowserRouter>);
        const text = wrapper.find('a#zh-HK');
        expect(text.text()).toBe('Chinese');
    });
    
    it("renders english in language menu header correctly", () => {
        const history = createMemoryHistory('/en-GB/HKD')
        const { getByTestId } = render(
            <BrowserRouter>
                <LanguageBase
                    lang='en-GB'
                    curr='HKD'
                    history={history} />
            </BrowserRouter >);
        expect(getByTestId('language')).toHaveTextContent("English");
    });

    it("renders chinese in language menu header correctly", () => {
        const history = createMemoryHistory('/en-GB/HKD')
        const { getByTestId } = render(
            <BrowserRouter>
                <LanguageBase
                    lang='zh-HK'
                    curr='HKD'
                    history={history} /> 
            </BrowserRouter >);
        expect(getByTestId('language')).toHaveTextContent("Chinese");
    });

    it("matches snapshot 1", () => {
        const history = createMemoryHistory('/en-GB/HKD') 
        const tree = renderer.create(
            <BrowserRouter>
                <LanguageBase
                    lang='en-GB'
                    curr='HKD'
                    history={history} />
            </BrowserRouter >
        ).toJSON();
        expect(tree).toMatchSnapshot();
    }); 

}); 