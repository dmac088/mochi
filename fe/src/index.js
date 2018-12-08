import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from "react-redux";
import store from './store';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from 'react-router-dom';

ReactDOM.render(<Provider store={store}>
                  <BrowserRouter>
                      <App />
                  </BrowserRouter>
                </Provider>, document.getElementById('root'));
registerServiceWorker();
