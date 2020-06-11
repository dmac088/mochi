import React from 'react';

function Download() {
    return (
        <React.Fragment>
            <h3>Downloads</h3>

            <div className="myaccount-table table-responsive text-center">
                <table className="table table-bordered">
                    <thead className="thead-light">
                        <tr>
                            <th>Product</th>
                            <th>Date</th>
                            <th>Expire</th>
                            <th>Download</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>Mostarizing Oil</td>
                            <td>Aug 22, 2018</td>
                            <td>Yes</td>
                            <td><a href="#" className="btn">Download File</a></td>
                        </tr>
                        <tr>
                            <td>Katopeno Altuni</td>
                            <td>Sep 12, 2018</td>
                            <td>Never</td>
                            <td><a href="#" className="btn">Download File</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </React.Fragment>
    );
}

export default Download;