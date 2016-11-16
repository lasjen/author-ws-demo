#!/bin/bash

SCRIPT_NAME=`basename "$0"`
IMAGE_NAME_DB="database/oracle12c"
IMAGE_NAME_WS="webservice/jetty-oracle"
CONTAINER_NR=02                           # Prefix of container name and portnumbers
START_APP_SERVER=1                        # 1 - webserver and db, 0 - only db

##################################################################
###  Don't edit below this line
##################################################################
CONTAINER_NAME_DB="author-db-$CONTAINER_NR"
CONTAINER_NAME_APP="author-web-$CONTAINER_NR"
TNS_PORT="15$CONTAINER_NR"
SSH_PORT="22$CONTAINER_NR"
APP_PORT="80$CONTAINER_NR"
ORA_PASSW="oracle"
PORTS_OK=1
PORTS_FAILED=""

checkPort() {
check_port=`nc -z localhost $1 | grep "succeeded" | wc -l`
if [ "$check_port" -gt 0 ]; then
  PORTS_OK=0
  PORTS_FAILED="$PORTS_FAILED $1"
fi
}

checkDbPorts() {
checkPort $TNS_PORT
checkPort $SSH_PORT

if [ $PORTS_OK -eq 0 ];then
   echo "Warning! Following ports are busy: $PORTS_FAILED"
   exit 1
fi
}

checkHttpPorts() {
checkPort $APP_PORT

if [ $PORTS_OK -eq 0 ];then
   echo "Warning! Following ports are busy: $PORTS_FAILED"
   exit 1
fi
}

checkAllPorts() {
 checkDbPorts
 checkHttpPorts
}

createDIR() {
if [ ! -d "$CONTAINER_NAME_DB" ]; then
  mkdir -p $CONTAINER_NAME_DB/oradata
  mkdir -p $CONTAINER_NAME_DB/script/create_users
  mkdir -p $CONTAINER_NAME_DB/deploy
  cp db-env/script/create_users/* $CONTAINER_NAME_DB/script/create_users/.
fi
}

removeDIR() {
if [ -d $CONTAINER_NAME_DB ] && [ ! -z $CONTAINER_NAME_DB ] ; then
  rm -rf $CONTAINER_NAME_DB
fi
}

checkDBLog() {
is_ready=$(readyDB)
if [ $is_ready = "TRUE" ]; then
  echo "DB is ready for use!"
else
  echo "DB is NOT ready for use!"
fi
}

readyDB() {
check_log=`docker logs $CONTAINER_NAME_DB | grep "DATABASE IS READY TO USE" | wc -c`
if [ $check_log -gt 0 ]; then
  echo "TRUE"
else
  echo "FALSE"
fi
}

newDB() {
if [[ $(docker ps | grep $CONTAINER_NAME_DB | wc -c) -ne 0 ]]; then
  echo "Warning! Oracle container allready running!"
  exit 1
fi
checkDbPorts
echo "Create new directory for $CONTAINER_NAME_DB"
createDIR
echo "Starting Docker Container: $CONTAINER_NAME_DB "
docker run -d --name $CONTAINER_NAME_DB -p $TNS_PORT:1521 -p $SSH_PORT:22 -e ORACLE_SID=CDBORCL -e ORACLE_PDB=ORCL -v $PWD/$CONTAINER_NAME_DB/oradata:/opt/oracle/oradata -v $PWD/$CONTAINER_NAME_DB/script:/opt/oracle/script $IMAGE_NAME_DB

if [ $(docker ps | grep $CONTAINER_NAME_APP | wc -c) -ne 0 ] && [ $START_APP_SERVER -eq 1 ]; then
  echo "Warning! Jetty container allready running!"
elif [ $START_APP_SERVER -eq 1 ]; then
  checkHttpPorts
  docker run -d --name $CONTAINER_NAME_APP -p $APP_PORT:8080 -v $PWD/$CONTAINER_NAME_DB/deploy:/var/lib/jetty/webapps --link $CONTAINER_NAME_DB:orcl-node $IMAGE_NAME_WS
fi
}

startDB() {
if [ $(docker ps | grep $CONTAINER_NAME_DB | wc -c) -gt 0 ]; then
  echo "Warning! Container $CONTAINER_NAME_DB is allready running!"
  exit 1
elif [ $(docker ps -a | grep $CONTAINER_NAME_DB | wc -c) -eq 0 ]; then
  echo "Warning! Container $CONTAINER_NAME_DB does not exist"
  exit 1
else
  checkDBPorts
  echo "Starting Container: $CONTAINER_NAME_DB"
  docker start $CONTAINER_NAME_DB
fi

if [ $START_APP_SERVER -eq 1 ] && [ $(docker ps | grep $CONTAINER_NAME_APP | wc -c) -gt 0 ]; then
  echo "Warning! Jetty container allready running!"
  exit 1
elif [ $START_APP_SERVER -eq 1 ] && [ $(docker ps -a | grep $CONTAINER_NAME_APP | wc -c) -eq 0 ]; then
  echo "Warning! Container $CONTAINER_NAME_APP does not exist"
  exit 1
else
  checkHttpPorts
  echo "Starting Jetty Container: $CONTAINER_NAME_APP"
  docker start $CONTAINER_NAME_APP
fi

}

stopDB() {
if [ $(docker ps | grep $CONTAINER_NAME_DB | wc -c) -eq 0 ]; then
  echo "Warning! Container not running!"
  exit 1
fi
echo "Stopping Container: $CONTAINER_NAME_DB"
docker stop $CONTAINER_NAME_DB
if [ $START_APP_SERVER -eq 1 ] && [ $(docker ps | grep $CONTAINER_NAME_APP | wc -c) -eq 0 ]; then
  echo "Warning! Jetty Container not running!"
  exit 1
fi
echo "Stopping Jetty Container: $CONTAINER_NAME_APP"
docker stop $CONTAINER_NAME_APP
}

removeDB() {
if [[ $(docker ps | grep $CONTAINER_NAME_DB | wc -c) -ne 0 ]]; then
  stopDB 
fi
echo "Removing Container: $CONTAINER_NAME_DB"
docker rm $CONTAINER_NAME_DB
echo "Removing data directory for $CONTAINER_NAME_DB"
removeDIR
echo "Removing Jetty Container: $CONTAINER_NAME_APP"
docker rm $CONTAINER_NAME_APP
}

setPW() {
is_ready=$(readyDB)
if [ $is_ready = "TRUE" ]; then
  docker exec $CONTAINER_NAME_DB ./setPassword.sh $ORA_PASSW
fi
}

createUsers() {
is_ready=$(readyDB)
if [ $is_ready = "TRUE" ]; then
  docker exec $CONTAINER_NAME_DB /opt/oracle/script/create_users/createUsers.sh 
fi
}

#########################
# The command line help #
#########################
display_help() {
    echo "Usage: $0 [options] {new|start|stop|restart|remove|check|passw|users} " >&2
    echo
    echo "    -h     Display this help menu"
    echo
    exit 1
}

################################
# Check if parameters options  #
# are given on the commandline #
################################
while :
do
    case "$1" in
      -h | --help)
          display_help  # Call your function
          exit 0
          ;;
      --) # End of all options
          shift
          break
          ;;
      -*)
          echo "Error: Unknown option: $1" >&2
          ## or call function display_help
          exit 1 
          ;;
      *)  # No more options
          break
          ;;
    esac
done

######################
# Check if parameter #
# is set too execute #
######################
case "$1" in
  new)
    newDB
    echo "Started creating new DB. To check if ready, run ... ./$SCRIPT_NAME check"
    ;;
  start)
    startDB
    echo "StartDB Completed"
    ;;
  stop)
    stopDB
    echo "StopDB Completed"
    ;;
  restart)
    stopDB  
    startDB 
    echo "Restart Completed"
    ;;
  remove)
    removeDB
    echo "Remove Completed"
    ;;
  check)
    checkDBLog
    ;;
  passw)
    setPW
    ;;
  users)
    createUsers
    ;;
  *)
    display_help
    ;;
esac

exit 1
;;
