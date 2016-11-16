# Author-Demo-WS

## Synopsis

This repository contains a web service running towards an Oracle database using:

- Java Object Oriented Query (jOOQ) language
- Docker environments

## Getting Started

### Docker Environment

This environment uses two docker containers to run an Oracle database and a Jetty web server.:

* [Oracle Database Container](https://github.com/oracle/docker-images/tree/master/OracleDatabase) - Follow instructions and name image "database/oracle12c"
* [Jetty Web Server Container](docker/jetty-env) - Create a image named "webserver/jetty-oracle" by follow the instructions 

When you have both images in place, you can start the environment by running:

```
cd docker
./runServer.sh
```




