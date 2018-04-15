# Serving Content with Spring MVC

Example which uses a single controller and a single URL to return several types of content.

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

- Single controller for several content types

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [latest snapshot Javadoc site][javadoc-develop].

The documentation site is actually a Maven site, and its sources are included in the project. If required it can be generated by using the following Maven command:

```
mvn verify site -P h2,jetty
```

The verify phase is required, otherwise some of the reports won't be generated.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 8

All other dependencies are handled through Maven, and noted in the included POM file.

### Profiles

Maven profiles are included for setting up the database and an embedded server.

| Profile  | Database              |
|----------|-----------------------|
| h2       | H2 in-memory database |
| mysql    | MySQL database        |
| postgres | PostgreSQL database   |

| Profile  | Server                   |
|----------|--------------------------|
| jetty    | Jetty embedded server    |
| tomcat7  | Tomcat 7 embedded server |

### Installing

The project can be installed by creating the war file and deploying it into a server.

### Running

To run the project locally in an embedded server just use the following Maven command for deploying to Jetty with an H2 in-memory database:

```
mvn jetty:run-war -P h2,jetty
```

An embedded Tomcat can be used through this other profile:

```
mvn tomcat7:run-war -P h2,tomcat7
```

With this the project will be accessible at [http://localhost:8080/serving_content/].

This path is defined in the server.test.path property inside the pom, and can be changed by modifying its value.

### Running the tests

The project requires a database and a server for being able to run the integration tests.

Just like running the project, an embedded server with an in-memory database can be used:

```
mvn verify -P h2,jetty
```

### Packaging the WAR

When creating the WAR file the database connection credentials should be set manually:

```
mvn package -P mysql -Ddatabase.username=[username] -Ddatabase.password=[password] -Ddatabase.url=[DB url]
```

Otherwise the project will try to use the default testing values.

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under the [MIT License][license].

[issues]: https://github.com/bernardo-mg/spring-mvc-serving-content-example/issues
[javadoc-develop]: ${urlSiteDevelop}/spring-mvc-serving-content-example/apidocs
[javadoc-release]: ${urlSiteRelease}/spring-mvc-serving-content-example/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/spring-mvc-serving-content-example
[site-develop]: ${urlSiteDevelop}/spring-mvc-serving-content-example
[site-release]: ${urlSiteRelease}/spring-mvc-serving-content-example
