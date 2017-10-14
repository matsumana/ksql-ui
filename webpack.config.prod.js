const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
  entry: "./src/main/javascript/index.tsx",
  output: {
    path: `${__dirname}/target/scala-2.12/classes/assets/javascript`,
    filename: "bundle.js"
  },
  resolve: {
    extensions: [".ts", ".tsx", ".js"]
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: [
          { loader: "ts-loader" }
        ]
      }
    ]
  },
  plugins: [
    new UglifyJSPlugin()
  ]
};
