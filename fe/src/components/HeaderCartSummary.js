import React from 'react';

const HeaderCartSummary = (props) =>{
    return(
        <div className="cart-info">
          <table>
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
          </table>
        </div>
    );
}


export default HeaderCartSummary;
