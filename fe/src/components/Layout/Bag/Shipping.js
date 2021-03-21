import React from 'react';

function Shipping() {
    return (
        <div className="calculate-shipping">
            <h4>Calculate Shipping</h4>
            <form action="#">
                <div className="row">
                    <div className="col-md-6 col-12 mb-25">
                        <select className="nice-select">
                            <option>Hong Kong Post</option>
                        </select>
                    </div>
                    <div className="col-md-6 col-12 mb-25">
                        <select className="nice-select">
                            <option>Dhaka</option>
                            <option>Barisal</option>
                            <option>Khulna</option>
                            <option>Comilla</option>
                            <option>Chittagong</option>
                        </select>
                    </div>
                    <div className="col-md-6 col-12 mb-25">
                        <input type="text" placeholder="Postcode / Zip" />
                    </div>
                    <div className="col-md-6 col-12 mb-25">
                        <input type="submit" defaultValue="Estimate" />
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Shipping;