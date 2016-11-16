# Oracle Database Server Docker Container

## Synopsis

This directory shows how to create a Oracle database image and then a database  container for running an Oracle database.
The Oracle container will have the following users:

* DEVDATA: The data owner. After new objects are created, run "exec user_grant.granttoroles;".
* DEV: The appliation user with grants towards the DEVDATA objects.

Passwords for both users are set to "dev".

## Getting Started

### Creating the Orace Database docker image

Use the official docker images to build a [Oracle Database Image](https://github.com/oracle/docker-images/tree/master/OracleDatabase). 

1. Clone git repo (https://github.com/oracle/docker-images.git)
2. Download Oracle installation files for 12.1.0.2
3. Build image

#### 1.Clone Git repo
Run the following command to clone the docker-image repo:
```
git clone https://github.com/oracle/docker-images.git
cd docker-images/
ls | grep -v OracleDatabase | xargs rm -rf
```
Note! You will only need the OracleDatabase directory.

#### 2.Download the Oracle installation software

Download the 12.1.0.2 installation files: 
- linuxamd64_12102_database_1of2.zip
- linuxamd64_12102_database_2of2.zip

Place the files in the directory  "docker-images/OracleDatabase/dockerfiles/12.1.0.2"

```
cd docker-images
cp <download path>/linuxamd64_12102_database*.zip OracleDatabase/dockerfiles/12.1.0.2/.
```

#### 3.Build Oracle Database Image

Run the following commands:
```
cd docker-images/OracleDatabase/dockerfiles
./buildDockerImage.sh -v 12.1.0.2 -e -i
```
Run the following command to verify your image:
```
docker images | grep "oracle/database"
```
### Starting and running the Oracle Database Container

To start and run the Oracle database container see the [project description](../../../..)

Good luck!



