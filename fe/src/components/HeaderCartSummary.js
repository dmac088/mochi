import React from 'react';
import { Table } from 'react-bootstrap';

const HeaderCartSummary = (props) =>{
    return(
        <div className="cart-info">
          <Table condensed hover>
            <tbody>
              <tr>
                <td>
                  No. of items
                </td>
                <td>:</td>
                <td>
                  <strong>
                    {props.totalItems}
                  </strong>
                </td>
              </tr>
              <tr>
                <td>
                  Sub Total
                </td>
                <td>:</td>
                <td>
                  <strong>
                    {props.total}
                  </strong>
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
    );
}


export default HeaderCartSummary;
