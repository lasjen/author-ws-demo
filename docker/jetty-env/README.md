# Jetty Web Server Docker Image

## Synopsis

This directory shows how to create a Jetty docker image for running web applications towards an Oracle database.
Note! To create and run the Jetty docker container see the [project description](../../../..).

## Getting Started

Download the ojdbc7.jar and ucp.jar files from [Oracle Downloads](http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html), and put in the "docker/jetty-env/source" directory.

Run the following to create a Jetty image:

```
cd docker/jetty-env
docker build . -t "webserver/jetty-oracle"
```
To run the jetty container see the [project description](../../../..)

## Accessing the Oracle database

The Jetty container is setup with a connection pool towards node name "orcl-node".
The connection pool expects to find a DEV user (with password "dev"). This user is created in the [database creation - see 1.d](../../../..).

The connection pool setup is found in the [jetty-plus.xml](source/jetty-plus.xml):

```
  <!-- Add a UCP DataSource -->
  <New id="demo-oracle-ucp" class="org.eclipse.jetty.plus.jndi.Resource">
    <Arg>jdbc/UCPPool</Arg>
    <Arg>
      <New class="oracle.ucp.jdbc.PoolDataSourceImpl">
        <Set name="connectionFactoryClassName">oracle.jdbc.pool.OracleDataSource</Set>
        <Set name="inactiveConnectionTimeout">20</Set>
        <Set name="user">dev</Set>
        <Set name="password">dev</Set>
        <Set name="URL">jdbc:oracle:thin:@orcl-node:1521/orcl</Set>
        <Set name="minPoolSize">2</Set>
        <Set name="maxPoolSize">5</Set>
        <Set name="initialPoolSize">2</Set>
      </New>
    </Arg>
  </New>
``` 
When the container starts up (using runServer.sh script described in the  [project description](../../../..)), the database container will be linked to the jetty server/container with a "db-node" alias.

To access the connection pool see [code](../../src/main/java/no/rightcloud/demo/author/db/DatabaseUtil.java):

``` 
ctx = new InitialContext();
         // Here we lookup the datasource with the name
         // "java:comp/env/jdbc/jcgDS"
         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/UCPPool");
         return ds.getConnection();:
```
## Running the container

To run the jetty container see the [project description](../../../..)

Good luck!



