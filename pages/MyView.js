import React, { PropTypes, Component } from 'react';
import { requireNativeComponent, View } from 'react-native';

var iface = {
  name: 'MyView',
  propTypes: {
    ...View.propTypes
  }
};
module.exports = requireNativeComponent('MyView', iface);