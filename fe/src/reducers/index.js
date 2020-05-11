import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";

export default combineReducers({
  category: categoryReducer,
  discovery: discoveryReducer,
});
