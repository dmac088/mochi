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
import 'font-awesome/css/font-awesome.min.css';
import {
  BrowserRouter as Router
} from 'react-router-dom';


ReactDOM.render(<Provider store={store}>
                  <Router>
                    <App />
                  </Router>
                </Provider>, document.getElementById('root'));
                
registerServiceWorker();
