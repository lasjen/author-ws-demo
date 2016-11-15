#!/bin/bash
START_APP_SERVER=0
IMAGE_NAME_DB="database/oracle12c"
IMAGE_NAME_WS="webservice/jetty-oracle"

setVar() {
if [ -z "$DB_NR" ]; then
  DB_NR="01"
fi
CONTAINER_NAME_DB="ora12c-db-$DB_NR"
CONTAINER_NAME_APP="jetty-ora-$DB_NR"
TNS_PORT="15$DB_NR"
SSH_PORT="22$DB_NR"
APP_PORT="80$DB_NR"
ORA_PASSW="oracle"
}

createDIR() {
if [ ! -d "$CONTAINER_NAME_DB" ]; then
  mkdir -p $CONTAINER_NAME_DB/oradata
  mkdir -p $CONTAINER_NAME_DB/script
  mkdir -p $CONTAINER_NAME_DB/deploy
  cp script/create_users/* $CONTAINER_NAME_DB/script/.
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
echo "Create new directory for $CONTAINER_NAME_DB"
createDIR
echo "Starting Docker Container: $CONTAINER_NAME_DB "
docker run -d --name $CONTAINER_NAME_DB -p $TNS_PORT:1521 -p $SSH_PORT:22 -e ORACLE_SID=CDBORCL -e ORACLE_PDB=ORCL -v $PWD/$CONTAINER_NAME_DB/oradata:/opt/oracle/oradata -v $PWD/$CONTAINER_NAME_DB/script:/opt/oracle/script $IMAGE_NAME_DB

if [ $(docker ps | grep $CONTAINER_NAME_APP | wc -c) -ne 0 ] && [ $START_APP_SERVER -eq 1 ]; then
  echo "Warning! Jetty container allready running!"
elif [ $START_APP_SERVER -eq 1 ]; then
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
fi
echo "Starting Container: $CONTAINER_NAME_DB"
docker start $CONTAINER_NAME_DB

if [ $START_APP_SERVER -eq 1 ] && [ $(docker ps | grep $CONTAINER_NAME_APP | wc -c) -gt 0 ]; then
  echo "Warning! Jetty container allready running!"
  exit 1
elif [ $START_APP_SERVER -eq 1 ] && [ $(docker ps -a | grep $CONTAINER_NAME_APP | wc -c) -eq 0 ]; then
  echo "Warning! Container $CONTAINER_NAME_APP does not exist"
  exit 1
fi
echo "Starting Jetty Container: $CONTAINER_NAME_APP"
docker start $CONTAINER_NAME_APP
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

createUser() {
is_ready=$(readyDB)
if [ $is_ready = "TRUE" ]; then
  docker exec $CONTAINER_NAME_DB /opt/oracle/script/createUser.sh 
fi
}

#########################
# The command line help #
#########################
display_help() {
    echo "Usage: $0 [option] {new|start|stop|restart|remove|check|passw|user} " >&2
    echo
    echo "   -d, --db_number        Set database container number"
    echo "   -a, --appserv          Startup Jetty container" 
    echo
    # echo some stuff here for the -a or --add-options 
    exit 1
}

################################
# Check if parameters options  #
# are given on the commandline #
################################
while :
do
    case "$1" in
      -d | --db_number)
          if [ $# -ne 0 ]; then
            DB_NR="$2"   # You may want to check validity of $2
          fi
          shift 2
          ;;
      -h | --help)
          display_help  # Call your function
          exit 0
          ;;
      -a | --appserv)
          START_APP_SERVER=1  # Trigger start of application server
          shift
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
setVar

case "$1" in
  new)
    newDB
    echo "Started creating new DB. To check if ready, run ... ./runDB.sh -d <id> check"
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
  user)
    createUser
    ;;
  *)
    display_help
    ;;
esac

exit 1
;;
