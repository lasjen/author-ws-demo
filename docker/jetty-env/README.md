# Jetty Web Server Docker Container

## Synopsis

This directory shows how to create a Jetty Container for running web applications towards an Oracle database.


## Getting Started

Run the following to create a Jetty image:

```
cd docker/jetty-env
docker build . -t "webserver/jetty-oracle"
```
To run the jetty container see the [project description](../../../..)

## Accessing the Oracle database

To access the connection pool see [code](../../src/main/java/no/rightcloud/demo/author/db/DatabaseUtil.java):

```
InitialContext ctx = new InitialContext();
         // Here we lookup the datasource with the name
         // "java:comp/env/jdbc/jcgDS"
         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UCPPool");
         return ds.getConnection();:
```

Good luck!



