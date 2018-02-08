/**
 * React Native Webpack Starter Kit
 * https://github.com/jhabdas/react-native-webpack-starter-kit
 */
(function() {
  'use strict'

  var path = require('path')
  var packageJSON = require('./package.json')
  var webpack = require('webpack')
  
  const PATHS = {
	  build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version),
  };
 
  console.log('path to JAR file = ' + PATHS.build);
 
  module.exports = {
    debug: true,
    devtool: 'source-map',
    entry: {
      'index.ios': ['./src/ios/main.ios.js'],
      'index.android': ['./src/android/main.android.js'],
    },
    output: {
      path: PATHS.build,	
      publicPath: '/assets/',
      filename: '[name].bundle.js',
    },
    module: {
      preLoaders: [
        {
          test: /\.(js|jsx|es6)$/,
          include: path.resolve(__dirname, 'src'),
          loader: 'eslint-loader',
        }
      ],
      loaders: [
        {
          test: /\.js$/,
          include: /node_modules\/react-native/,
          loader: 'babel',
          query: {
            cacheDirectory: true,
            presets: ['es2015', 'stage-1', 'react'],
          }
        },
        {
          test: /\.(js|jsx|es6)$/,
          exclude: /node_modules/,
          loader: 'babel',
          query: {
            cacheDirectory: true,
            presets: ['es2015', 'stage-1', 'react'],
          }
        }
      ]
    }
  }
}())
