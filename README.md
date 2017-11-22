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

# How to launch in local

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
```

After that, an archive will be generated to `target/universal`

# How to launch in production

## package

```
$ cd /path/to/ksql-ui
$ sbt clean universal:packageZipTarball
```

## launch

```
$ tar xvf ksql-ui-x.x.x.tgz
$ APPLICATION_SECRET=your_secret ./ksql-ui-x.x.x/bin/ksql-ui
```
