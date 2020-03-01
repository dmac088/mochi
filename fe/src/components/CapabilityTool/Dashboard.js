import React, { Component } from "react";
import AddButton from "./AddButton";
import Capability from "./Capability";
import { connect } from "react-redux";
import { getAllCategories } from "../../actions/CategoryActions";

class Dashboard extends Component {


  componentDidMount() {
    this.props.getAllCategories();
  }

  render() {
    return (
      <React.Fragment>
        <AddButton />
        <Capability />
        <Capability />
        <Capability />
      </React.Fragment>
    );
  }
}

export default connect(null,
                      {getAllCategories})
                      (Dashboard);
