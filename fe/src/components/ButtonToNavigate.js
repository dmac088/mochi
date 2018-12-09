import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom'

const ButtonToNavigate = ({ history }) => (
  <button
    className="btn btn-outline-success mr-sm-2 my-2 my-sm-0"
    type="submit"
    onClick={() => history.push('/Signup')}
  >
    Signup
  </button>
);


ButtonToNavigate.propTypes = {
    history: PropTypes.shape({
    push: PropTypes.func.isRequired,
  }),
};

export default withRouter(ButtonToNavigate);
