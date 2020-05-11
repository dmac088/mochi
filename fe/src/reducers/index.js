import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";

export default combineReducers({
  category: categoryReducer,
  discovery: discoveryReducer,
  session: sessionReducer,
});
