import React, {useEffect} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { logoutSession } from '../../../../actions/SessionActions';
import { findByUserName } from '../../../../actions/CustomerActions';
import { getAccountPath } from "../../Helpers/Route/Route";
import { Link } from 'react-router-dom';
import  Address  from "../Address/Address";
import  Download  from "../Download/Download";
import  Default from "../Default/Default";
import  AccountDetails  from "../AccountDetails/AccountDetails";


function Dashboard(props) {
  const { match, history } = props;

  const dispatch = useDispatch();

  const logout = (e) => {
    e.preventDefault();
    dispatch(logoutSession());
    history.push(getAccountPath(match));
  }
  
  useEffect(() => {
    dispatch(findByUserName());
  }, []);

  const customer = useSelector(state => state.customer);

  return (
    <React.Fragment>
      <div className="my-account-section section position-relative mb-50 fix">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="row">
                <div className="col-lg-3 col-12">
                  <div className="myaccount-tab-menu nav" role="tablist">
                    <Link to={""} className="active" data-toggle="tab"><i className="fa fa-dashboard"></i>
                        Dashboard</Link>

                    <Link to={""}  data-toggle="tab"><i className="fa fa-cart-arrow-down"></i> Orders</Link>

                    <Link to={""}  data-toggle="tab"><i className="fa fa-cloud-download"></i> Download</Link>

                    <Link to={""}  data-toggle="tab"><i className="fa fa-credit-card"></i> Payment
                        Method</Link>

                    <Link to={""}  data-toggle="tab"><i className="fa fa-map-marker"></i> address</Link>

                    <Link to={""}  data-toggle="tab"><i className="fa fa-user"></i> Account Details</Link>

                    <a href="#" onClick={logout}><i className="fa fa-sign-out"></i> Logout</a>
                  </div>
                </div>

                <div className="col-lg-9 col-12">
                  <div className="tab-content" id="myaccountContent">
                    <div className="tab-pane fade show active" id="dashboad" role="tabpanel">
                      <div className="myaccount-content">
                        <Default 
                          customer={customer}/>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Dashboard;