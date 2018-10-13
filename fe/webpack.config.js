
var packageJSON = require('./package.json');
const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require("extract-text-webpack-plugin");



const PATHS = {
  build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
};

module.exports = {
  watch: false,
  context: path.resolve(__dirname, './src'),
  entry: {
    app: './index.js',
  },
  output: {
    path: PATHS.build,
    publicPath: "dist",
    filename: '[name].bundle.js',
  },
  devtool: 'source-map',
  resolve: {
    alias: {
      moment: 'moment/src/moment'
    }
  },
   
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        include:[path.resolve(__dirname, 'src')],
        exclude: [path.resolve(__dirname,"node_modules")],
        use: "babel-loader"
      },
      {
        test: /\.scss?$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader!sass-loader'
        })
      },
	   {
        test: /\.css?$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader'
        })
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: "file-loader"
      }
    ]
  },
  plugins: [
    new ExtractTextPlugin('style.css')
  ]
};
