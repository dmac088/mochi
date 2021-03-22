import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";
import customerReducer from "./customerReducer";
import bagReducer from "./bagReducer";
import bagContentsReducer from "./bagContentsReducer";
import billingAddressReducer from "./billingAddressReducer";
import shippingAddressReducer from "./shippingAddressReducer";
import shippingProviderReducer from "./shippingProviderReducer";
import shippingDestinationReducer from "./shippingDestinationReducer";
import shippingTypeReducer from "./shippingTypeReducer";

export default combineReducers({
  categories:         categoryReducer,
  discovery:          discoveryReducer,
  session:            sessionReducer,
  customer:           customerReducer,
  bag:                bagReducer,
  bagContents:        bagContentsReducer,
  billingAddress:     billingAddressReducer,
  shippingAddress:    shippingAddressReducer,
  shippingProviders:    shippingProviderReducer,
  shippingDestinations:  shippingDestinationReducer,
  shippingTypes:  shippingTypeReducer,
});
