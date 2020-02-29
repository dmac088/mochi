
var packageJSON = require('./package.json');
const path = require('path');
const webpack = require('webpack');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');


const PATHS = {
  build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
};

module.exports = {
  watch: false,
  context: path.resolve(__dirname, './src'),
  devServer: {
    contentBase: "./public",
    compress: true,
    port: 3000,
    open: true,
    historyApiFallback: true,
    watchOptions: {
      // Delay the rebuild after the first change
      aggregateTimeout: 300,

      // Poll using interval (in ms, accepts boolean too)
      poll: 1000,
    },
  },
  entry: {
    app: './index.js',
  },
  output: {
    path: PATHS.build,
    publicPath: "dist",
    filename: '[name].bundle.js',
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        include:[path.resolve(__dirname, 'src')],
        exclude: [path.resolve(__dirname,"node_modules")],
        use: {
          loader: "babel-loader"
        }
      },
      {
        test: /\.css$/,
        use:  [
                  'style-loader',
                  MiniCssExtractPlugin.loader,
                  'css-loader'
              ]
      },
      {
        test: /\.scss$/,
        use: [
          'style-loader',
          MiniCssExtractPlugin.loader,
          'css-loader',
          'postcss-loader',
          'sass-loader'
        ]
      },
      {
        test: /\.(jpe?g|png|gif|svg)$/i,
        use: "file-loader"
      }
    ]
  },
  devtool: "inline-source-map",
  plugins: [
    new MiniCssExtractPlugin({
      filename: '[name].bundle.css'
    }),

    new HtmlWebpackPlugin({
      inject: false,
      hash: true,
      template: '../public/index.html',
      filename: 'index.html'
    })
  ]
};
