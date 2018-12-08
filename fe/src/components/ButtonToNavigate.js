import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom'

const ButtonToNavigate = ({ history }) => (
  <button
    type="button"
    onClick={() => history.push('/Signup')}
  >
    Navigate
  </button>
);


ButtonToNavigate.propTypes = {
    history: PropTypes.shape({
    push: PropTypes.func.isRequired,
  }),
};

export default withRouter(ButtonToNavigate);
