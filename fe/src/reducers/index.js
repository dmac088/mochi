import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";
import customerReducer from "./customerReducer";

export default combineReducers({
  category: categoryReducer,
  discovery: discoveryReducer,
  session: sessionReducer,
  customer: customerReducer,
});
