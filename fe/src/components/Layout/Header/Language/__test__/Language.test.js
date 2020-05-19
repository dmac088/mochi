import React from 'react';
import ReactDOM from 'react-dom';
import LanguageBase from '../Language';
import { BrowserRouter } from 'react-router-dom';
import { createMemoryHistory } from 'history'
import { render, cleanup } from '@testing-library/react';
import "@testing-library/jest-dom/extend-expect";
import renderer from 'react-test-renderer';
import Enzyme, { shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

describe('Test language menu', () => {

    afterEach(cleanup);

    it("renders without crashing", () => {
        const div = document.createElement("div");
        ReactDOM.render(
            <BrowserRouter>
                <LanguageBase />
            </BrowserRouter >, div);
    });

    it("renders english in language menu correctly", () => {
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

    it("renders chinese in language menu correctly", () => {
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


    it("matches snapshot 2", () => {
        const history = createMemoryHistory('/zh-HK/USD') 
        const tree = renderer.create(
            <BrowserRouter>
                <LanguageBase
                    lang='zh-HK'
                    curr='USD'
                    history={history} />
            </BrowserRouter >
        ).toJSON();
        expect(tree).toMatchSnapshot();
    }); 
});