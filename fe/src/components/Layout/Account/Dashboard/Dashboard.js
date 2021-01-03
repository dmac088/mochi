import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { logoutSession } from '../../../../services/Session';
import { clearBag } from '../../../../services/Bag';
import { findByUserName } from '../../../../services/Customer';
import { getAccountPath, getAccountSubPath } from "../../Helpers/Route/Route";
import { matchPath } from 'react-router';
import { Link } from 'react-router-dom';
import Default from "../Default/Default";
import Orders from "../Orders/Orders";
import Payment from "../Payment/Payment";
import Address  from "../Address/Address";
import Download  from "../Download/Download";
import Admin from "../Admin/Admin";
import AccountDetails  from "../AccountDetails/AccountDetails";


function Dashboard(props) {
  const { match, history } = props;

  const dispatch = useDispatch();
  const customer = useSelector(state => state.customer);
  const discovery = useSelector(state => state.discovery);

  const logout = (e) => {
    e.preventDefault();
    dispatch(logoutSession());
    dispatch(clearBag());
    history.push(getAccountPath(match));
  }
  
  useEffect(() => {
    if(!discovery.loading && discovery.isDone) {
      if(!customer.loading && !customer.isDone) {
        dispatch(findByUserName());
      }
    }
  }, [discovery.loading, discovery.isDone, customer.loading, customer.isDone]);

  const componentChoice = {
      "orders": Orders,
      "payment": Payment,
      "address": Address,
      "download": Download,
      "accountdetails": AccountDetails,
      "dashboard": Default,
      "admin": Admin,
  }

  const mockMatch = matchPath(history.location.pathname, {
    path: "/:lang/:curr/myaccount/:component"
  })
  

  const selectedComponent = () => {   
    if(mockMatch) {
      const { params } = mockMatch;
      const TheComponent = componentChoice[params.component];
      return (
        <TheComponent 
          {...props}
          customer={customer} />
      );
    }
    return <Default {...props}
                    customer={customer}/>;
  }

  const activeClass = (tab) => {
    if(!mockMatch) { return null; } 
    const { params } = mockMatch;
    return (params.component === tab) ? "active" : "";
  }

  return ( 
    <React.Fragment>
      <div className="my-account-section section position-relative mb-50 fix">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="row">
                <div className="col-lg-3 col-12">
                  <div className="myaccount-tab-menu nav" role="tablist">
                    <Link to={() => getAccountSubPath(match, 'dashboard')} className={activeClass('dashboard')} data-toggle="tab"><i className="fa fa-dashboard"></i>Dashboard</Link>

                    <Link to={() => getAccountSubPath(match, 'orders')}  className={activeClass('orders')} data-toggle="tab"><i className="fa fa-cart-arrow-down"></i> Orders</Link>

                    <Link to={() => getAccountSubPath(match, 'download')}  className={activeClass('download')} data-toggle="tab"><i className="fa fa-cloud-download"></i> Download</Link>

                    <Link to={() => getAccountSubPath(match, 'payment')}  className={activeClass('payment')} data-toggle="tab"><i className="fa fa-credit-card"></i> Payment Method</Link>

                    <Link to={() => getAccountSubPath(match, 'address')}  className={activeClass('address')} data-toggle="tab"><i className="fa fa-map-marker"></i> address</Link>

                    <Link to={() => getAccountSubPath(match, 'accountdetails')} className={activeClass('accountdetails')} data-toggle="tab"><i className="fa fa-user"></i> Account Details</Link>

                    <Link to={() => getAccountSubPath(match, 'admin')} className={activeClass('admin')} data-toggle="tab"><i className="fa fa-user"></i> Admin</Link>

                    <a href="#" onClick={logout}><i className="fa fa-sign-out"></i> Logout</a>
                  </div>
                </div>

                <div className="col-lg-9 col-12">
                  <div className="tab-content" id="myaccountContent">
                    <div className="tab-pane fade show active" id="dashboard" role="tabpanel">
                      <div className="myaccount-content">
                        {selectedComponent()}
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