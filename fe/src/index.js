import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from "react-redux";
import store from './store';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import 'jquery/src/jquery';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';


ReactDOM.render(<Provider store={store}>
                <App />
                </Provider>, document.getElementById('root'));
registerServiceWorker();
