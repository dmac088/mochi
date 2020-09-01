import { combineReducers } from "redux";
import categoryReducer from "./categoryReducer";
import discoveryReducer from "./discoveryReducer";
import sessionReducer from "./sessionReducer";
import customerReducer from "./customerReducer";
import bagReducer from "./bagReducer";
import bagContentsReducer from "./bagContentsReducer";

export default combineReducers({
  categories:   categoryReducer,
  discovery:    discoveryReducer,
  session:      sessionReducer,
  customer:     customerReducer,
  bag:          bagReducer,
  bagContents:  bagContentsReducer,
});
