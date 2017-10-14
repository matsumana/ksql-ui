module.exports = {
  entry: "./src/main/javascript/index.tsx",
  output: {
    path: `${__dirname}/target/scala-2.12/classes/assets/javascript`,
    filename: "bundle.js"
  },
  devtool: "source-map",
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
  }
};
