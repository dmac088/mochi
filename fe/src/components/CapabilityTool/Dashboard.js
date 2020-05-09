import React, { Component } from "react";
import AddButton from "./AddButton";
import Category from "./Category";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getAllCategories } from "../../actions/CategoryActions";

class Dashboard extends Component {

  componentDidMount() {
    this.props.getAllCategories();
  }

  render() {
    const {categories} = this.props;
    return (
      <React.Fragment>
        <AddButton />
        {
          categories.map(category => (
            <Category key={category.categoryCode} category={category}/>
          ))
        }
      </React.Fragment>
    );
  }
}

Dashboard.propTypes = {
  getAllCategories: PropTypes.func.isRequired,
  categories: PropTypes.array.isRequired
}

const mapStateToProps = state => ({
  categories: state.category.categories
})

export default connect(mapStateToProps,
                      {getAllCategories})
                      (Dashboard);
