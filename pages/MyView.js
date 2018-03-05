import {PropTypes, Component} from 'react';
import {requireNativeComponent, View} from 'react-native';
import * as React from 'react';

class MyView extends Component {

  constructor(props) {
    super(props);
  }

  static propTypes = {
    ...View.propTypes,
  };

  render() {
    return <MyView
      {...this.props}/>;
  }
}

export default requireNativeComponent('MyView', MyView);