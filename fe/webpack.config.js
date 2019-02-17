
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
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 3000
  },
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
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: "file-loader"
      }
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: "main.scss"
    }),
    new HtmlWebpackPlugin({
      inject: false,
      hash: true,
      template: '../public/index.html',
      filename: 'index.html'
    })

  ]
};
