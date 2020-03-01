import { combineReducers } from "redux";
import capabilityReducer from "./capabilityReducer";
import categoryReducer from "./categoryReducer";

export default combineReducers({
  capability: capabilityReducer,
  category: categoryReducer,
});
