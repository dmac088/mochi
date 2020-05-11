import React, { Component } from "react";

export class Category extends Component {
  
  render() {
    const { category } = this.props;
    return (
      <div className="card card-body border-primary mb-3">
        <h4 className="text-primary">
          { category.data.categoryDesc }
          <i className="fas fa-user-edit ml-2" style={{ color: "blue" }} />
          <i className="fas fa-user-times ml-2" style={{ color: "red" }} />
        </h4>

        <ul className="list-group">
          <li className="list-group-item bg-light text-primary">
            Category code: { category.data.categoryCode }
          </li>
          <li className="list-group-item bg-light  text-primary">
            Category description: { category.data.categoryDesc }
          </li>
          <li className="list-group-item bg-light  text-primary">
            Category level: { category.data.categoryLevel }
          </li>
          <li className="list-group-item bg-light  text-primary">
            Product count: { category.data.count }
          </li>
        </ul>
      </div>
    );
  }
}

export default Category;
