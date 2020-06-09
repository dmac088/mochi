import { createBrowserHistory } from 'history';
import { matchPath } from 'react-router'

export const history = createBrowserHistory();

export const { params } = matchPath(history.location.pathname, {
    path: "/:lang/:curr"
 })