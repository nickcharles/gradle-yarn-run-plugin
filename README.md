gradle-yarn-run-plugin
=====================

A Gradle Plugin to create lifecycle tasks that trigger `yarn run` commands.

Usage
-----
1. [Apply the plugin](https://plugins.gradle.org/plugin/com.palantir.npm-run)
1. Add `yarn` to your `devDependencies` block in your `package.json`
1. Configure your `package.json` `scripts` block

This will allow you to have a consistent Gradle task interface between your Yarn + Java projects. You should be able to
run commands like the following:

```bash
./gradlew build -x check
```

It will build your Yarn package without running the tests.


Tasks
-----
The following tasks are added:

- `clean` - Runs `yarn run clean`
- `test` - Runs `yarn run test`
- `check` - Depends on `:test`
- `build` - Runs `yarn run build` and depends on `:check`. Builds the production-ready version of the assets.
- `buildDev` - Runs `yarn run buildDev` and depends on `:check`. Builds the development-mode version of the assets.


Configuration
-------------
You can configure the `yarn run *` commands in your `build.gradle`. Here's an example:

```groovy
yarnRun {
    clean       "other-clean"       // defaults to "clean"
    test        "other-test"        // defaults to "test"
    build       "other-build"       // defaults to "build"
    buildDev    "other-buildDev"    // defaults to "buildDev"
}
```


Contributing
------------
Before working on the code, if you plan to contribute changes, please read the [CONTRIBUTING](CONTRIBUTING.md) document.


License
-------
This project is made available under the [Apache 2.0 License][license].


[gradle-node-project]: https://github.com/srs/gradle-node-plugin
[license]: http://www.apache.org/licenses/LICENSE-2.0
