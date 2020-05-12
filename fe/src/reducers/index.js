import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";
import errorReducer from "./errorReducer";

export default combineReducers({
  category: categoryReducer,
  discovery: discoveryReducer,
  session: sessionReducer,
  error: errorReducer,
});
