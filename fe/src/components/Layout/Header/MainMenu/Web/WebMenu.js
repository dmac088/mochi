import React from "react";
import BasicMenuItem from '../BasicMenuItem';
import { getContactPath, getHomePath, getCategoryPath } from '../../../Helpers/Route/Route';

function MainMenu(props) {
    const { match } = props;

    return (
        <nav style={{ "display": "block" }}>
            <ul>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getHomePath(match)}
                        descKey={'home'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getCategoryPath('ACC01', match)}
                        descKey={'accessories'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getCategoryPath('FAS01', match)}
                        descKey={'fashion'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getCategoryPath('HOM01', match)}
                        descKey={'homeandlifestyle'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        descKey={'contact'}
                        routePath={getContactPath(props.match)} />
                </li>
            </ul>
        </nav>
    );
}

export default MainMenu;