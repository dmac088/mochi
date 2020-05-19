import React from 'react';
import ReactDOM from 'react-dom';
import LanguageBase from '../Language';
import { BrowserRouter } from 'react-router-dom';
import { render } from '@testing-library/react';

it("renders without crashing", () => {
    const div = document.createElement("div");
    ReactDOM.render(
        <BrowserRouter>
            <LanguageBase />
        </BrowserRouter >, div);
});

it("renders language menu correctly", () => {
    render( 
        <BrowserRouter>
            <LanguageBase />
        </BrowserRouter >);
});

