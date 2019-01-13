import React from 'react';
import { Table } from 'react-bootstrap';

const HeaderCartSummary = (props) =>{
    return(
          <Table condensed responsive>
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
    );
}


export default HeaderCartSummary;
