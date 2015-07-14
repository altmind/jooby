---
layout: index
title: modules
version: 0.8.1
---

## mvn jooby:run
* [jooby:run](/doc/maven-plugin): maven plugin with hot reload of classes, powered by [JBoss Modules](https://github.com/jboss-modules/jboss-modules).

## parser & formatter
* [jackson](/doc/jackson): JSON supports via Jackson.
* [gson](/doc/gson): JSON supports via Gson.

## template engines
* [handlebars/mustache](/doc/hbs): logic less and semantic Mustache templates.
* [freemarker](/doc/ftl): render templates with FreeMarker.

## session
* [redis](/doc/jedis/#redis-session-store): HTTP session on [Redis](http://redis.io).
* [memcached](/doc/spymemcached/#session-store): HTTP session on [Memcached](http://memcached.org).
* [mongodb](/doc/mongodb/#mongodb-session-store): HTTP session on [MongoDB](http://mongodb.github.io/mongo-java-driver/).
* [ehcache](/doc/ehcache/#session-store): HTTP session on [Ehcache](http://ehcache.org).

## sql
* [jdbc](/doc/jdbc): high performance connection pool for jdbc via [Hikari](https://github.com/brettwooldridge/HikariCP).
* [jdbi](/doc/jdbi): fluent API for JDBC.
* [flyway](/doc/flyway): database migrations via [Flyway](http://flywaydb.org/).

## hibernate
* [hibernate](/doc/hbm): object/relational mapping.
* [hibernate validator](/doc/hbv): bean validation.

## mongodb
* [mongodb](/doc/mongodb): Mongodb driver.
* [morphia](/doc/morphia): Object/Document mapper via [Morphia](https://github.com/mongodb/morphia).
* [jongo](/doc/jongo): Query in Java as in Mongo Shell.

## caches
* [redis](/doc/jedis): Advanced cache and key/value store for Java.
* [memcached](/doc/spymemcached): In-memory key-value store for small chunks of arbitrary data.
* [ehcache](/doc/ehcache): Java's most widely-used cache.

## full text search
* [elastic search](/doc/elasticsearch): enterprise full text search via [Elastic Search](https://github.com/elastic/elasticsearch).

## amazon web services
* [aws](/doc/aws): Amazon web services ```s3, sns, sqs, ..., etc.```.

## swagger
* [swagger](/doc/swagger): powerful representation of your RESTful API.

## auth & security
* [pac4j](/doc/pac4j): authentication module via: [Pac4j](https://github.com/pac4j/pac4j).

## scheduling
* [quartz](/doc/quartz): advanced job scheduling.

## enterprise integration patterns (EIP)
* [camel](/doc/camel): enterprise service bus for Jooby.

## emails
* [commons-email](/doc/commons-email): Email supports via [Apache Commons Email](https://commons.apache.org/proper/commons-email).

## amazon web services
* [aws](/doc/aws): wire AWS services and expose them in Guice.

## servers
* [netty](/doc/netty)
* [jetty](/doc/jetty)
* [undertow](/doc/undertow)

## servlet
* [servlet](/doc/servlet): supports war deployments.