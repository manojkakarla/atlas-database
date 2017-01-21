# Atlas Database
Database definitions, migrations and DAOs

This has 2 modules
* Migrations
* Dao

## Atlas Migrations
* Holds migration scripts as liquibase changesets.
Uses [dropwizard-migrations](https://dropwizard.github.io/dropwizard/manual/migrations.html) for DB versioning and applying changesets.

#### Usage
`
java -jar atlas-migrations-<version>.jar db migrate config.yml
`

Also uses [maven-liquibase-plugin](http://www.liquibase.org/documentation/maven/) for applying changes locally without building the artifact.

#### Usage
`
mvn liquibase:update
`

## Atlas Dao
* Defines Domain classes
* Defines DAOs
Uses [dropwizard-jdbi](https://dropwizard.github.io/dropwizard/manual/jdbi.html) for database access.



