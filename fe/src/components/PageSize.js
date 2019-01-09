import React from 'react';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

export default class Example extends React.Component {
  constructor(props) {
    super(props);

    //this.toggle = this.toggle.bind(this);
    this.state = {
      dropdownOpen: false
    };
  }

  toggle = () => {
    this.setState(prevState => ({
      dropdownOpen: !prevState.dropdownOpen
    }));
  }

  render() {
    return (
      <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
        <DropdownToggle caret>
          Page Size
        </DropdownToggle>
        <DropdownMenu>
          <DropdownItem id="5" onClick={this.props.changePageSize}>5</DropdownItem>
          <DropdownItem id="10" onClick={this.props.changePageSize}>10</DropdownItem>
        </DropdownMenu>
      </Dropdown>
    );
  }
}
