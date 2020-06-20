import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";
import customerReducer from "./customerReducer";

export default combineReducers({
  categories: categoryReducer,
  discovery: discoveryReducer,
  session: sessionReducer,
  customer: customerReducer,
});
