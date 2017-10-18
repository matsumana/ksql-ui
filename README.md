# ksql-ui

[![CircleCI](https://circleci.com/gh/matsumana/ksql-ui.svg?style=shield)](https://circleci.com/gh/matsumana/ksql-ui)

# How to setup in your local environment

- Install sbt

```
$ brew install sbt
```

- Install Node.js

```
$ brew install node
```

- Install yarn

```
$ npm install -g yarn
```

- Install dependent javascript libraries

```
$ cd /path/to/ksql-ui
$ yarn install
```

# How to launch this app in your local environment

```
$ cd /path/to/ksql-ui
$ sbt
[info] Loading global plugins from /path/to/.sbt/0.13/plugins
[info] Loading project definition from /path/to/ksql-ui/project
[info] Set current project to ksql-ui (in build file:/path/to/ksql-ui/)
> run
[info] Running info.matsumana.ksqlui.WebServer
Server online at http://localhost:8080/
Press RETURN to stop...```
```

__Caution__

After that, you must build javascript sources on an another terminal.  
You can choose `yarn build` or `yarn watch`.  
Either doesn't no problem, but `yarn watch` seems useful more than `yarn build`.  
`yarn watch` command compiles the souces continuously.

```
$ cd /path/to/ksql-ui
$ yarn watch
yarn run v1.2.1
$ webpack -w --colors

Webpack is watching the files…

ts-loader: Using typescript@2.5.3 and /path/to/ksql-ui/tsconfig.json
Hash: 22b35ac05bd845519185
Version: webpack 3.7.1
Time: 2260ms
    Asset    Size  Chunks                    Chunk Names
bundle.js  976 kB       0  [emitted]  [big]  main
   [4] (webpack)/buildin/global.js 509 bytes {0} [built]
  [10] ./src/main/javascript/index.ts 383 bytes {0} [built]
    + 40 hidden modules
```

# How to build assemply jar file for production and launch it

```
$ cd /path/to/ksql-ui
$ sbt clean assembly
$ java -jar ./target/scala-2.12/ksql-ui-assembly-*.jar
```
