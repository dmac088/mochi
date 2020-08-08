import React from "react";
import BrandMenu from './BrandMenu';
import BasicMenuItem from '../BasicMenuItem';
import { getContactPath, getHomePath } from '../../../Helpers/Route/Route';

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
                        routePath={getHomePath(match)}
                        descKey={'accessories'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getHomePath(match)}
                        descKey={'fashion'} />
                </li>
                <li>
                    <BasicMenuItem
                        {...props}
                        routePath={getHomePath(match)}
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