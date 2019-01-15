import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import { connect } from 'react-redux';

class HeaderCartSummary extends Component {

  constructor(props) {
    super(props);
  }

  render() {
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
                    {this.props.cart.totalItems}
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
                    {this.props.cart.totalAmount}
                  </strong>
                </td>
              </tr>
            </tbody>
          </Table>
    );
  }
}


export default connect(state => ({
    cart:     state.services.cart,
}))
(HeaderCartSummary);
