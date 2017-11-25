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

Specify the your KSQL server with the environment variable `KSQL_API_SERVER`
If the environment variable `KSQL_API_SERVER` is not set, it will connect to `http://localhost:8080`

```
$ cd /path/to/ksql-ui
$ KSQL_API_SERVER=http://your_ksql_server sbt
[info] Loading global plugins from /path/to/.sbt/0.13/plugins
[info] Loading project definition from /path/to/ksql-ui/project
[info] Set current project to ksql-ui (in build file:/path/to/ksql-ui/)
[ksql-ui] $ run
[info] Updating {file:/path/to/ksql-ui/}root...
[info] Resolving jline#jline;2.14.4 ...
[info] Done updating.

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Enter to stop and go back to the console...)
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
$ APPLICATION_SECRET=your_secret KSQL_API_SERVER=http://your_ksql_server ./ksql-ui-x.x.x/bin/ksql-ui
```
