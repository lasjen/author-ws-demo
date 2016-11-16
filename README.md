# Author-Demo-WS

## Synopsis

This repository contains a demo web service running towards an Oracle database using:

- Java Object Oriented Query (jOOQ) language

The repository also contains  descriptions and templates to run the database and web server environments.

## Pre requirements

The description below depends on the following requirements:

- [Docker](https://www.docker.com/products/overview)
- [Maven](https://maven.apache.org/download.cgi)

## Getting Started

The following contains a description of how to:

1. Create and run a DevOps environment for deploying and running the demo web service
2. Setting up the JOOQ environment using "trial" licence
3. Deploying the application

### 1.DevOps Environment (using Docker)

#### a. Creatng the  Docker Images
This environment uses two docker containers to run an Oracle database and a Jetty web server.:

* [Oracle Database Container](docker/db-env) - Follow instructions to make an image named "database/oracle:12.1.0.2"
* [Jetty Web Server Container](docker/jetty-env) - Follow the instructions to make an image named "webserver/jetty-oracle" 

#### b. Creating the database and web server containers

To create the database and a Oracle container run the following:

```
cd docker
./runServer.sh new
```
This will create a database and a webservice container. The database container will initialize a database creation.
To check if the database creation is completed and ready for use, run the following:

```
cd docker
./runServer.sh check 
```
#### c. Resetting passwords

When the database has been started run the following to reset sys and system passwords to "oracle":

```
cd docker
./runServer.sh passw
```

#### d. Creating the DEVDATA and DEV users

To create the needed users, run the following comands:
```
cd docker
./runServer.sh users 
```

#### e. Running the docker containers

Run the following commands to start 
```
cd docker
./runServer.sh start
```

#### f. Other commands

Run the following commands to stop, restart and remove the containers:
```
cd docker
./runServer.sh stop		# Stop containers
./runServer.sh restart		# Stop and re-start containers
./runServer.sh remove           # Remove containers and container directory (including database files)
```

### 2. jOOQ setup

#### a. Download the jOOQ API
Go to the [jOOQ download page](http://www.jooq.org/download/) and download the Professional "free trial" edition (Note! The professional edition is required to run towards an Oracle database). 

#### b. Install jOOQ libraries into local maven repository 
Unzip the downloaded file and install files to your maven repository: 
```
# unzip jOOQ-3.8.6.zip
# cd jOOQ-3.8.6
# sh maven-install.sh
```

### 3. Compile, package and Deploy the application





